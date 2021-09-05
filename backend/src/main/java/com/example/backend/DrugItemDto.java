package com.example.backend;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DrugItemDto {

    private String urlId;
    private String brandName;
    private String genericName;
    private String price;

}
