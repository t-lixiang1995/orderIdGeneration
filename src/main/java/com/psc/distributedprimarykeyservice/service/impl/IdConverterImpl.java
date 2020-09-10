package com.psc.distributedprimarykeyservice.service.impl;

import com.psc.distributedprimarykeyservice.entity.ID;
import com.psc.distributedprimarykeyservice.entity.IdMeta;
import com.psc.distributedprimarykeyservice.service.IdConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lixiang
 * @date 2020/9/10 9:56
 * @desc
 */
@Data
@NoArgsConstructor
public class IdConverterImpl implements IdConverter {
    public long convert(ID id) {
        long ret = 0;

        ret |= id.getSequence();

        ret |= id.getWorker() << IdMeta.SEQUENCE_BITS;

        ret |= id.getTimeStamp() << IdMeta.TIMESTAMP_LEFT_SHIFT_BITS;

        return ret;
    }

    public ID convert(long id) {
        ID ret = new ID();

        ret.setSequence(id & IdMeta.SEQUENCE_MASK);

        ret.setWorker((id >>> IdMeta.SEQUENCE_BITS) & IdMeta.ID_MASK);

        ret.setTimeStamp((id >>> IdMeta.TIMESTAMP_LEFT_SHIFT_BITS) & IdMeta.TIMESTAMP_MASK);

        return ret;
    }
}
