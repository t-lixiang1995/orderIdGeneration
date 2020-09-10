package com.psc.distributedprimarykeyservice.service;

import com.psc.distributedprimarykeyservice.entity.ID;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public interface IdService {
    Long genId();

    ID expId(Long id);

    Date transTime(Long time);

    Long makeId(Long time, Long seq);

    Long makeId(Long time, Long seq, Long machine);
}