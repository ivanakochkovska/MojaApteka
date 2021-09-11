package com.example.backend;

import io.tej.SwaggerCodgen.api.DrugsApi;
import io.tej.SwaggerCodgen.model.DrugItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DrugsController implements DrugsApi {



    private final DrugService drugService;
    private final DrugsConverter drugsConverter;


    @Override
    public ResponseEntity<List<DrugItem>> drugsOverviewGet()  {

        List<DrugItemDto> drugItemDtos = null;
        try {
            drugItemDtos = drugService.getDrugs();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<DrugItem> drugItems = drugItemDtos.stream().map(drugsConverter::convertDrug).collect(Collectors.toList());
        return ResponseEntity.ok(drugItems);
    }
}
