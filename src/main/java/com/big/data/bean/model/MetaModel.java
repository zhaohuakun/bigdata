package com.big.data.bean.model;

import com.big.data.bean.common.CommonBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaModel extends CommonBean {

    private int id;

    private String nameCn;

    private String nameEn;

    private String nameAbbr;

    private String fDesc;
}