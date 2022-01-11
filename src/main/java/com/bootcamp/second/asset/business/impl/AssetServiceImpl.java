package com.bootcamp.second.asset.business.impl;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;
import com.bootcamp.second.asset.repository.AssetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;
    
    @Override
    public Mono<Asset> create(Asset asset) {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Asset> remove(String assetId) {
        
        return assetRepository.findById(assetId)
            .flatMap(p -> assetRepository.deleteById(p.getId().thenReturn(p)));
    }

    @Override
    public Flux<Asset> findByOwner(String owner) {
        
        return assetRepository.findAssetsByOwner(owner);
    }

    @Override
    public Flux<Asset> findByStatus(String status) {
        
        return assetRepository.findAssetsByStatus(status);
    }    
}
