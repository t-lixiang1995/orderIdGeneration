package com.psc.distributedprimarykeyservice.service.impl;

import com.psc.distributedprimarykeyservice.service.IOrderService;
import com.psc.distributedprimarykeyservice.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author pcitc
 * @createTime 2019年1月29日 下午1:00:11
 * 
 */
@Service("redisOrderServiceImpl")
public class RedisOrderServiceImpl implements IOrderService {

	@Autowired
	private RedisUtil redis;
	
	public String getOrderId() {
		String key = "orderId";
		int timeout = 60;
		String prefix = getPrefix(new Date()); //根据时间获取前缀
		long id = redis.incr(key, timeout); //使用redis获取自增ID
		System.out.println("insert into order_id(id) values('"+prefix+ String.format("%1$05d", id)+"');");//00001  00002 ... 10001
		return prefix+ String.format("%1$05d", id);
	}
	
	private String getPrefix(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);//2018
        int day = c.get(Calendar.DAY_OF_YEAR);//93---1
        int hour = c.get(Calendar.HOUR_OF_DAY);//21
		//天数转为3位字符串,不满3位用0补齐
        String dayFmt = String.format("%1$03d", day);
		//小时转为2位字符串,不满2位用0补齐
        String hourFmt = String.format("%1$02d", hour);
		//2位年份+3位天数+2位小时 
        return (year - 2000) + dayFmt + hourFmt;
    }
}
