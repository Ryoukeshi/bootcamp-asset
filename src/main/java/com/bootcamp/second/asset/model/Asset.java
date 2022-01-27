package com.bootcamp.second.asset.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assets")
public class Asset {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field(name = "owner")
    private String owner;

    @Field(name = "asset_type")
    private String asset_type;

    @Field(name = "amount")
    private String amount;

    @Field(name = "status")
    private String status;
}
