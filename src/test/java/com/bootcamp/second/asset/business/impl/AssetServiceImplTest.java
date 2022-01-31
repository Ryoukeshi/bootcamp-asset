package com.bootcamp.second.asset.business.impl;

import com.bootcamp.second.asset.controller.AssetController;
import com.bootcamp.second.asset.model.Asset;
import com.bootcamp.second.asset.model.AssetDTO;
import com.bootcamp.second.asset.model.Client;
import com.bootcamp.second.asset.model.Operation;
import com.bootcamp.second.asset.repository.AssetRepository;
import com.bootcamp.second.asset.utils.Constants;
import com.bootcamp.second.asset.utils.ConversionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssetServiceImplTest {

    @MockBean
    private ConversionUtils conversionUtils;

    @Autowired
    private AssetServiceImpl assetService;

    @MockBean
    private AssetRepository assetRepository;

    private static final AssetDTO assetDTO = new AssetDTO();
    private static final Asset asset = new Asset();
    private static final Client client = new Client();
    private static final Operation operation1 = new Operation();
    private static final Operation operation2 = new Operation();
    private static final List<AssetDTO> assetDTOList = new ArrayList<>();
    private static final ArrayList<Operation> operationsList = new ArrayList<>();
    /**********Data for Client************/
    private static final String clientId = "68asd86123687herfgfqw84344fdfxzm";
    private static final String name = "Bruce";
    private static final String lastName = "Banner";
    private static final String clientType = "Personal";
    private static final String clientProfile = "vip";
    private static final String documentType = "dni";
    private static final String documentNumber = "125184401";
    private static final String phoneNumber = "987164521";
    private static final boolean debt = false;
    private static final String clientStatus = "ACTIVE";
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

        BeanUtils.copyProperties(assetDTO, asset);
        assetDTOList.add(assetDTO);
    }

    @Test
    void create() {
        when(assetRepository.findById(assetId)).thenReturn(Mono.just(asset));
        //when(conversionUtils.createAssetEntity(assetDTO)).thenReturn(Mono.just(assetDTO));
        when(assetRepository.insert(asset)).thenReturn(Mono.just(asset));

        Mono<AssetDTO> monoTest = assetService.create(assetDTO);

        StepVerifier.create(monoTest).expectComplete();
    }

    @Test
    void findById() {
        when(assetRepository.findById(assetId)).thenReturn(Mono.just(asset));

        Mono<AssetDTO> monoTest = assetService.findById(assetId);

        StepVerifier.create(monoTest).expectComplete();
    }

    @Test
    void findAll() {
        when(assetRepository.findAll()).thenReturn(Flux.just(asset));

        Flux<AssetDTO> monoTest = assetService.findAll();

        StepVerifier.create(monoTest).verifyComplete();
    }

    @Test
    void update() {
        when(assetRepository.findById(assetId)).thenReturn(Mono.just(asset));
        //when(conversionUtils.createAssetEntity(assetDTO)).thenReturn(Mono.just(assetDTO));
        when(assetRepository.save(asset)).thenReturn(Mono.just(asset));

        Mono<AssetDTO> monoTest = assetService.create(assetDTO);

        StepVerifier.create(monoTest).expectComplete();
    }

    @Test
    void remove() {
        when(assetRepository.findById(assetId)).thenReturn(Mono.just(asset));

        when(assetRepository.save(asset)).thenReturn(Mono.just(asset));

        Mono<AssetDTO> monoTest = assetService.remove(assetId);

        StepVerifier.create(monoTest).expectComplete();
    }

    @Test
    void findAssetByName() {
        when(assetRepository.findByOwner_Name(name)).thenReturn(Flux.just(asset));

        Flux<AssetDTO> monoTest = assetService.findAssetByName(name);

        StepVerifier.create(monoTest).verifyComplete();
    }

    @Test
    void findAssetByAssetType() {
        when(assetRepository.findByAssetType(assetType)).thenReturn(Flux.just(asset));

        Flux<AssetDTO> monoTest = assetService.findAssetByAssetType(assetType);

        StepVerifier.create(monoTest).verifyComplete();
    }
}