package com.example.backend;

import io.tej.SwaggerCodgen.api.DrugApi;
import io.tej.SwaggerCodgen.model.DrugItemDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrugController implements DrugApi {

    private final DrugService drugService;
    private final DrugsConverter drugsConverter;

    @Override
    public ResponseEntity<DrugItemDetails> drugGet(String id) {
        return ResponseEntity.ok(drugService.getDrugDetails(id));
    }
}
