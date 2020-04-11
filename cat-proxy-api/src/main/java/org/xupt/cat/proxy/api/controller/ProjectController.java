package org.xupt.cat.proxy.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.service.project.IProjectQuery;
import org.xupt.cat.proxy.api.utils.JsonUtil;

/**
 * @author lining
 * @data 2020-04-11 下午2:01
 */
@RequestMapping("/project")
@RestController
@Slf4j
public class ProjectController {

    @Autowired
    private IProjectQuery projectQuery;

    @RequestMapping(value = "/allDomain", method = RequestMethod.GET)
    public BaseResponse queryAllProject() {
        log.info("query all project.");
        BaseResponse response = projectQuery.queryAllProject();
        log.info("query all project. response :{}", JsonUtil.toJson(response));
        return response;
    }
}
