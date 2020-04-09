package org.xupt.cat.proxy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xupt.cat.proxy.api.constant.SystemConstant;

@SpringBootApplication
public class CatProxyApiApplication {

    public static void main(String[] args) {
        //设置端口等信息
        SystemConstant.parseParam(args);
        SpringApplication.run(CatProxyApiApplication.class, args);
    }

}
