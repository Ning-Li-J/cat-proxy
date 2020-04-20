package org.xupt.cat.proxy.api.domain.responses.project;

import lombok.Data;
import org.xupt.cat.proxy.api.domain.Project;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-11 下午2:07
 */
@Data
public class ProjectResponse {

    private List<Project> projectList;
}
