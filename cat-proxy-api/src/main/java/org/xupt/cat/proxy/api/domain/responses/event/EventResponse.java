package org.xupt.cat.proxy.api.domain.responses.event;

import lombok.Data;

import java.util.List;

/**
 * @author lining
 * @data 2020-04-08 下午12:50
 */
@Data
public class EventResponse {

    private List<Event> eventList;

    @Data
    public static class Event {
        private String name;

        private String total;

        private String failure;

        private String qps;
    }
}
