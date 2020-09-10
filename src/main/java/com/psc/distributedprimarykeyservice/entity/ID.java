package com.psc.distributedprimarykeyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lixiang
 * @date 2020/9/10 9:46
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ID implements Serializable {
    private long timeStamp;
    private long worker;
    private long sequence;
}
