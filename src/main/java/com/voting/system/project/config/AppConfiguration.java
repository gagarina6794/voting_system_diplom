package com.voting.system.project.config;

import lombok.extern.log4j.Log4j2;
import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Log4j2
@Configuration
public class AppConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    //https://habr.com/ru/post/462541/
    @ConditionalOnProperty(value = "vote.h2.tcp_server.enable", havingValue = "true")
    public Server h2Server() throws SQLException {
        log.info("Start H2 TCP server");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
