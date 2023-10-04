package com.koala.wms.DTO;

import com.koala.wms.entity.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RecordDTO extends Record{
    private String username;
    private String adminname;
    private String goodsname;
    private String storagename;
    private String goodstypename;
}
