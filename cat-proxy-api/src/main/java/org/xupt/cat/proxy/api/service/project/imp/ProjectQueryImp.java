package org.xupt.cat.proxy.api.service.project.imp;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xupt.cat.proxy.api.constant.CatConstant;
import org.xupt.cat.proxy.api.domain.Project;
import org.xupt.cat.proxy.api.domain.dto.CatDTO;
import org.xupt.cat.proxy.api.domain.responses.BaseResponse;
import org.xupt.cat.proxy.api.domain.responses.project.ProjectResponse;
import org.xupt.cat.proxy.api.enums.ErrorCode;
import org.xupt.cat.proxy.api.service.project.IProjectQuery;
import org.xupt.cat.proxy.api.utils.DateUtil;
import org.xupt.cat.proxy.api.utils.HttpProxyUtil;
import org.xupt.cat.proxy.api.utils.JsonUtil;
import org.xupt.cat.proxy.api.utils.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lining
 * @data 2020-04-11 下午2:07
 */
@Service
@Slf4j
public class ProjectQueryImp implements IProjectQuery {

    @Override
    public BaseResponse queryAllProject() {

        Document document = null;
        try {
            document = HttpProxyUtil.sendGetHttp(CatConstant.QUERY_PROJECT_URI,
                    JsonUtil.toMap(covert()), null);
        } catch (Exception e) {
            log.error("Query all project all error! e :{}", e);
        }

        if (Objects.isNull(document)) {
            return ResponseUtil.buildFailResponce(ErrorCode.CAT_RESPONSE_EMPTY);
        }
        ProjectResponse projectResponse = covertProject(document);
        return ResponseUtil.buildSuccessResponce(projectResponse);
    }

    private CatDTO covert() {
        CatDTO catDTO = new CatDTO();
        catDTO.setIp("All");
        catDTO.setDomain("cat");
        catDTO.setReportType("day");
        catDTO.setOp(CatConstant.OP_VIEW);
        catDTO.setDate(DateUtil.nowYYYYMMDDHH());
        return catDTO;
    }

    private ProjectResponse covertProject(Document document) {
        log.info("start covert ProjectResponse.");

        List<Project> projectList = new ArrayList<>();

        Elements elements = document.getElementById("project").getElementsByTag("a");
        for (Element element : elements) {
            Project project = new Project();
            String domain = element.text().replaceAll("\\[|\\]", "").trim();
            project.setDomain(domain);
            project.setDepartment(element.attr("depart"));
            project.setProductLine(element.attr("line"));

            projectList.add(project);
        }

        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setProjectList(projectList);
        log.info("end covert ProjectResponse.");
        return projectResponse;
    }
}
