package com.psc.distributedprimarykeyservice.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lixiang
 * @date 2020/9/10 10:45
 * @desc
 */
@ConfigurationProperties(prefix = "generate")
@Data
public class Generate {
    private String worker;
    private String workerId;
    private String datacenterId;
}
