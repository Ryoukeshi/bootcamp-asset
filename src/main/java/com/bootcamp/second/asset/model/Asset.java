package com.bootcamp.second.asset.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Document(collection = "assets")
public class Asset {

    @Id
    private String id = UUID.randomUUID().toString();
    
    private String owner;
    private String asset_type;
    private String amount;
    private String status;
}
