package com.psc.distributedprimarykeyservice.service;

import com.psc.distributedprimarykeyservice.entity.ID;

public interface IdConverter {
    long convert(ID id);

    ID convert(long id);
}
