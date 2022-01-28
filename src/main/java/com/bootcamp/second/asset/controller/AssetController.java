package com.bootcamp.second.asset.controller;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;

import com.bootcamp.second.asset.model.AssetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AssetController {

    @Autowired
    private AssetService assetService;

    @CircuitBreaker(name = "createAssetCB", fallbackMethod = "fallBackCreateAsset")
    @PostMapping("/api/assets")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AssetDTO> create(@RequestBody AssetDTO asset){

      log.info("----create----");

      return assetService.create(asset);
    }

    @CircuitBreaker(name = "findAssetCB", fallbackMethod = "fallBackFindAsset")
    @GetMapping("/api/assets/{id}")
    public Mono<AssetDTO> byId(@PathVariable ("id") String id) {

      log.info("----byId----");

      return assetService.findById(id);
    }

    @CircuitBreaker(name = "findAllAssetsCB", fallbackMethod = "fallBackFindAllAssets")
    @GetMapping("/api/assets")
    public Flux<AssetDTO> findAll() {

      log.info("----findAll----");

      return assetService.findAll();
    }

    @GetMapping("/api/assets/findByOwner")
    public Flux<AssetDTO> findByOwner(@RequestParam ("owner") String owner){

      log.info("----findByOwner----");

      return assetService.findAssetByOwner(owner);
    }

    @GetMapping("/api/assets/findByType")
    public Flux<AssetDTO> findByAssetType(@RequestParam ("type") String asset_type) {

      log.info("----findByAsset_Type----");

        return assetService.findAssetByAssetType(asset_type);
    }
    
    @CircuitBreaker(name = "updateAssetCB", fallbackMethod = "fallBackUpdateAsset")
    @PutMapping("/api/assets/{id}")
    public Mono<ResponseEntity<AssetDTO>> update(@PathVariable ("id") String id, @RequestBody AssetDTO asset){

      log.info("----update----");

      return assetService.update(asset)
          .flatMap(assetUpdate -> Mono.just(ResponseEntity.ok(assetUpdate)))
          .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @CircuitBreaker(name = "deleteAssetCB", fallbackMethod = "fallBackDeleteAsset")
    @DeleteMapping("/api/assets/{id}")
    public Mono<ResponseEntity<AssetDTO>> delete(@PathVariable ("id") String assetId){

      log.info("----delete----");

      return assetService.remove(assetId)
          .flatMap(asset -> Mono.just(ResponseEntity.ok(asset)))
          .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<ResponseEntity<String>> fallBackCreateAsset(@RequestBody Asset asset, RuntimeException ex){
      return Mono.just(ResponseEntity.ok().body("---Error while creating asset with type: " 
        + asset.getAssetType() + " not available---"));
    }

    public Mono<ResponseEntity<String>> fallBackFindAsset(@PathVariable String id, RuntimeException ex){
      return Mono.just(ResponseEntity.ok().body("---Error while finding asset with id: "
        + id + " not available---"));
    }

    public Mono<ResponseEntity<String>> fallBackFindAllAssets(RuntimeException ex){
      return Mono.just(ResponseEntity.ok().body("---Error while finding assets not available---"));
    }

    public Mono<ResponseEntity<String>> fallBackUpdateAsset(@PathVariable String id, @RequestBody Asset asset, 
                                                                RuntimeException ex){
      return Mono.just(ResponseEntity.ok().body("---Error while updating asset with id: " 
        + id + ", owner: " + asset.getOwner() + " not available---"));
    }

    public Mono<ResponseEntity<String>> fallBackDeleteAsset(@PathVariable String id, RuntimeException ex){
      return Mono.just(ResponseEntity.ok().body("---Error while deleting asset with id: " 
      + id + " not available---"));
    }
}
