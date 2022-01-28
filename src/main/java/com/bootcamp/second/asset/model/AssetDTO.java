package com.bootcamp.second.asset.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {

    private String id;
    private Client owner;
    private String assetType;
    private String totalAmount;
    private String availableAmount;
    private String status;
    private ArrayList<Operation> operations;
}
