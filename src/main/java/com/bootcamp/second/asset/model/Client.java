package com.bootcamp.second.asset.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Client {

    private String id;
    private String name;
    private String lastName;
    private String clientType;
    private String clientProfile;
    private String documentType;
    private String documentNumber;
    private String phoneNumber;
    private boolean debt;
    private String status;
}
