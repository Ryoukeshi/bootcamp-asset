package com.bootcamp.second.asset.business.impl;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;
import com.bootcamp.second.asset.model.AssetDTO;
import com.bootcamp.second.asset.model.Client;
import com.bootcamp.second.asset.repository.AssetRepository;

import com.bootcamp.second.asset.utils.Constants;
import com.bootcamp.second.asset.utils.ConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;


    
    @Override
    public Mono<AssetDTO> create(AssetDTO assetDTO) {
        
        return assetRepository.findById(assetDTO.getId())
                .switchIfEmpty(Mono.just(new Asset()))
                .flatMap(asset1 -> ConversionUtils.createAssetEntity(assetDTO))
                .map(ConversionUtils::assetDtoToEntity)
                .flatMap(assetRepository::insert)
                .map(ConversionUtils::entityToAssetDto);
    }

    @Override
    public Mono<AssetDTO> findById(String assetId) {
        
        return assetRepository.findById(assetId)
                .switchIfEmpty(Mono.empty())
                .filter(asset -> asset.getStatus().equalsIgnoreCase(Constants.ACTIVE.name()))
                .map(ConversionUtils::entityToAssetDto);
    }

    @Override
    public Flux<AssetDTO> findAll() {
        
        return assetRepository.findAll()
                .filter(a -> a.getStatus().equalsIgnoreCase(Constants.ACTIVE.name()))
                .map(ConversionUtils::entityToAssetDto);
    }

    @Override
    public Mono<AssetDTO> update(String id, AssetDTO asset) {
        
        return assetRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(a -> ConversionUtils.updateAssetEntity(asset, id))
                .map(ConversionUtils::assetDtoToEntity)
                .flatMap(assetRepository::save)
                .map(ConversionUtils::entityToAssetDto);
    }

    @Override
    public Mono<AssetDTO> remove(String assetId) {
        
        return assetRepository.findById(assetId)
                .switchIfEmpty(Mono.empty())
                .doOnNext(a -> a.setStatus(Constants.INACTIVE.name()))
                .flatMap(assetRepository::save)
                .map(ConversionUtils::entityToAssetDto);
    }

    @Override
    public Flux<AssetDTO> findAssetByName(String name) {
        
        return assetRepository.findByOwner_Name(name)
                .switchIfEmpty(Flux.empty())
                .filter(a -> a.getStatus().equalsIgnoreCase(Constants.ACTIVE.name()))
                .map(ConversionUtils::entityToAssetDto);
    }

    @Override
    public Flux<AssetDTO> findAssetByAssetType(String asset_type) {
        
        return assetRepository.findByAssetType(asset_type)
                .switchIfEmpty(Flux.empty())
                .filter(a -> a.getStatus().equalsIgnoreCase(Constants.ACTIVE.name()))
                .map(ConversionUtils::entityToAssetDto);
    }
}
