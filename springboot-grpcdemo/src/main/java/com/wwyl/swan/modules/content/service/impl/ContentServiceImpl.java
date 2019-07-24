package com.wwyl.swan.modules.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.protobuf.ByteString;
import com.wwyl.kiwi.grpc.service.KiwiFileServerGrpc;
import com.wwyl.kiwi.grpc.service.SendConfigDataAck;
import com.wwyl.kiwi.grpc.service.SendConfigDataRequest;
import com.wwyl.lark.core.code.CommonReturnCode;
import com.wwyl.lark.util.md5.MD5Util;
import com.wwyl.lark.web.model.PageDto;
import com.wwyl.swan.common.code.ContentFeedbackStatusEnum;
import com.wwyl.swan.common.code.ContentStatusEnum;
import com.wwyl.swan.common.exception.ContentException;
import com.wwyl.swan.modules.content.dao.ContentDetailMapper;
import com.wwyl.swan.modules.content.dao.ContentFeedbackMapper;
import com.wwyl.swan.modules.content.dao.ContentRepository;
import com.wwyl.swan.modules.content.model.*;
import com.wwyl.swan.modules.content.service.ContentDetailService;
import com.wwyl.swan.modules.content.service.ContentServerService;
import com.wwyl.swan.modules.content.service.ContentService;
import io.grpc.Channel;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.client.GrpcChannelProperties;
import net.devh.springboot.autoconfigure.grpc.client.GrpcChannelsProperties;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

/**
 * Created with IntelliJ IDEA.
 * User: spockwang
 * Date: 02/12/2017
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 * Description: 生成推送或预取文件
 */
@Service
@Slf4j
@Transactional
public class ContentServiceImpl implements ContentService {

    private final static String TASK_FINISH = "finish";

    @GrpcClient(value = "kiwi")
    private Channel kiwiChannel;

    @Autowired
    private ContentRepository contentRepository;

//    @Autowired
//    private CygnetApi cygnetApi;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Tracer tracer;

    @Value("${cache.machine.amount}")
    private Integer cacheMachineAmount;

    @Value("${grpc.client.kiwi.timeLater}")
    private Long timeLater;

    @Autowired
    private GrpcChannelsProperties grpcChannelsProperties;

    @Resource
    private ContentDetailMapper contentDetailMapper;
    @Resource
    private ContentFeedbackMapper contentFeedbackMapper;

    @Resource
    private ContentDetailService contentDetailService;
    @Resource
    private ContentServerService contentServerService;

    @Value("${content.feedback.expire}")
    private Long contentFeedbackExpire;

    @Value("${content.feedback.fetchExpire}")
    private Long contentFeedbackFetchExpire;

    @Value("${internal.app.turkey.url.swanTask}")
    private String swanTaskUrl;

    @Value("${internal.app.token}")
    private String token;

    @Override
    public ContentEntity save(ContentEntity contentEntity) {
        contentEntity.setOrigin(ContentOriginEnum.SWAN.getCode());
        contentEntity.setCreatedDate(new Date());
        contentEntity.setTaskId(UUID.randomUUID().toString());
        contentEntity.setStatus(ContentStatusEnum.NOT_START.getStatus().intValue());
        contentRepository.save(contentEntity);
        //String[] details = contentEntity.getContent().split(SwanConstant.CONTENT_URL_SPLIT_STRING);
        List<ContentDetailEntity> contentDetailEntities = new ArrayList<>();

        for (String detail : contentEntity.getContents()) {
            ContentDetailEntity contentDetailEntity = new ContentDetailEntity();
            contentDetailEntity.setPushType(contentEntity.getPushType().getValue());
            contentDetailEntity.setContentUrl(detail);
            contentDetailEntity.setContentId(contentEntity.getId());
            contentDetailEntity.setStatus(ContentStatusEnum.IN_PROGRESS.getStatus());
            contentDetailEntity.setCreatedDate(new Date());
            contentDetailEntity.setMd5(contentEntity.getMd5());
            //  获取主机数量和二级域名
            SwanTaskFirstDomainRequest swanTaskFirstDomainRequest = new SwanTaskFirstDomainRequest();
            swanTaskFirstDomainRequest.setTaskNo(contentEntity.getTaskId());
            swanTaskFirstDomainRequest.setTaskType(contentEntity.getContentAction().toString());
            SecondDomainDto secondDomainDto = this.loadSecondDomainDto(swanTaskFirstDomainRequest);

            if(secondDomainDto != null){
//                if(contentEntity.getContentAction() == ContentActionEnum.MD5_ONLY ||
//                        (contentEntity.getContentAction() == ContentActionEnum.FETCH && firstDomain.getFetchStrategy() == FetchStrategyEnum.SECONDDOMAIN.getCode())){
//                    contentDetailEntity.setSecondDomain(secondDomainDto.getSecondDomain());
//
//                }else{
//                    contentDetailEntity.setSecondDomain(" ");
//                }

                contentDetailEntity.setDeviceIds(new ArrayList<>());
                List<String> cacheList = secondDomainDto.getCacheList();
                List<String> shieldList = secondDomainDto.getShieldList();

                if(contentEntity.getContentAction() == ContentActionEnum.PUSH || contentEntity.getContentAction() == ContentActionEnum.MD5_ONLY){
                    contentDetailEntity.setServerNumber(secondDomainDto.getCacheNumber()+secondDomainDto.getShieldNumber());

                    if (null != cacheList && !cacheList.isEmpty()) {
                        contentDetailEntity.getDeviceIds().addAll(cacheList);
                    }

                    if (null != shieldList && !shieldList.isEmpty()) {
                        contentDetailEntity.getDeviceIds().addAll(shieldList);
                    }

                }else{
                    // 静态父取shieldNumber
                    contentDetailEntity.setServerNumber(secondDomainDto.getShieldNumber());

                    if (null != shieldList && !shieldList.isEmpty()) {
                        contentDetailEntity.getDeviceIds().addAll(shieldList);
                    }

//                    if(firstDomain.getFetchStrategy() == FetchStrategyEnum.SECONDDOMAIN.getCode()){
//                        contentDetailEntity.setServerNumber(secondDomainDto.getCacheNumber()+secondDomainDto.getShieldNumber());
//
//                        if (null != cacheList && !cacheList.isEmpty()) {
//                            contentDetailEntity.getDeviceIds().addAll(cacheList);
//                        }
//                    }
                }
            }

            contentDetailEntities.add(contentDetailEntity);
        }

        if (!contentDetailService.insertBatch(contentDetailEntities)) {
            throw new ContentException(CommonReturnCode.SAVE_DATA_FAIL.getCode());
        }

        if (!addContentServer(contentDetailEntities)) {
            throw new ContentException(CommonReturnCode.SAVE_DATA_FAIL.getCode());
        }

        return contentEntity;
    }

    /**
     * 保存预取内容反馈结果<br/>
     * 保存之前将对反馈结果的md5与原md5进行比对，并将比对结果存储<br/>
     * 如果反馈的md5为null或空串，则标记为检测成功。<br/>
     * 只有当反馈的md5与用户输入的md5不匹配时，才标记为检测失败<br/>
     * Date：2018-4-25 15:08:41<br/>
     * @param contentFeedBackEntity 反馈内容对象
     * @return {ContentFeedBackEntity}
     * */
    @Override
    public ContentFeedBackEntity saveContentFeedback(ContentFeedBackEntity contentFeedBackEntity) {

        if (isSaved(contentFeedBackEntity)) {
            return contentFeedBackEntity;
        }

        ContentEntity contentEntity = contentRepository.findFirstByTaskId(contentFeedBackEntity.getTaskId());

        if (null != contentEntity) {
            List<ContentDetailEntity> contentDetailEntities =getContentDetailListByContentIdAndUrl(
                    contentEntity.getId(), contentFeedBackEntity.getUrl());

            if (null == contentDetailEntities || contentDetailEntities.isEmpty()) {
                return null;
            }

            ContentDetailEntity contentDetailEntity = contentDetailEntities.get(0);

            if (isContentExpired(contentEntity, contentDetailEntity)) {
                contentFeedBackEntity.setStatus(ContentFeedbackStatusEnum.TIMEOUT.getStatus());

            }else {
                contentFeedBackEntity = setMd5OnlyFeedbackStatus(contentFeedBackEntity, contentEntity, contentDetailEntity);
            }

            if (StringUtils.isBlank(contentFeedBackEntity.getMd5())) {
                //如果不设置空格串，插入时会被自动转换为null。查询时，空格空串会被自动trim()
                contentFeedBackEntity.setMd5(" ");
            }

            contentFeedBackEntity.setCreatedDate(new Date());
            Integer result = contentFeedbackMapper.insert(contentFeedBackEntity);
            updateContentFinishedIfAllFeedback(contentDetailEntity, contentFeedBackEntity.getTaskId());

            if (null != result && 0 < result) {
                return contentFeedBackEntity;
            }
        }

        return null;
    }

    /**
     * 获取预取（推送、md5校验）结果<br/>
     * 如果反馈的md5为null或空串，则标记为检测成功。<br/>
     * 只有当反馈的md5与用户输入的md5不匹配时，才标记为检测失败<br/>
     * Date：2018-4-25 15:10:29
     * @param contentResultRequestDto 获取结果条件
     * @return {ContentResultDto} 预取结果dto
     * */
    @Override
    public PageDto getContentResult(ContentResultRequestDto contentResultRequestDto) {
        Integer pageSize = contentResultRequestDto.getPageSize();
        Integer currPage = contentResultRequestDto.getCurrPage();
        contentResultRequestDto.setOffset(pageSize * (currPage - 1));

        List<ContentResultDto> contentResultDtos = contentFeedbackMapper.getContentFeedbackList(contentResultRequestDto);
        contentResultDtos = contentResultDtos.stream()
                .map(dto -> {
                    Long successAmount = dto.getSuccessAmount();
                    Integer serverAmount = dto.getServerAmount();

                    successAmount = null == successAmount ? 0 : successAmount;
                    serverAmount = null == serverAmount ? 0 : serverAmount;

                    if (0 == serverAmount) {
                        dto.setFitRate(0D);

                    } else {
                        dto.setFitRate(successAmount/(serverAmount * 1.00D));
                    }

                    if (ContentActionEnum.MD5_ONLY.name().equals(dto.getContentAction())) {
                        dto = getMd5OnlyContentResult(dto);
                    }

                    if (1 <= dto.getFitRate()
                            || (ContentStatusEnum.FINISHED.getStatus().equals(dto.getStatus()))
                            && 0.99D <= dto.getFitRate()) {
                        dto.setFitRate(1D);
                    }

                    return dto;

                }).collect(Collectors.toList());

        PageDto dto = new PageDto();
        dto.setList(contentResultDtos);
        dto.setTotalCount(contentFeedbackMapper.countContentFeedback(contentResultRequestDto).intValue());

        return dto;
    }

    @Scheduled(fixedRate = 1000*60*1)
    @Transactional
    public void feedbackFinishQuart () {
        Date date = new Date();
        Long time = date.getTime();
        Long fetchLastExpireDate = time - contentFeedbackFetchExpire;
        Long lastExpireDate = time - contentFeedbackExpire;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, Object> map = new HashMap<>();
        map.put("fetchLastExpireDate", simpleDateFormat.format(new Date(fetchLastExpireDate)));
        map.put("lastExpireDate", simpleDateFormat.format(new Date(lastExpireDate)));
        contentDetailMapper.updateContentDetailStatus(map);
    }

    /**
     * 定时向turkey发送任务结束反馈：5分钟执行一次<br/>
     * Date：2018-8-28 17:28:53<br/>
     * */
    @Scheduled(fixedRate = 1000*60*5)
    @Transactional
    public void responseTaskStatusToTurkeyQuartz () {
        log.info("responseTaskStatusToTurkeyQuartz:{}", System.currentTimeMillis());
        List<ContentEntity> contentEntities = contentDetailMapper.getFinishedContentList();
        log.info("finished count:{}", contentEntities.size());

        if (!contentEntities.isEmpty()) {
            List<Long> ids =  contentEntities.stream().map(content -> {
                Wrapper<ContentDetailEntity> wrapper = new EntityWrapper<>();
                wrapper.eq("content_id", content.getId());
                ContentDetailEntity contentDetailEntity = contentDetailService.selectOne(wrapper);

                SwanTaskFirstDomainRequest swanTaskFirstDomainRequest = new SwanTaskFirstDomainRequest();
                swanTaskFirstDomainRequest.setFirstDomain(contentDetailEntity.getFirstDomain());
                swanTaskFirstDomainRequest.setTaskNo(content.getTaskId());
                swanTaskFirstDomainRequest.setStatus(TASK_FINISH);

                swanTaskFirstDomainRequest = initRequestParam(swanTaskFirstDomainRequest);

                ResponseEntity<String> responseEntity =
                        restTemplate.postForEntity(swanTaskUrl, swanTaskFirstDomainRequest, String.class);

                if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                    log.info("response content result fail, task id:{}", content.getTaskId());
                    return -1L;

                }else {
                    return content.getId();
                }

            }).collect(Collectors.toList());

            contentDetailMapper.updateContentFinished(ids);
        }
    }

    /**
     * 当所有主机都feedback以后，将任务更新为结束<br/>
     * Date：2018-5-7 20:07:37<br/>
     * @param contentDetailEntity 内容详细对象，id不为空
     * @param taskId 任务id
     * */
    private void updateContentFinishedIfAllFeedback(ContentDetailEntity contentDetailEntity, String taskId) {
        contentDetailEntity = contentDetailService.selectById(contentDetailEntity.getId());
        Integer serverNumber = contentDetailEntity.getServerNumber();

        Wrapper<ContentFeedBackEntity> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("count(*)").eq("task_id", taskId)
                .and().eq("url", contentDetailEntity.getContentUrl());

        Integer feedbackCount = contentFeedbackMapper.selectCount(wrapper);

        if (!ContentStatusEnum.FINISHED.getStatus().equals(contentDetailEntity.getStatus())
                && serverNumber <= feedbackCount) {
            contentDetailEntity.setStatus(ContentStatusEnum.FINISHED.getStatus());
            contentDetailService.updateById(contentDetailEntity);
        }
    }

    /**
     * md5only校验的时候，反馈回来的内容要根据反馈内容设置反馈的结果
     * Date：2018-5-4 18:02:46
     * @param contentFeedBackEntity 校验反馈对象
     * @param contentEntity 校验关联的内容对象
     * @param contentDetailEntity 校验关联的内容详细对象
     * @return {ContentFeedBackEntity} 设置了反馈结果的反馈对象
     * */
    private ContentFeedBackEntity setMd5OnlyFeedbackStatus (
            ContentFeedBackEntity contentFeedBackEntity, ContentEntity contentEntity, ContentDetailEntity contentDetailEntity) {

        if (ContentActionEnum.MD5_ONLY.toString().equals(contentEntity.getContentAction().toString())) {
            String originalMd5 = contentDetailEntity.getMd5();
            String feedbackMd5 = contentFeedBackEntity.getMd5();

            if (StringUtils.isNotBlank(originalMd5) && StringUtils.isNotBlank(feedbackMd5)
                    && !originalMd5.toLowerCase().equals(feedbackMd5.toLowerCase())) {
                contentFeedBackEntity.setStatus(ContentFeedbackStatusEnum.FAIL.getStatus());

            }else {
                contentFeedBackEntity.setStatus(ContentFeedbackStatusEnum.SUCCESS.getStatus());
            }
        }

        return contentFeedBackEntity;
    }

    /**
     * 内容是否已经过期（30分钟过期）<br/>
     * Date：2018-5-4 17:54:31<br/>
     * @param contentEntity 内容实体，获取创建时间，并与当前时间对比
     * @param contentDetailEntity 内容详细对象
     * @return {Boolean} true表示已过期
     * */
    private Boolean isContentExpired (ContentEntity contentEntity, ContentDetailEntity contentDetailEntity) {
        long contentTime = contentEntity.getCreatedDate().getTime();
        long now = new Date().getTime();
        boolean isExpired = contentFeedbackExpire < now - contentTime;

        if (isExpired
                && !contentDetailEntity.getStatus().equals(ContentStatusEnum.FINISHED.getStatus())) {
            contentDetailEntity.setStatus(ContentStatusEnum.FINISHED.getStatus());
            contentDetailService.updateById(contentDetailEntity);
        }

        return isExpired;
    }

    /**
     * 根据contentId获取对应的ContentDetail详细列表<br/>
     * Date：2018-5-4 16:17:03<br/>
     * @param contentId 内容id
     * @param url 查询的url
     * @return {List<ContentDetailEntity>}
     * */
    private List<ContentDetailEntity> getContentDetailListByContentIdAndUrl (Long contentId, String url) {
        Wrapper<ContentDetailEntity> wrapper = new EntityWrapper();
        wrapper.eq("content_id", contentId)
                .and().eq("content_url", url);
        return contentDetailService.selectList(wrapper);
    }

    /**
     * md5校验校验时，如果用户输入的md5值为空，则要按照特殊计算规则：<br/>
     * 返回结果最多的md5总数 + md5为空的返回总数<br/>
     * Date：2018-5-4 16:25:59<br/>
     * @param contentResultDto 内容结果dto。该对象已获得
     * @return {ContentResultDto}
     * */
    private ContentResultDto getMd5OnlyContentResult(ContentResultDto contentResultDto) {
        String md5 = contentResultDto.getMd5();

        if (StringUtils.isBlank(md5)) {
            String content = contentResultDto.getContent();
            String taskId = contentResultDto.getTaskId();

            Wrapper<ContentFeedBackEntity> wrapper = new EntityWrapper<>();
            Integer successCount = 0;
            String maxCountMd5 = "";

            wrapper.setSqlSelect("md5", "count(*)").eq("task_id", taskId)
                    .and().eq("url", content)
                    .and().ne("md5", "").groupBy("md5")
                    //超时的、为空的反馈不能计入结果
                    .and().ne("status", ContentFeedbackStatusEnum.TIMEOUT.getStatus());

            List md5NotNullList = contentFeedbackMapper.selectMaps(wrapper);

            if (!md5NotNullList.isEmpty()) {
                Map<String, Object> maxMap = (Map<String, Object>) md5NotNullList.stream().max((a, b) -> {
                    Long aCount = (Long)((Map)a).get("count(*)");
                    Long bCount = (Long)((Map)b).get("count(*)");

                    if (aCount.longValue() > bCount.longValue()) {
                        return 1;
                    }

                    return -1;
                }).get();
                maxCountMd5 = (String) maxMap.get("md5");
                successCount = ((Long) maxMap.get("count(*)")).intValue();
            }

            wrapper = new EntityWrapper<>();
            wrapper.eq("task_id", taskId)
                    .and().eq("url", content)
                    .and().eq("md5", "")
                    //超时的、为空的反馈不能计入结果
                    .and().ne("status", ContentFeedbackStatusEnum.TIMEOUT.getStatus());
            Integer md5NullCount = contentFeedbackMapper.selectCount(wrapper);

            successCount += md5NullCount;
            contentResultDto.setFitRate(successCount/(contentResultDto.getServerAmount() * 1.00D));
            contentResultDto.setMd5(maxCountMd5);
        }

        return contentResultDto;
    }

    /**
     * 反馈结果是否已经被存储过了<br/>
     * Date：2018-4-25 16:16:24<br/>
     * @param contentFeedBackEntity 反馈结果
     * @param {Boolean} true表示反馈结果已经被存储过了
     * */
    private boolean isSaved (ContentFeedBackEntity contentFeedBackEntity) {

        Wrapper<ContentFeedBackEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("task_id", contentFeedBackEntity.getTaskId())
                .eq("url", contentFeedBackEntity.getUrl())
                .eq("ip", contentFeedBackEntity.getIp());

        int count = contentFeedbackMapper.selectCount(wrapper);
        return 0 < count;
    }

    /**
     * 生成SendConfigDataRequest
     * @param contentEntity
     * @return
     */
    private SendConfigDataRequest doGenerateSendConfigDataRequest(ContentEntity contentEntity, String content, KiwiContentSecondDomain kiwiContentSecondDomain) {
        ByteString contentBytes = ByteString.copyFromUtf8(content);
        SendConfigDataRequest.Builder builder = sendConfigDataRequestBuilder(contentEntity,kiwiContentSecondDomain);
        builder.setData(contentBytes).setSizeInBytes(contentBytes.size());
        return builder.build();
    }

    /**
     *  构造SendConfigDataRequest.Builder
     * @param contentEntity
     * @return
     */
    private SendConfigDataRequest.Builder sendConfigDataRequestBuilder(ContentEntity contentEntity, KiwiContentSecondDomain kiwiContentSecondDomain) {

        SendConfigDataRequest.Builder builder = SendConfigDataRequest.newBuilder();
        String fileName = "";
        if (contentEntity.getContentAction().equals(ContentActionEnum.PUSH)) {
            builder.setModuleName("xclean");
            fileName = contentEntity.getTaskId().toString()+"_"+contentEntity.getCreatedDate().getTime()/1000;
        }
        if (contentEntity.getContentAction().equals(ContentActionEnum.FETCH)) {
            builder.setModuleName("xfetch");
            fileName = contentEntity.getTaskId().toString()+"_"+contentEntity.getCreatedDate().getTime()/1000;
            if(kiwiContentSecondDomain.getIsSend()){
                fileName = String.format("%s/%s",kiwiContentSecondDomain.getSecondDomain(),fileName);
            }
        }
        if (contentEntity.getContentAction().equals(ContentActionEnum.MD5_ONLY)){
            builder.setModuleName("xfetch_md5_only");
            fileName = "md5_"+contentEntity.getTaskId().toString()+"_"+contentEntity.getCreatedDate().getTime()/1000;
            if(kiwiContentSecondDomain.getIsSend()){
                fileName = String.format("%s/%s",kiwiContentSecondDomain.getSecondDomain(),fileName);
            }
        }
        if (contentEntity.getContentAction().equals(ContentActionEnum.MD5_FETCH)){
            builder.setModuleName("xfetch_md5");
            fileName = "md5fetch_"+contentEntity.getTaskId().toString()+"_"+contentEntity.getCreatedDate().getTime()/1000;
            if(kiwiContentSecondDomain.getIsSend()){
                fileName = String.format("%s/%s",kiwiContentSecondDomain.getSecondDomain(),fileName);
            }
        }
        log.info("kiwi fileName:{}",fileName);
        builder.setFileName(fileName)
                .setSendTime(System.currentTimeMillis() / 1000);
        return builder;
    }

    /**
     * 生成推送内容
     */
    private String doGenerateContent(ContentEntity contentEntity, List<ContentDetailEntity> contentDetailEntities) {
        StringBuffer buffer = new StringBuffer();

        if(contentEntity.getContentAction() == ContentActionEnum.PUSH){
            contentDetailEntities.forEach(x->{
                // 新squid 没有支持url-expire 选项 ，只有删除推送 url-delete，统一只发送删除标记
                String operation = (x.getPushType().equals(ContentPushTypeEnum.URL.getValue()) ?
                        ContentPushTypeEnum.URL.name().toLowerCase() + "-" + ContentOperationTypeEnum.DELETE.name().toLowerCase()
                        : ContentPushTypeEnum.DIR.name().toLowerCase()+ "-" + contentEntity.getOperationType().name().toLowerCase());
                buffer.append(String.format("%s %s %s\r\n", operation, x.getContentUrl(), x.getCreatedDate().getTime()/1000));
            });
        }

        if(contentEntity.getContentAction() == ContentActionEnum.FETCH){
            contentDetailEntities.forEach(x->{
                String operation = (x.getPushType().equals(ContentPushTypeEnum.URL.getValue()) ?
                        ContentPushTypeEnum.URL.name().toLowerCase()
                        : ContentPushTypeEnum.DIR.name().toLowerCase()) + "-" + contentEntity.getOperationType().name().toLowerCase();
                buffer.append(String.format("%s %s %s\r\n", operation, x.getContentUrl(), x.getCreatedDate().getTime()/1000));
            });
        }

        if(contentEntity.getContentAction() == ContentActionEnum.MD5_ONLY){
            contentDetailEntities.forEach(x->{
                buffer.append(String.format("%s %s %s %s\r\n",
                        "md5-only", x.getContentUrl(), x.getCreatedDate().getTime()/1000
                        ,StringUtil.isBlank(x.getContentRange()) ? "-" : x.getContentRange()));
            });
        }

        if(contentEntity.getContentAction() == ContentActionEnum.MD5_FETCH){
            contentDetailEntities.forEach(x->{
                buffer.append(String.format("%s-fetch %s %s %s\r\n", x.getMd5(), x.getContentUrl(), x.getCreatedDate().getTime()/1000
                        ,StringUtil.isBlank(x.getContentRange()) ? "-" : x.getContentRange()));
            });
        }

        log.info("kiwi content buffer:{}",buffer.toString());
        return buffer.toString();
    }

    public SendConfigDataAck sendConfigData(SendConfigDataRequest request,String currentTraceId) {

        GrpcChannelProperties channelProperties = grpcChannelsProperties.getChannel("kiwi");
        Metadata metadata = new Metadata();
        Metadata.Key tokenKey = Metadata.Key.of("token", Metadata.ASCII_STRING_MARSHALLER);
        Metadata.Key traceIdKey = Metadata.Key.of("X-B3-SpanId", Metadata.ASCII_STRING_MARSHALLER);

        metadata.put(tokenKey, channelProperties.getToken());
        metadata.put(traceIdKey, currentTraceId);

        KiwiFileServerGrpc.KiwiFileServerBlockingStub kiwiStub = KiwiFileServerGrpc.newBlockingStub(kiwiChannel);
        kiwiStub = MetadataUtils.attachHeaders(kiwiStub, metadata);
        log.info("start sendConfigDataAck");
        SendConfigDataAck sendConfigDataAck = kiwiStub.sendConfigData(request);
        log.info("end sendConfigDataAck");
        return sendConfigDataAck;
    }

    @Override
    public List<ContentEntity> findByIdIn(List<Long> ids) {
        return contentRepository.findByIdIn(ids);
    }

    @Async
    @Override
    public void sendContentKiwi(ContentEntity contentEntity) {
        // 获取detail
        Long contentId = contentEntity.getId();
        String traceId = tracer.getCurrentSpan().traceIdString();
        Map<String,Object> map = new HashMap<>();
        map.put("content_id",contentId);

        List<ContentDetailEntity> contentDetailEntities = contentDetailService.selectByMap(map);
        String doGeneratecontent = doGenerateContent(contentEntity,contentDetailEntities);
        KiwiContentSecondDomain kiwiContentSecondDomain = new KiwiContentSecondDomain();
        kiwiContentSecondDomain.setIsSend(false);

        SendConfigDataRequest request = doGenerateSendConfigDataRequest(contentEntity,doGeneratecontent,kiwiContentSecondDomain);
        SendConfigDataAck ack = this.sendConfigData(request,traceId);

        if (!StringUtil.isEmpty(ack.getErr())) {
            log.info("send shield kiwi fail,kiwi-sendConfigData:{}", ack.getErr());
            contentDetailEntities = contentDetailEntities.stream().map(detail -> {
                detail.setStatus(ContentStatusEnum.FAIL.getStatus());

                return detail;
            }).collect(Collectors.toList());

            contentDetailService.updateBatchById(contentDetailEntities);
            return;
        }

        log.info("主线程执行完毕{}",Thread.currentThread().getId());

        // 二级域名策略隔三分钟再发送
        Map<String, List<ContentDetailEntity>> contentDetailMap =
                contentDetailEntities.stream().collect(groupingBy(ContentDetailEntity::getSecondDomain));

        contentDetailMap.forEach((k,v)->{
            if(StringUtils.isNotBlank(k)){
                Thread thread = new Thread(() -> {
                    try {
                        if(contentEntity.getContentAction() != ContentActionEnum.MD5_ONLY){
                            Thread.sleep(timeLater);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String content = doGenerateContent(contentEntity,v);
                    KiwiContentSecondDomain kiwiContentSecondDomainMap = new KiwiContentSecondDomain();
                    kiwiContentSecondDomainMap.setIsSend(true);
                    kiwiContentSecondDomainMap.setSecondDomain(v.get(0).getSecondDomain());
                    SendConfigDataRequest requestMap = doGenerateSendConfigDataRequest(contentEntity,content,kiwiContentSecondDomainMap);
                    SendConfigDataAck ackMap = this.sendConfigData(requestMap,traceId);

                    log.info("子线程执行完毕{}",Thread.currentThread().getId());

                    if(!StringUtil.isEmpty(ackMap.getErr())){
                        log.info("send edge kiwi fail,kiwi-sendConfigData:{}", ack.getErr());

                        List<ContentDetailEntity> detailEntities = v.stream().map(detail -> {
                            detail.setStatus(ContentStatusEnum.FAIL.getStatus());
                            return detail;
                        }).collect(Collectors.toList());

                        contentDetailService.updateBatchById(detailEntities);
                    }

                });
                thread.start();
            }
        });
    }

    @Override
    public Map<String, Object> ContentActionFtp(ContentDto contentFtpDto) {
        // 从cygnet获取所有客户一级域名
        List<EnterpriseFirstDomainResponse> enterpriseFirstDomainResponses = null;
        // 按客户创建content内容task
        List<ContentDetailDto> contentDetailDtos = contentFtpDto.getContent();

        contentDetailDtos.forEach(x->{
            enterpriseFirstDomainResponses.forEach(y->{
                if(x.getUri().split("/")[1].equals(y.getDomain())){
                    x.setCustomerDomain(y.getDomain());
                    x.setFirstDomain(y.getFirstDomain());
                    x.setEnterpriseCode(y.getEnterpriseCode());
                    x.setEnterpriseId(y.getEnterpriseId());
                    x.setFetchStrategy(y.getFetchStrategy());
                }
            });
        });

        // 获取所有不存在客户的域名
        List<ContentDetailDto> contentWithoutEnterprise = contentDetailDtos.stream().
                filter(x->StringUtils.isBlank(x.getEnterpriseCode())).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();

        if(contentWithoutEnterprise.size() == contentDetailDtos.size()){
            result.put("uris", contentWithoutEnterprise.stream().map(x->x.getUri()).collect(Collectors.joining(";")));
            return result;
        }

        // 根据一级域名获取一共有几个客户，根据action拆解任务
        Map<String, List<ContentDetailDto>> contentEnterprise =
                contentDetailDtos.stream().filter(x->StringUtils.isNotBlank(x.getEnterpriseCode())).collect(groupingBy(ContentDetailDto::getEnterpriseCode));
        // 记录所有生成的content，一并发送给kiwi
        List<ContentEntity> contentEntities = new ArrayList<>();

        if(contentFtpDto.getContentAction() == ContentActionEnum.PUSH){
            contentEnterprise.forEach((k,v)->{
                ContentEntity contentEntity = new ContentEntity();
                contentEntity.setContentAction(ContentActionEnum.PUSH);
                contentEntity.setOperationType(ContentOperationTypeEnum.DELETE);
                contentEntity.setTaskId(UUID.randomUUID().toString());
                contentEntity.setCreatedDate(new Date());
                contentEntity.setOrigin(ContentOriginEnum.FTP.getCode());
                contentEntity.setEnterpriseCode(k);
                contentEntity.setEnterpriseId(v.get(0).getEnterpriseId());
                contentEntity.setStatus(ContentStatusEnum.NOT_START.getStatus().intValue());
                contentRepository.save(contentEntity);
                contentEntities.add(contentEntity);
                List<ContentDetailEntity> contentDetailEntities = v.stream().map(x->{
                    ContentDetailEntity contentDetailEntity = new ContentDetailEntity();
                    BeanUtils.copyProperties(x,contentDetailEntity);

                    //  获取主机数量
                    SwanTaskFirstDomainRequest swanTaskFirstDomainRequest = new SwanTaskFirstDomainRequest();
                    swanTaskFirstDomainRequest.setFirstDomain(x.getFirstDomain());
                    swanTaskFirstDomainRequest.setTaskNo(contentEntity.getTaskId());
                    swanTaskFirstDomainRequest.setTaskType(contentEntity.getContentAction().toString());
                    SecondDomainDto secondDomainDto = this.loadSecondDomainDto(swanTaskFirstDomainRequest);
                    if(secondDomainDto != null){
                        contentDetailEntity.setServerNumber(secondDomainDto.getCacheNumber()+secondDomainDto.getShieldNumber());
                        contentDetailEntity.setDeviceIds(new ArrayList<>());
                        List<String> cacheList = secondDomainDto.getCacheList();
                        List<String> shieldList = secondDomainDto.getShieldList();

                        if (null != cacheList && !cacheList.isEmpty()) {
                            contentDetailEntity.getDeviceIds().addAll(cacheList);
                        }

                        if (null != shieldList && !shieldList.isEmpty()) {
                            contentDetailEntity.getDeviceIds().addAll(shieldList);
                        }
                    }
                    contentDetailEntity.setSecondDomain(" ");
                    contentDetailEntity.setPushType(x.getPushType().getValue());
                    contentDetailEntity.setStatus(ContentStatusEnum.IN_PROGRESS.getStatus());
                    contentDetailEntity.setContentId(contentEntity.getId());
                    contentDetailEntity.setContentUrl(String.format("http:/%s",x.getUri()));
                    contentDetailEntity.setCreatedDate(new Date());
                    return contentDetailEntity;
                }).collect(Collectors.toList());

                if (contentDetailService.insertBatch(contentDetailEntities)) {
                    addContentServer(contentDetailEntities);
                }
            });
        }

        if(contentFtpDto.getContentAction() == ContentActionEnum.FETCH){
            contentEnterprise.forEach((k,v)->{
                Map<Boolean,List<ContentDetailDto>> contentAction =
                        v.stream().collect(partitioningBy(e -> StringUtils.isBlank(e.getMd5())));
                contentAction.forEach((w,y)->{
                    if(y != null && !y.isEmpty()){
                        ContentEntity contentEntity = new ContentEntity();
                        contentEntity.setTaskId(UUID.randomUUID().toString());
                        contentEntity.setCreatedDate(new Date());
                        contentEntity.setOrigin(ContentOriginEnum.FTP.getCode());
                        contentEntity.setEnterpriseCode(k);
                        contentEntity.setEnterpriseId(y.get(0).getEnterpriseId());
                        contentEntity.setOperationType(ContentOperationTypeEnum.DELETE);
                        contentEntity.setStatus(ContentStatusEnum.NOT_START.getStatus().intValue());

                        if(w){
                            contentEntity.setContentAction(ContentActionEnum.FETCH);
                        }else{
                            contentEntity.setContentAction(ContentActionEnum.MD5_FETCH);
                        }

                        contentRepository.save(contentEntity);
                        contentEntities.add(contentEntity);
                        List<ContentDetailEntity> contentDetailEntities = y.stream().map(x->{
                            ContentDetailEntity contentDetailEntity = new ContentDetailEntity();
                            BeanUtils.copyProperties(x,contentDetailEntity);

                            //  获取主机数量和二级域名
                            SwanTaskFirstDomainRequest swanTaskFirstDomainRequest = new SwanTaskFirstDomainRequest();
                            swanTaskFirstDomainRequest.setFirstDomain(x.getFirstDomain());
                            swanTaskFirstDomainRequest.setTaskNo(contentEntity.getTaskId());
                            swanTaskFirstDomainRequest.setTaskType(contentEntity.getContentAction().toString());
                            SecondDomainDto secondDomainDto = this.loadSecondDomainDto(swanTaskFirstDomainRequest);
                            if(secondDomainDto != null){
                                contentDetailEntity.setDeviceIds(new ArrayList<>());
                                List<String> cacheList = secondDomainDto.getCacheList();
                                List<String> shieldList = secondDomainDto.getShieldList();

                                if (null != shieldList && !shieldList.isEmpty()) {
                                    contentDetailEntity.getDeviceIds().addAll(shieldList);
                                }

                                if(x.getFetchStrategy() == FetchStrategyEnum.SECONDDOMAIN.getCode()){
                                    contentDetailEntity.setSecondDomain(secondDomainDto.getSecondDomain());
                                    contentDetailEntity.setServerNumber(secondDomainDto.getCacheNumber()+secondDomainDto.getShieldNumber());

                                    if (null != cacheList && !cacheList.isEmpty()) {
                                        contentDetailEntity.getDeviceIds().addAll(cacheList);
                                    }

                                }else if(x.getFetchStrategy() == FetchStrategyEnum.STATIC.getCode()){
                                    contentDetailEntity.setSecondDomain(" ");
                                    contentDetailEntity.setServerNumber(secondDomainDto.getShieldNumber());
                                }
                            }
                            contentDetailEntity.setStatus(ContentStatusEnum.IN_PROGRESS.getStatus());
                            contentDetailEntity.setPushType(x.getPushType().getValue());
                            contentDetailEntity.setContentId(contentEntity.getId());
                            contentDetailEntity.setContentUrl(String.format("http:/%s",x.getUri()));
                            contentDetailEntity.setCreatedDate(new Date());
                            return contentDetailEntity;
                        }).collect(Collectors.toList());

                        if (contentDetailService.insertBatch(contentDetailEntities)) {
                            addContentServer(contentDetailEntities);
                        }
                    }
                });
            });
        }

        result.put("contentEntities", contentEntities);
        result.put("uris", contentWithoutEnterprise.stream().map(x->x.getUri()).collect(Collectors.joining(";")));
        return result;
    }

    @Override
    public List<ContentEntity> cdnContentFetch(CdnContentFetchDto cdnContentFetchDto) {
        // 记录所有生成的content，一并发送给kiwi
        List<ContentEntity> contentEntities = new ArrayList<>();
        Map<Boolean,List<ContentDetailDto>> contentAction =
                cdnContentFetchDto.getContent().stream().collect(partitioningBy(e -> StringUtils.isBlank(e.getMd5())));
        contentAction.forEach((w,y)->{
            if(y != null && !y.isEmpty()){
                ContentEntity contentEntity = new ContentEntity();
                contentEntity.setTaskId(UUID.randomUUID().toString());
                contentEntity.setCreatedDate(new Date());
                contentEntity.setOrigin(ContentOriginEnum.OPENAPI.getCode());
                contentEntity.setEnterpriseCode(cdnContentFetchDto.getEnterpriseCode());
                contentEntity.setOperationType(ContentOperationTypeEnum.DELETE);
                contentEntity.setStatus(ContentStatusEnum.NOT_START.getStatus().intValue());

                if(w){
                    contentEntity.setContentAction(ContentActionEnum.FETCH);
                }else{
                    contentEntity.setContentAction(ContentActionEnum.MD5_FETCH);
                }

                contentRepository.save(contentEntity);
                List<ContentDetailEntity> contentDetailEntities = y.stream().map(x->{
                    ContentDetailEntity contentDetailEntity = new ContentDetailEntity();
//                    EnterpriseFirstDomainResponse firstDomain = cygnetApi.findFirstRootDomainByDomain(getDomainFromUrl(x.getUri())).getData();
//                    contentEntity.setEnterpriseId(firstDomain.getEnterpriseId());
//                    contentDetailEntity.setCustomerDomain(firstDomain.getDomain());
//                    contentDetailEntity.setFirstDomain(firstDomain.getFirstDomain());
//                    //  获取主机数量和二级域名
//                    SwanTaskFirstDomainRequest swanTaskFirstDomainRequest = new SwanTaskFirstDomainRequest();
//                    swanTaskFirstDomainRequest.setFirstDomain(firstDomain.getFirstDomain());
//                    swanTaskFirstDomainRequest.setTaskNo(contentEntity.getTaskId());
//                    swanTaskFirstDomainRequest.setTaskType(contentEntity.getContentAction().toString());
//                    SecondDomainDto secondDomainDto = this.loadSecondDomainDto(swanTaskFirstDomainRequest);
//
//                    contentDetailEntity.setDeviceIds(new ArrayList<>());
//                    List<String> cacheList = secondDomainDto.getCacheList();
//                    List<String> shieldList = secondDomainDto.getShieldList();
//
//                    if (null != shieldList && !shieldList.isEmpty()) {
//                        contentDetailEntity.getDeviceIds().addAll(shieldList);
//                    }
//
//                    if(firstDomain.getFetchStrategy() == FetchStrategyEnum.SECONDDOMAIN.getCode()){
//                        contentDetailEntity.setSecondDomain(secondDomainDto.getSecondDomain());
//                        contentDetailEntity.setServerNumber(secondDomainDto.getCacheNumber()+secondDomainDto.getShieldNumber());
//
//                        if (null != cacheList && !cacheList.isEmpty()) {
//                            contentDetailEntity.getDeviceIds().addAll(cacheList);
//                        }
//
//                    }else if(firstDomain.getFetchStrategy() == FetchStrategyEnum.STATIC.getCode()){
//                        contentDetailEntity.setSecondDomain(" ");
//                        contentDetailEntity.setServerNumber(secondDomainDto.getShieldNumber());
//                    }
//                    contentDetailEntity.setMd5(x.getMd5());
//                    contentDetailEntity.setStatus(ContentStatusEnum.IN_PROGRESS.getStatus());
//                    contentDetailEntity.setPushType(ContentPushTypeEnum.URL.getValue());
//                    contentDetailEntity.setContentId(contentEntity.getId());
//                    contentDetailEntity.setContentUrl(x.getUri());
//                    contentDetailEntity.setCreatedDate(new Date());
                    return contentDetailEntity;
                }).collect(Collectors.toList());
                contentRepository.save(contentEntity);
                contentEntities.add(contentEntity);

                if (contentDetailService.insertBatch(contentDetailEntities)) {
                    addContentServer(contentDetailEntities);
                }
            }
        });

        return contentEntities;
    }

    @Override
    public ContentEntity cdnContentPush(CdnContentPushDto cdnContentPushDto) {

        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setContentAction(ContentActionEnum.PUSH);
        contentEntity.setOperationType(cdnContentPushDto.getOperationtype());
        contentEntity.setOrigin(ContentOriginEnum.OPENAPI.getCode());
        contentEntity.setCreatedDate(new Date());
        contentEntity.setEnterpriseCode(cdnContentPushDto.getEnterpriseCode());
        contentEntity.setTaskId(UUID.randomUUID().toString());
        contentEntity.setStatus(ContentStatusEnum.NOT_START.getStatus().intValue());
        contentRepository.save(contentEntity);

        List<ContentDetailDto> contentDetailDtos = cdnContentPushDto.getContent();
        List<ContentDetailEntity> contentDetailEntities = contentDetailDtos.stream().map(x->{
            ContentDetailEntity contentDetailEntity = new ContentDetailEntity();
            contentDetailEntity.setPushType(x.getPushType().getValue());
            contentDetailEntity.setContentUrl(x.getUri());
            contentDetailEntity.setContentId(contentEntity.getId());
            contentDetailEntity.setStatus(ContentStatusEnum.IN_PROGRESS.getStatus());
            contentDetailEntity.setCreatedDate(new Date());
//            EnterpriseFirstDomainResponse firstDomain = cygnetApi.findFirstRootDomainByDomain(getDomainFromUrl(x.getUri())).getData();
//            contentEntity.setEnterpriseId(firstDomain.getEnterpriseId());
//            contentDetailEntity.setCustomerDomain(firstDomain.getDomain());
//            contentDetailEntity.setFirstDomain(firstDomain.getFirstDomain());
            //  获取主机数量和二级域名
            SwanTaskFirstDomainRequest swanTaskFirstDomainRequest = new SwanTaskFirstDomainRequest();
//            swanTaskFirstDomainRequest.setFirstDomain(firstDomain.getFirstDomain());
            swanTaskFirstDomainRequest.setTaskNo(contentEntity.getTaskId());
            swanTaskFirstDomainRequest.setTaskType(contentEntity.getContentAction().toString());
            SecondDomainDto secondDomainDto = this.loadSecondDomainDto(swanTaskFirstDomainRequest);
            contentDetailEntity.setSecondDomain(" ");
            contentDetailEntity.setServerNumber(secondDomainDto.getCacheNumber()+secondDomainDto.getShieldNumber());

            contentDetailEntity.setDeviceIds(new ArrayList<>());
            List<String> cacheList = secondDomainDto.getCacheList();
            List<String> shieldList = secondDomainDto.getShieldList();

            if (null != shieldList && !shieldList.isEmpty()) {
                contentDetailEntity.getDeviceIds().addAll(shieldList);
            }

            if (null != cacheList && !cacheList.isEmpty()) {
                contentDetailEntity.getDeviceIds().addAll(cacheList);
            }

            return contentDetailEntity;
        }).collect(Collectors.toList());
        contentRepository.save(contentEntity);

        if (contentDetailService.insertBatch(contentDetailEntities)) {
            addContentServer(contentDetailEntities);
        }

        return contentEntity;
    }

    @Override
    public SecondDomainDto loadSecondDomainDto(SwanTaskFirstDomainRequest swanTaskFirstDomainRequest) {

        swanTaskFirstDomainRequest.setStatus("wait");
        swanTaskFirstDomainRequest = initRequestParam(swanTaskFirstDomainRequest);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(swanTaskUrl, swanTaskFirstDomainRequest, String.class);
        SwanTaskFirstDomainResponse swanTaskFirstDomainResponse = JSON.parseObject(responseEntity.getBody(),SwanTaskFirstDomainResponse.class);
        SecondDomainDto secondDomainDto = swanTaskFirstDomainResponse.getData();

        log.info("swan-->task-->response-->taskId:{},firstDomain:{},cacheNumber:{},shieldNumber:{},cacheList:{},shieldList:{}",
                swanTaskFirstDomainRequest.getTaskNo(),swanTaskFirstDomainRequest.getFirstDomain()
                ,secondDomainDto.getCacheNumber(),secondDomainDto.getShieldNumber()
                ,Arrays.toString(secondDomainDto.getCacheList().toArray())
                ,Arrays.toString(secondDomainDto.getShieldList().toArray()));

        return secondDomainDto;
    }

    /**
     * 批量添加内容操作关联的服务器<br/>
     * Date：2018-8-28 14:33:11<br/>
     * @param contentDetailEntities 内容操作详细列表
     * */
    private Boolean addContentServer (List<ContentDetailEntity> contentDetailEntities) {
        if (null == contentDetailEntities || contentDetailEntities.isEmpty()) {
            log.info("contentDetailEntities is empty");
            return true;
        }

        List<List<ContentServerEntity>> contentServerEntitiesList = contentDetailEntities.stream().map(detail -> {
            List<String> deviceIds = detail.getDeviceIds();
            Long contentDetailId = detail.getId();

            return deviceIds.stream().map(deviceId -> {
                ContentServerEntity contentServerEntity = new ContentServerEntity();
                contentServerEntity.setDeviceId(deviceId);
                contentServerEntity.setContentDetailId(contentDetailId);

                return contentServerEntity;
            }).collect(Collectors.toList());

        }).collect(Collectors.toList());

        List<ContentServerEntity> contentServerEntities = new ArrayList<>();

        contentServerEntitiesList.forEach(list -> {
            contentServerEntities.addAll(list);
        });

        if (!contentServerEntities.isEmpty()) {
            return contentServerService.insertBatch(contentServerEntities);
        }

        log.info("contentServerEntities is empty");
        return true;
    }

    /**
     * 初始化访问turkey的签名<br/>
     * Date：2018-8-28 21:15:25<br/>
     * @param swanTaskFirstDomainRequest 请求参数入参
     * @return {SwanTaskFirstDomainRequest} 初始化以后的参数
     * */
    private SwanTaskFirstDomainRequest initRequestParam (SwanTaskFirstDomainRequest swanTaskFirstDomainRequest) {
        swanTaskFirstDomainRequest.setStamp(System.currentTimeMillis()/1000);
        // 计算签名，先写死，后续做切面优化
        String value = swanTaskFirstDomainRequest.getFirstDomain()+swanTaskFirstDomainRequest.getStamp()
                +swanTaskFirstDomainRequest.getStatus()+swanTaskFirstDomainRequest.getTaskNo()+token;

        String sign = MD5Util.getMD5(value).toLowerCase();
        swanTaskFirstDomainRequest.setSign(sign);

        log.info("swan task sign:{}",sign);
        log.info("swan task firstDomain request:{}",JSON.toJSONString(swanTaskFirstDomainRequest));

        return swanTaskFirstDomainRequest;
    }

    /**
     * 根据url切分出域名<br/>
     * Date：2018-8-29 15:15:18<br/>
     * @param url 要处理的url
     * @return {String} 切分获取到的域名
     * */
    private String getDomainFromUrl (String url) {
        log.info("targe url is:{}", url);

        if (StringUtils.isBlank(url)) {
            log.info("url is blank");
            return null;
        }

        String domain = url.split("//")[1].split("/")[0].split(":")[0];
        log.info("the domain is:{}", domain);

        return domain;
    }

//    /**
//     *  object转map
//     */
//    private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
//        Map<String, Object> map = new HashMap<>();
//        Class<?> clazz = obj.getClass();
//        for (Field field : clazz.getDeclaredFields()) {
//            field.setAccessible(true);
//            String fieldName = field.getName();
//            Object value = field.get(obj);
//            map.put(fieldName, value);
//         }
//        return map;
//    }
//
//    public static void main(String[] args) throws IllegalAccessException{
//        SwanTaskFirstDomainRequest swanTaskFirstDomainRequest = new SwanTaskFirstDomainRequest();
//        Map<String,Object> map = objectToMap(swanTaskFirstDomainRequest);
//        //String[] key = new String[]{"taskNo","status","firstDomain"};
//        //domains={1}&from={2}&to={3}&time_granularity={4}&stamp={5}
//        List<String> list = new ArrayList<>();
//        map.forEach((k,v)->{
//            if(!k.equals("sign")){
//                list.add(k);
//            }
//        });
//        String[] key = list.toArray(new String[list.size()]);
//        Arrays.sort(key);
//        System.out.println(key[0]+key[1]+key[2]);
//    }
}
