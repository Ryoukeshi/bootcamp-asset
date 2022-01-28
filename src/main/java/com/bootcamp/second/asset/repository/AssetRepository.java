package com.bootcamp.second.asset.repository;

import com.bootcamp.second.asset.model.Asset;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface AssetRepository extends ReactiveMongoRepository<Asset, String>{

    Flux<Asset> findByOwner(String owner);

    Flux<Asset> findByAssetType(String asset_type);

}
