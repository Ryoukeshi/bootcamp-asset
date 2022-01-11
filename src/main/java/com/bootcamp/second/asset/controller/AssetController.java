package com.bootcamp.second.asset.controller;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;

//import org.apache.log4j.spi.LoggerFactory;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Asset> create(@RequestBody Asset asset){

        log.info("----create----");

        return assetService.create(asset);
    }

    @GetMapping("/api/assets/specific")
    public Mono<Asset> byId(@RequestParam ("id") String id) {

        log.info("----byId----");

        return assetService.findById(id);
      }

      @GetMapping("/api/assets/all")
      public Flux<Asset> findAll() {

        log.info("----findAll----");

        return assetService.findAll();
      }

      @GetMapping("/api/assets/findByOwner")
      public Flux<Asset> findByOwner(@RequestParam ("owner") String owner){

        log.info("----findByOwner----");

        return assetService.findByOwner(owner);
      }

      @GetMapping("/api/assets/findByType")
      public Flux<Asset> findByAssetType(@RequestParam ("type") String asset_type) {

        log.info("----findByAsset_Type----");

        return assetService.findByAsset_Type(asset_type);
      }

      @GetMapping("/api/assets/findByStatus")
      public Flux<Asset> findByStatus(@RequestParam ("status") String status){

        log.info("----findByStatus----");

        return assetService.findByStatus(status);
      }
    
      @PutMapping("/api/assets/upd")
      public Mono<ResponseEntity<Asset>> update(@RequestBody Asset asset){

        log.info("----update----");

        return assetService.update(asset)
            .flatMap(assetUpdate -> Mono.just(ResponseEntity.ok(assetUpdate)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
      }

      @DeleteMapping("/api/assets/delete")
      public Mono<ResponseEntity<Asset>> delete(@RequestParam ("id") String assetId){

        log.info("----delete----");

        return assetService.remove(assetId)
            .flatMap(asset -> Mono.just(ResponseEntity.ok(asset)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
      }
}
