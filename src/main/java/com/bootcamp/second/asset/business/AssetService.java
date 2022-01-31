package com.bootcamp.second.asset.business;

import com.bootcamp.second.asset.model.Asset;

import com.bootcamp.second.asset.model.AssetDTO;
import com.bootcamp.second.asset.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssetService {

    Mono<AssetDTO> create(AssetDTO asset);

    Mono<AssetDTO> findById(String assetId);

    Flux<AssetDTO> findAll();

    Mono<AssetDTO> update(String id, AssetDTO asset);

    Mono<AssetDTO> remove(String assetId);

    Flux<AssetDTO> findAssetByOwner(Client owner);

    Flux<AssetDTO> findAssetByAssetType(String asset_type);
}
