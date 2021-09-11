package com.example.backend;

import io.tej.SwaggerCodgen.model.DrugItem;
import org.springframework.stereotype.Component;

@Component
public class DrugsConverter {

    public DrugItem convertDrug(DrugItemDto drugItemDto) {

        DrugItem drugItem = new DrugItem();
        String getNumberOfTheUrl = drugItemDto.getUrlId().substring(30);
        drugItem.setUrlId(getNumberOfTheUrl);
        drugItem.setBrandName(drugItemDto.getBrandName());
        drugItem.setGenericName(drugItemDto.getGenericName());
        drugItem.setPrice(drugItemDto.getPrice());

        return drugItem;
    }
}
