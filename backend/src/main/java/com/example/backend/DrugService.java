package com.example.backend;

import io.tej.SwaggerCodgen.model.DrugItemDetails;

import java.io.FileNotFoundException;
import java.util.List;

public interface DrugService {

    List<DrugItemDto> getDrugs() throws FileNotFoundException;

    DrugItemDetails getDrugDetails(String id);
}
