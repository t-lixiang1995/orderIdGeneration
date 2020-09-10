package com.psc.distributedprimarykeyservice.service.impl;

import com.psc.distributedprimarykeyservice.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 
 * @author pcitc
 * @createTime 2018年1月29日 下午12:26:46
 * 
 */
@Service("uuidOrderServiceImpl")
public class UuidOrderServiceImpl implements IOrderService {

	public String getOrderId() {
		UUID randomUUID = UUID.randomUUID(); //UUID生成
		System.out.println("insert into order_id(id) values('"+randomUUID+"');");
		return randomUUID.toString();
	}

}
