package com.bootcamp.second.asset.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    private String operationId;
    private Date date;
    private String operationType;
    private String amount;
}
