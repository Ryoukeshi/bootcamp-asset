package com.bootcamp.second.asset.utils;

import com.bootcamp.second.asset.model.Asset;
import com.bootcamp.second.asset.model.AssetDTO;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

public class ConversionUtils {

    public static AssetDTO entityToAssetDto(Asset asset){
        AssetDTO assetDTO = new AssetDTO();
        BeanUtils.copyProperties(asset, assetDTO);
        return assetDTO;
    }

    public static Asset assetDtoToEntity(AssetDTO assetDTO){
        Asset asset = new Asset();
        BeanUtils.copyProperties(assetDTO, asset);
        return asset;
    }

    public static Mono<AssetDTO> createAssetEntity(AssetDTO assetDTO){
        AssetDTO modifiedAssetDTO = new AssetDTO();
        BeanUtils.copyProperties(assetDTO, modifiedAssetDTO);
        modifiedAssetDTO.setStatus(Constants.ACTIVE.name());
        return Mono.just(modifiedAssetDTO);
    }

    public static Mono<AssetDTO> updateAssetEntity(AssetDTO assetDTO, String id){
        AssetDTO modifiedAssetDTO = new AssetDTO();
        BeanUtils.copyProperties(assetDTO, modifiedAssetDTO);
        modifiedAssetDTO.setId(id);
        return Mono.just(modifiedAssetDTO);
    }
}
