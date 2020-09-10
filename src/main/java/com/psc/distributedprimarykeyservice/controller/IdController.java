package com.psc.distributedprimarykeyservice.controller;

import com.psc.distributedprimarykeyservice.entity.Generate;
import com.psc.distributedprimarykeyservice.entity.ID;
import com.psc.distributedprimarykeyservice.entity.MakeID;
import com.psc.distributedprimarykeyservice.service.IOrderService;
import com.psc.distributedprimarykeyservice.service.IdService;
import com.psc.distributedprimarykeyservice.util.SnowflakeIdWorker;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
/**
 * @author lixiang
 * @date 2020/9/10 9:39
 * @desc
 */


@Api(value = "/")
@RestController
@Slf4j
public class IdController {
    @Resource
    private IdService idService;
    @Resource
    private Generate generate;
    @Autowired
    @Qualifier("redisOrderServiceImpl")
    private IOrderService redisService;
    @Autowired
    @Qualifier("uuidOrderServiceImpl")
    private IOrderService uuidService;

    @GetMapping(value = "/id")
    @ApiOperation(value = "生成ID", httpMethod = "GET", notes = "成功返回ID", response = Long.class)
    public Long genId() {
        return idService.genId();
    }

    @GetMapping("/id/{id:[0-9]*}")
    @ApiOperation(
            value = "对ID进行解析",
            httpMethod = "GET",
            notes = "成功返回解析后的ID（json格式）",
            response = ID.class)
    public ID explainId(@ApiParam(value = "要解析的ID", required = true) @PathVariable("id") Long id) {
        log.info("id is {}", id);
        return idService.expId(id);
    }

    @GetMapping("/time/{time:[0-9]*}")
    @ApiOperation(
            value = "对时间戳进行解析",
            httpMethod = "GET",
            notes = "成功返回yyyy-MM-dd HH:mm:ss格式的日期时间",
            response = String.class)
    public String transTime(
            @ApiParam(value = "要解析的时间戳", required = true) @PathVariable("time") Long time) {
        log.info("time is {}", time);
        return DateFormatUtils.format(idService.transTime(time), "yyyy-MM-dd HH:mm:ss");
    }

    @PostMapping("/id")
    @ApiOperation(value = "传入相应参数生成ID", httpMethod = "POST", notes = "成功返回ID", response = Long.class)
    public Long makeId(
            @ApiParam(value = "传入的相应参数（json格式）", required = true) @RequestBody MakeID makeID) {
        Long worker = makeID.getMachine();
        Long time = makeID.getTime();
        Long sequence = makeID.getSeq();
        log.info("worker is {}", worker);
        log.info("time is {}", time);
        log.info("sequence is {}", sequence);

        if (time == -1 || sequence == -1) {
            throw new IllegalArgumentException("Both time and sequence are required.");
        }

        return worker == -1
                ? idService.makeId(time, sequence)
                : idService.makeId(time, worker, sequence);
    }

    @GetMapping(value = "/snowId")
    @ApiOperation(value = "原版雪花算法生成ID", httpMethod = "GET", notes = "成功返回ID", response = Long.class)
    public Long genSnowId() {
        log.info("worker workerId is :{}， datacenterId is :{}", generate.getWorkerId(), generate.getDatacenterId());
        Long workerId = Long.parseLong(generate.getWorkerId());
        Long datacenterId = Long.parseLong(generate.getDatacenterId());
        SnowflakeIdWorker SnowflakeIdWorker = new SnowflakeIdWorker(workerId, datacenterId);
        return SnowflakeIdWorker.nextId();
    }

    @GetMapping(value = "/redisGenId")
    @ApiOperation(value = "生成redis自增ID", httpMethod = "GET", notes = "成功返回ID", response = String.class)
    public String redisGenId() {

        return redisService.getOrderId();
    }

    @GetMapping(value = "/UUId")
    @ApiOperation(value = "生成UUID", httpMethod = "GET", notes = "成功返回ID", response = String.class)
    public String UUId() {

        return uuidService.getOrderId();
    }
}
