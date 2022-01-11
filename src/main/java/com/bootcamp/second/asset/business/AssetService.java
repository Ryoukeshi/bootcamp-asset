package com.bootcamp.second.asset.business;

import com.bootcamp.second.asset.model.Asset;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AssetService {

    Mono<Asset> create(Asset asset);

    Mono<Asset> findById(String assetId);

    Flux<Asset> findAll();

    Mono<Asset> update(Asset asset);

    Mono<Asset> remove(String assetId);

    Flux<Asset> findByOwner(String owner);

    Flux<Asset> findByStatus(String status);
}
