package com.bootcamp.second.asset.business.impl;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;
import com.bootcamp.second.asset.repository.AssetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private WebClient webClient;
    
    @Override
    public Mono<Asset> create(Asset asset) {
        
        //if(!asset.getAsset_type().isBlank() && !asset.getOwner().isBlank()){
            return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("owner", asset.getOwner(), 
                    uriBuilder.queryParam("asset_type", asset.getAsset_type(),
                    uriBuilder.queryParam("amount", asset.getAmount()),
                    uriBuilder.queryParam("status", asset.getStatus())))
                .build())
                .retrieve()
                .bodyToFlux(Asset.class)
                .next()
                .flatMap(a -> {
                    return assetRepository.save(asset);
                });
                //.flatMap(assetRepository.save(asset));
        //}
        
        //return assetRepository.save(asset);
    }

    @Override
    public Mono<Asset> findById(String assetId) {
        
        return assetRepository.findById(assetId);
    }

    @Override
    public Flux<Asset> findAll() {
        
        return assetRepository.findAll();
    }

    @Override
    public Mono<Asset> update(Asset asset) {
        
        return assetRepository.findById(asset.getId()).flatMap(a -> assetRepository.save(asset).thenReturn(a));
    }

    @Override
    public Mono<Asset> remove(String assetId) {
        
        return assetRepository
            .findById(assetId)
            .flatMap(p -> assetRepository.deleteById(p.getId()).thenReturn(p));
    }

    @Override
    public Flux<Asset> findAssetByOwner(String owner) {
        
        return assetRepository.findAssetsByOwner(owner);
    }

    @Override
    public Flux<Asset> findAssetByAsset_Type(String asset_type) {
        
        return assetRepository.findAssetsByAsset_Type(asset_type);
    }

    @Override
    public Flux<Asset> findAssetByStatus(String status) {
        
        return assetRepository.findAssetsByStatus(status);
    }    
}
