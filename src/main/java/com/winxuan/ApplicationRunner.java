package com.winxuan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author leitao.
 * @category
 * @time: 2019/7/23 0023-12:26
 * @version: 1.0
 * @description:
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.winxuan")
public class ApplicationRunner {

    private static final Log LOG = LogFactory.getLog(ApplicationRunner.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
        LOG.info("====================== Spring data ES Simple 启动成功!======================");
    }

}
