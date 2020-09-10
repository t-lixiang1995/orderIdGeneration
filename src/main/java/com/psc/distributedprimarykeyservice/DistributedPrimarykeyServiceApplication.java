package com.psc.distributedprimarykeyservice;

import com.psc.distributedprimarykeyservice.entity.Generate;
import com.psc.distributedprimarykeyservice.service.IdService;
import com.psc.distributedprimarykeyservice.service.impl.IdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
@EnableConfigurationProperties(Generate.class)
@Slf4j
public class DistributedPrimarykeyServiceApplication {

    @Resource
    private Generate generate;

    public static void main(String[] args) {
        SpringApplication.run(DistributedPrimarykeyServiceApplication.class, args);
    }

    @Bean(name = "idService")
    public IdService idService() {
        log.info("worker id is :{}", generate.getWorker());
        return new IdServiceImpl(Long.parseLong(generate.getWorker()));
    }

}
