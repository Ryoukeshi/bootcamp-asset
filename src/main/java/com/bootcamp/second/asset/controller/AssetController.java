package com.bootcamp.second.asset.controller;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;

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

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/api/assets")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Asset> create(@RequestBody Asset asset){

        log.info("----create----");

        return assetService.create(asset);
    }

    @GetMapping("/api/assets/{id}")
    public Mono<Asset> byId(@PathVariable ("id") String id) {

        log.info("----byId----");

        return assetService.findById(id);
      }

      @GetMapping("/api/assets")
      public Flux<Asset> findAll() {

        log.info("----findAll----");

        return assetService.findAll();
      }

      @GetMapping("/api/assets/findByOwner")
      public Flux<Asset> findByOwner(@RequestParam ("owner") String owner){

        log.info("----findByOwner----");

        return assetService.findAssetByOwner(owner);
      }

      @GetMapping("/api/assets/findByType")
      public Flux<Asset> findByAssetType(@RequestParam ("type") String asset_type) {

        log.info("----findByAsset_Type----");

        return assetService.findAssetByAsset_Type(asset_type);
      }

      @GetMapping("/api/assets/findByStatus")
      public Flux<Asset> findByStatus(@RequestParam ("status") String status){

        log.info("----findByStatus----");

        return assetService.findAssetByStatus(status);
      }
    
      @PutMapping("/api/assets/{id}")
      public Mono<ResponseEntity<Asset>> update(@PathVariable ("id") String id, @RequestBody Asset asset){

        log.info("----update----");

        return assetService.update(asset)
            .flatMap(assetUpdate -> Mono.just(ResponseEntity.ok(assetUpdate)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
      }

      @DeleteMapping("/api/assets/{id}")
      public Mono<ResponseEntity<Asset>> delete(@PathVariable ("id") String assetId){

        log.info("----delete----");

        return assetService.remove(assetId)
            .flatMap(asset -> Mono.just(ResponseEntity.ok(asset)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
      }
}
