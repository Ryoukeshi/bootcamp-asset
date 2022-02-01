package com.bootcamp.second.asset.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BCoin {
    private double buyFee;
    private double sellFee;
    private String purseId;
}
