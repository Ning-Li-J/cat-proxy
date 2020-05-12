package org.xupt.cat.proxy.api.controller.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.xupt.cat.proxy.api.constant.SystemConstant;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lining
 * @data 2019/12/31
 */
@Configuration
@Slf4j
public class TomcatConfig {
    @Bean
    public WebServerFactoryCustomizer tomcatCustomizer() {
        return container -> {
            //对Tomcat服务器修改
            if (container instanceof TomcatServletWebServerFactory) {
                TomcatServletWebServerFactory serverFactory = (TomcatServletWebServerFactory) container;
                //设置线程池模型
                serverFactory.addConnectorCustomizers(new Config(SystemConstant.PORT));
                //设置上下文路径
                serverFactory.setContextPath(SystemConstant.CONTEXT_PATH);
                //使用Nio模型
                serverFactory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
            }
        };
    }

    private static class Config implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
        private volatile Connector connector;

        int serverPort;

        public Config(int serverPort) {
            this.serverPort = serverPort;
        }

        @Override
        public void customize(Connector connector) {
            this.connector = connector;
            //设置Nio模型线程池的参数
            Http11Nio2Protocol protocol = (Http11Nio2Protocol) connector.getProtocolHandler();
            connector.setPort(serverPort);
            connector.setEnableLookups(false);
            connector.setProperty("acceptCount", "200");
            connector.setURIEncoding("UTF-8");

            protocol.setProperty("bufferPoolSize", "-1");
            protocol.setMaxConnections(100);
            protocol.setConnectionTimeout(10000);
            protocol.setDisableUploadTimeout(true);
            protocol.setCompression("on");
            protocol.setCompressionMinSize(860);
            protocol.setNoCompressionUserAgents("gozilla, traviata");
            protocol.setMaxThreads(200);
            protocol.setMinSpareThreads(10);
            protocol.setKeepAliveTimeout(3000);
            protocol.setMaxKeepAliveRequests(1000 * 60 * 60);
        }

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            try {
                this.connector.pause();
                Executor executor = this.connector.getProtocolHandler().getExecutor();
                if (executor instanceof ThreadPoolExecutor) {
                    try {
                        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                        threadPoolExecutor.shutdown();
                        if (!threadPoolExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
                            log.warn("Tomcat thread pool did not shut down gracefully within "
                                    + "30 seconds. Proceeding with forceful shutdown");
                        }
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            } catch (Exception e) {
                log.warn("Shutdown Exception {}", e.getMessage());
            }
        }
    }
}
