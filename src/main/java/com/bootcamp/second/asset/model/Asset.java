package com.bootcamp.second.asset.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "assets")
public class Asset {

    @Field(name = "id")
    @Id
    private String id;

    @Field(name = "owner")
    private Client owner;

    @Field(name = "asset_type")
    private String assetType;

    @Field(name = "totalAmount")
    private String totalAmount;

    @Field(name = "availableAmount")
    private String availableAmount;

    @Field(name = "status")
    private String status;

    @Field(name = "operations")
    private ArrayList<Operation> operations;
}
