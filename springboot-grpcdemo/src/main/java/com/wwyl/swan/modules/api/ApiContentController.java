package com.wwyl.swan.modules.api;

import com.wwyl.lark.core.code.CommonReturnCode;
import com.wwyl.lark.core.model.EnterpriseUserLoginInfo;
import com.wwyl.lark.core.model.ResultBean;
import com.wwyl.lark.util.constant.GlobalConstant;
import com.wwyl.lark.web.model.PageDto;
import com.wwyl.swan.common.code.SwanReturnCode;
import com.wwyl.swan.modules.content.model.ContentEntity;
import com.wwyl.swan.modules.content.model.ContentPushTypeEnum;
import com.wwyl.swan.modules.content.model.ContentResultRequestDto;
import com.wwyl.swan.modules.content.service.ContentService;
import com.wwyl.swan.modules.resource.model.entity.DomainEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 02/12/2017
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@RestController
@RequestMapping( value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE )
public class ApiContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/content/result")
    public ResultBean<PageDto> getContentResult (@RequestBody ContentResultRequestDto contentResultRequestDto,
                                                 HttpServletRequest request) {
        ResultBean<PageDto> resultBean = new ResultBean<>();
        EnterpriseUserLoginInfo loginInfo =
                (EnterpriseUserLoginInfo)request.getAttribute(GlobalConstant.ENTERPRISEUSER_LOGIN_INFO);
        contentResultRequestDto.setEcode (loginInfo.getEcode());

        PageDto contentResultDto = contentService.getContentResult(contentResultRequestDto);

        if (null != contentResultDto) {
            resultBean.setData(contentResultDto);
            resultBean.setCode(CommonReturnCode.SUCCESS.getCode());

        }else {
            resultBean.setCode(CommonReturnCode.REQUEST_DATA_NOTEXISTS.getCode());
        }

        return resultBean;
    }

    @PostMapping(value = "/content")
    public ResultBean<Long> content(HttpServletRequest request, @RequestBody ContentEntity contentEntity) {

        ResultBean<Long> result = ResultBean.Success();
        EnterpriseUserLoginInfo loginInfo = (EnterpriseUserLoginInfo)request.getAttribute(GlobalConstant.ENTERPRISEUSER_LOGIN_INFO);
        List<DomainEntity> domainList = null;//networkResourceService.findDomainListByEnterpriseId(loginInfo.getEnterpriseId());
        Set<String> domains = domainList.stream().map(x -> x.getName()).collect(Collectors.toSet());
        // 不属于客户域名
        List<String> notBelongedDomainsList = new ArrayList<>();
        // 异常域名
        List<String> exceptionDomainsList = new ArrayList<>();
        List<String> contents = contentEntity.getContents();
        ContentPushTypeEnum pushType = contentEntity.getPushType();

        for(String inputDomain : contents){
            String inputDomainHost = null;

            //如果推送的是目录，结尾必须是/
            if (pushType.equals(ContentPushTypeEnum.DIR) &&
                    !inputDomain.substring(inputDomain.length() - 1).equalsIgnoreCase("/")) {
                exceptionDomainsList.add(inputDomain);
                continue;
            }

            try {
                inputDomainHost = new URL(inputDomain).getHost();

            } catch (MalformedURLException e) {
                log.error("fail inputDomain{}",inputDomainHost,e);
                exceptionDomainsList.add(inputDomain);
                continue;
            }

            if (domains.contains(inputDomainHost)) {
                continue ;
            }

            notBelongedDomainsList.add(inputDomain);
        }

        String exceptionDomains = exceptionDomainsList.stream().collect(Collectors.joining(";")).toString();

        if (!StringUtils.isEmpty(exceptionDomains)) {
            result.setCode(SwanReturnCode.EXCEPTION_DOMAINS.toString());
            result.setMsg(exceptionDomains);
            return result;
        }

        String combined = notBelongedDomainsList.stream().collect(Collectors.joining(";")).toString();

        if (!StringUtils.isEmpty(combined)) {
            result.setCode(SwanReturnCode.DOMAIN_NOT_BELONG.toString());
            result.setMsg(combined);
            return result;
        }

        contentEntity.setEnterpriseId(loginInfo.getEnterpriseId());
        contentEntity.setEnterpriseCode(loginInfo.getEcode());
        ContentEntity savedContent = contentService.save(contentEntity);

        if (null != savedContent) {
            contentService.sendContentKiwi(contentEntity);
            result.setData(savedContent.getId());

        }else {
            result.setCode(CommonReturnCode.SAVE_DATA_FAIL.getCode());
        }

        return result;
    }

    @PostMapping(value = "/content/fetchMD5")
    public ResultBean<Long> contentMD5(HttpServletRequest request, @RequestBody ContentEntity contentEntity) {
        ResultBean<Long> result = ResultBean.Success();
        EnterpriseUserLoginInfo loginInfo = (EnterpriseUserLoginInfo)request.getAttribute(GlobalConstant.ENTERPRISEUSER_LOGIN_INFO);
        List<DomainEntity> domainList = null; //networkResourceService.findDomainListByEnterpriseId(loginInfo.getEnterpriseId());
        Set<String> domains = domainList.stream().map(x -> x.getName()).collect(Collectors.toSet());
        String inputDomain = contentEntity.getContents().get(0);
        String inputDomainHost = null;

        try {
            inputDomainHost = new URL(inputDomain).getHost();

        } catch (MalformedURLException e) {
            result.setCode(SwanReturnCode.EXCEPTION_DOMAINS.toString());
            result.setMsg(inputDomain);
            return result;
        }

        if(!domains.contains(inputDomainHost)){
            result.setCode(SwanReturnCode.DOMAIN_NOT_BELONG.toString());
            result.setMsg(inputDomain);
            return result;
        }

        contentEntity.setEnterpriseId(loginInfo.getEnterpriseId());
        contentEntity.setEnterpriseCode(loginInfo.getEcode());
        ContentEntity savedContent = contentService.save(contentEntity);

        if (null != savedContent) {
            contentService.sendContentKiwi(contentEntity);
            result.setData(savedContent.getId());

        }else {
            result.setCode(CommonReturnCode.SAVE_DATA_FAIL.getCode());
        }

        return result;
    }

//    @GetMapping(value = "/content/test")
//    public ResultBean test() {
//        ResultBean<Long> result = ResultBean.Success();
//        List<Long> ids = new ArrayList<>();
//        ids.add(1l);
//        ids.add(2l);
//        ids.add(3l);
//        ids.add(4l);
//        List<ContentEntity> contentEntities = contentService.findByIdIn(ids);
//        for(ContentEntity contentEntity : contentEntities){
//            contentService.sendContentKiwi(contentEntity);
//        }
//        return result;
//    }
}
