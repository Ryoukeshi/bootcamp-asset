package com.bootcamp.second.asset.controller;

import com.bootcamp.second.asset.business.AssetService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "15000")
class AssetControllerTest {

    @MockBean
    private AssetService assetService;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeAll
    static void setUp() {

    }
    @Test
    void create() {
    }

    @Test
    void byId() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByOwner() {
    }

    @Test
    void findByAssetType() {
    }

    @Test
    void findByStatus() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void fallBackCreateAsset() {
    }

    @Test
    void fallBackFindAsset() {
    }

    @Test
    void fallBackFindAllAssets() {
    }

    @Test
    void fallBackUpdateAsset() {
    }

    @Test
    void fallBackDeleteAsset() {
    }
}