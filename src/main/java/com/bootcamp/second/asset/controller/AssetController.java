package com.bootcamp.second.asset.controller;

import com.bootcamp.second.asset.business.AssetService;
import com.bootcamp.second.asset.model.Asset;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AssetController {

    @Autowired
    private AssetService assetService;

    private Logger log;

    @PostMapping
    public Mono<Asset> create(@RequestBody Asset asset){
        
        return null;
    }

    @GetMapping("/api/assets/specific")
    public Mono<Asset> byId(@RequestParam("id") String id) {
        log.info("----byId----");

        return assetService.findById(id);
      }

      @GetMapping("")
      public Flux<Asset> findAll() {

        return assetService.findAll();
      }
    
}
