package com.wwyl.swan.modules.resource.service;

import com.wwyl.lark.core.model.ResultBean;
import com.wwyl.swan.modules.content.model.EnterpriseFirstDomainResponse;
import com.wwyl.swan.modules.resource.model.entity.DomainEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(serviceId = "cygnet")
public interface CygnetApi {

    @GetMapping(value = "/v1/domain/open/enterpriseAndProductId")
    ResultBean<List<DomainEntity>> loadDomainListByEnterpriseAndProductId(
            @RequestParam("enterpriseId") Long enterpriseId, @RequestParam("productLineId") Long productLineId);

    @GetMapping(value = "/v1/domain/open/enterprise")
    ResultBean<List<DomainEntity>> loadDomainListByEnterprise(@RequestParam("enterpriseId") Long enterpriseId);

    /**
     *  获取所有域名，包含客户信息，提供给swan内容推送预取用
     */
    @GetMapping(value="/v1/open/enterprise/firstdomain")
    ResultBean<List<EnterpriseFirstDomainResponse>> loadEnterpriseFirstDomainList();

    /**
     *  根据客户域名返回一级根域名
     */
    @RequestMapping(value="/v1/open/single/firstdomain",method = RequestMethod.GET)
    ResultBean<EnterpriseFirstDomainResponse> findFirstRootDomainByDomain(@RequestParam("domain") String domain);
}
