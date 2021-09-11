package com.example.backend;

import io.tej.SwaggerCodgen.model.DrugItem;
import io.tej.SwaggerCodgen.model.DrugItemDetails;
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

    @Override
    public DrugItemDetails getDrugDetails(String id) {

        String prefix = "http://purl.org/net/hifm/data#";
        Model model = ModelFactory.createDefaultModel();
        DrugItemDetails drugItemDetails = new DrugItemDetails();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("C:\\Users\\Ivana\\IdeaProjects\\MojaApteka\\backend\\src\\main\\resources\\dataset.ttl");
            model.read(fileInputStream, null, "TTL");
            Resource resource= model.getResource(prefix+id);

            String brandNameUri = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/brandName";
            String genericName = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/resource/drugbank/genericName";
            String pricePropertyUri="http://purl.org/net/hifm/ontology#refPriceWithVAT";
            String strengthUri = "http://purl.org/net/hifm/ontology#strength";
            String manufacturerUri = "http://purl.org/net/hifm/ontology#manufacturer";
            String dosageForm = "http://purl.org/net/hifm/ontology#dosageForm";
            String similarToUrl="http://purl.org/net/hifm/ontology#similarTo";



            StmtIterator stmtIterator1 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(brandNameUri), (Object) null));
            while (stmtIterator1.hasNext()) {
                drugItemDetails.setBrandName(stmtIterator1.nextStatement().getObject().toString());
            }

            StmtIterator stmtIterator2 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(genericName), (Object) null));
            while (stmtIterator2.hasNext()) {
                drugItemDetails.setGenericName(stmtIterator2.nextStatement().getObject().toString());
            }

            StmtIterator stmtIterator3 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(pricePropertyUri), (Object) null));
            while (stmtIterator3.hasNext()) {
                drugItemDetails.setPrice(stmtIterator3.nextStatement().getObject().toString());
            }

            StmtIterator stmtIterator4 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(strengthUri), (Object) null));
            while (stmtIterator4.hasNext()) {
                drugItemDetails.setStrength(stmtIterator4.nextStatement().getObject().toString());
            }
            StmtIterator stmtIterator5 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(manufacturerUri), (Object) null));
            while (stmtIterator5.hasNext()) {
                drugItemDetails.setManufacturer(stmtIterator5.nextStatement().getObject().toString());
            }
            StmtIterator stmtIterator6 = model.listStatements(new SimpleSelector(resource, new PropertyImpl(dosageForm), (Object) null));
            while (stmtIterator6.hasNext()) {
                drugItemDetails.setDosageForm(stmtIterator6.nextStatement().getObject().toString());
            }

            StmtIterator stmtIterator7=model.listStatements(
                    new SimpleSelector(resource,new PropertyImpl(similarToUrl),(Object)null)
            );

            List<DrugItem> drugItemDtos = new ArrayList<>();
            while(stmtIterator7.hasNext()) {
                DrugItem drugItemDto = new DrugItem();
                Resource drugResource=stmtIterator7.nextStatement().getResource();
                StmtIterator stmtIterator8 = model.listStatements(new SimpleSelector(drugResource, new PropertyImpl(brandNameUri), (Object) null));
                while (stmtIterator8.hasNext()) {
                    drugItemDto.setBrandName(stmtIterator8.nextStatement().getObject().toString());
                }

                StmtIterator stmtIterator9 = model.listStatements(new SimpleSelector(drugResource, new PropertyImpl(genericName), (Object) null));
                while (stmtIterator9.hasNext()) {
                    drugItemDto.setGenericName(stmtIterator9.nextStatement().getObject().toString());
                }

                StmtIterator stmtIterator10 = model.listStatements(new SimpleSelector(drugResource, new PropertyImpl(pricePropertyUri), (Object) null));
                while (stmtIterator10.hasNext()) {
                    drugItemDto.setPrice(stmtIterator10.nextStatement().getObject().toString());
                }
                drugItemDtos.add(drugItemDto);
            }
            drugItemDetails.setSimilarTo(drugItemDtos);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return drugItemDetails;
    }
}
