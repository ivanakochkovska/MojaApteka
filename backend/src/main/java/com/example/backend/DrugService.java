package com.example.backend;

import java.io.FileNotFoundException;
import java.util.List;

public interface DrugService {

    List<DrugItemDto> getDrugs() throws FileNotFoundException;
}
