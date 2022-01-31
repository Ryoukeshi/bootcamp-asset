package com.bootcamp.second.asset.controller;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;
import com.bootcamp.second.asset.model.AssetDTO;
import com.bootcamp.second.asset.model.Client;
import com.bootcamp.second.asset.model.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "15000")
class AssetControllerTest {

    @MockBean
    private AssetService assetService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AssetController assetController;

    private static final AssetDTO assetDTO = new AssetDTO();
    private static final Asset asset = new Asset();
    private static final Client client = new Client();
    private static final Operation operation1 = new Operation();
    private static final Operation operation2 = new Operation();
    private static final List<AssetDTO> assetDTOList = new ArrayList<>();
    private static final ArrayList<Operation> operationsList = new ArrayList<>();
    /**********Data for Client************/
    private static final String clientId = "68asd86123687herfgfqw84344fdfxzm";
    private static final String name = "Steve";
    private static final String lastName = "Rogers";
    private static final String clientType = "Personal";
    private static final String clientProfile = "vip";
    private static final String documentType = "dni";
    private static final String documentNumber = "70054620";
    private static final String phoneNumber = "987155412";
    private static final boolean debt = false;
    private static final String clientStatus = "1";
    /**********Data for Operations*******/
    private static final String operationId1 = "894erf8s97fwe324854gxhdoijgfwegzxf";
    private static final String operationType1 = "Transfer";
    private static final Date operationDate1 = Calendar.getInstance().getTime();
    private static final String operationAmount1 = "46510";

    private static final String operationId2 = "321wqf658ys68hhfah64a84hrevctkaf";
    private static final String operationType2 = "Payment";
    private static final Date operationDate2 = Calendar.getInstance().getTime();
    private static final String operationAmount2 = "51500";
    /**********Data for Asset************/
    private static final String assetId = "982wrdgjy15dyq2fh4f2vdsxer";
    private static final String assetType = "Personal";
    private static final String totalAmount = "500000";
    private static final String availableAmount = "145000";
    private static final String assetStatus = "1";

    @BeforeAll
    static void setUp() {
        /****Client****/
        client.setId(clientId);
        client.setName(name);
        client.setLastName(lastName);
        client.setClientType(clientType);
        client.setClientProfile(clientProfile);
        client.setDocumentType(documentType);
        client.setDocumentNumber(documentNumber);
        client.setPhoneNumber(phoneNumber);
        client.setDebt(debt);
        client.setStatus(clientStatus);

        /**Operations**/
        operation1.setOperationId(operationId1);
        operation1.setOperationType(operationType1);
        operation1.setDate(operationDate1);
        operation1.setAmount(operationAmount1);

        operation2.setOperationId(operationId2);
        operation2.setOperationType(operationType2);
        operation2.setDate(operationDate2);
        operation2.setAmount(operationAmount2);

        operationsList.add(operation1);
        operationsList.add(operation2);

        /****Asset****/
        assetDTO.setId(assetId);
        assetDTO.setOwner(client);
        assetDTO.setAssetType(assetType);
        assetDTO.setTotalAmount(totalAmount);
        assetDTO.setAvailableAmount(availableAmount);
        assetDTO.setOperations(operationsList);
        assetDTO.setStatus(assetStatus);

        asset.getId();
        asset.getOwner();
        asset.getAssetType();
        asset.getTotalAmount();
        asset.getAvailableAmount();
        asset.getOperations();
        asset.getStatus();

        BeanUtils.copyProperties(assetDTO, asset);
        assetDTOList.add(assetDTO);
    }

    @Test
    @DisplayName("GET -> /api/assets/{id}")
    void byId() {
        when(assetService.findById(assetId)).thenReturn(Mono.just(assetDTO));

        WebTestClient.ResponseSpec responseSpec = webTestClient
                                                    .get()
                                                    .uri("/api/assets/" + assetId)
                                                    .accept(MediaType.APPLICATION_JSON)
                                                    .exchange();

        responseSpec.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON);
        responseSpec.expectBody().jsonPath("$.id").isEqualTo(assetDTO.getId());
    }

    @Test
    @DisplayName("GET -> /api/assets")
    void findAll() {
        when(assetService.findAll()).thenReturn(Flux.fromIterable(assetDTOList));

        WebTestClient.ResponseSpec responseSpec = webTestClient.get()
                .uri("/api/assets")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        responseSpec.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
        //Assertions.assertNotNull(assetController.findAll());
    }

    @Test
    @DisplayName("GET -> /api/assets/findByOwner")
    void findByOwner() {
        when(assetService.findAssetByName(name)).thenReturn(Flux.just(assetDTO));

        WebTestClient.ResponseSpec responseSpec = webTestClient
                                                    .get()
                                                    .uri("/api/assets/findByOwner" + "?name=" + name)
                                                    .accept(MediaType.APPLICATION_JSON)
                                                    .exchange();

        responseSpec.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @DisplayName("GET -> /api/assets/findByAssetType")
    void findByAssetType() {
        when(assetService.findAssetByAssetType(assetType)).thenReturn(Flux.just(assetDTO));

        WebTestClient.ResponseSpec responseSpec = webTestClient
                                                    .get()
                                                    .uri("/api/assets/findByType" + "?type=" + assetType)
                                                    .accept(MediaType.APPLICATION_JSON)
                                                    .exchange();

        responseSpec.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @DisplayName("PUT -> /api/assets/{id}")
    void update() {
        when(assetService.update(assetId, assetDTO)).thenReturn(Mono.just(assetDTO));
        Assertions.assertNotNull(assetController.update(assetId, assetDTO));
    }

    @Test
    void fallBackCreateAsset() {
        Assertions.assertNotNull(assetController.fallBackCreateAsset(assetDTO, new RuntimeException("")));
    }

    @Test
    void fallBackFindAsset() {
        Assertions.assertNotNull(assetController.fallBackFindAsset(assetId, new RuntimeException("")));
    }

    @Test
    void fallBackFindAllAssets() {
        Assertions.assertNotNull(assetController.fallBackFindAllAssets(new RuntimeException("")));
    }

    @Test
    void fallBackUpdateAsset() {
        Assertions.assertNotNull(assetController.fallBackUpdateAsset(assetId, assetDTO, new RuntimeException("")));
    }

    @Test
    void fallBackDeleteAsset() {
        Assertions.assertNotNull(assetController.fallBackDeleteAsset(assetId, new RuntimeException("")));
    }
}