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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Flux<Asset> findByOwner(String owner) {
        
        return assetRepository.findAssetByOwner(owner);
    }

    
}
