package com.example.backend;

import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {

    @Override
    public List<DrugItemDto> getDrugs() {
        List<DrugItemDto> drugs = new ArrayList<>();
        try {
            String brandNameUri = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/brandName";
            String genericName = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/genericName";
            String pricePropertyUri="http://purl.org/net/hifm/ontology#refPriceWithVAT";


            Model model = ModelFactory.createDefaultModel();
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Ivana\\IdeaProjects\\MojaApteka\\backend\\src\\main\\resources\\dataset.ttl");


            model.read(fileInputStream, null, "TTL");


            StmtIterator stmtIterator = model.listStatements(new SimpleSelector(null, new PropertyImpl(brandNameUri), (Object) null));

            while (stmtIterator.hasNext()) {
                DrugItemDto drugItemDto = new DrugItemDto();
                Statement statement = stmtIterator.nextStatement();
                String drugUri = statement.getSubject().getURI(); //URI of the drug
                drugItemDto.setUrlId(drugUri);

                Resource resource= model.getResource(drugUri);
                //search for brandName
                StmtIterator stmtIterator1 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(brandNameUri), (Object) null));
               while (stmtIterator1.hasNext()) {
                   drugItemDto.setBrandName(stmtIterator1.nextStatement().getObject().toString());
               }

                StmtIterator stmtIterator2 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(genericName), (Object) null));
                while (stmtIterator2.hasNext()) {
                    drugItemDto.setGenericName(stmtIterator2.nextStatement().getObject().toString());
                }

                StmtIterator stmtIterator3 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(pricePropertyUri), (Object) null));
                while (stmtIterator3.hasNext()) {
                    drugItemDto.setPrice(stmtIterator3.nextStatement().getObject().toString());
                }

                drugs.add(drugItemDto);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return drugs;
    }
}
