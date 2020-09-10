package com.psc.distributedprimarykeyservice;

import com.psc.distributedprimarykeyservice.util.MacTools;
import com.psc.distributedprimarykeyservice.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author lixiang
 * @date 2020/9/10 11:12
 * @desc
 */
@Slf4j
public class SnowflakeIdWorkerTest {

    @Test
    public void stressTest() throws Exception {
        loop(50000000);
        loop(50000000);
        loop(50000000);
    }

    private void loop(int idNum) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < idNum; i++) {
            long id = idWorker.nextId();
            // log.info("{}", id);
        }
        long duration = System.currentTimeMillis() - start;
        log.info("total time:{}ms,speed is:{}/ms", duration, idNum / duration);
    }


    @Test
    public void getLocalMacTest() throws Exception {
        List<String> result = MacTools.getLocalMacList();
        log.info("本机mac地址为：{}" + result.toString());
    }

}
