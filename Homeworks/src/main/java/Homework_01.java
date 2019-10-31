import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;
import org.apache.log4j.BasicConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */

public class Homework_01 {

    public static class Task2 {
        void doStuff() {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();

            Resource myData = ResourceFactory.createResource("https://www.linkedin.com/in/konstantin-bogdanoski/");
            model.createResource(myData);
            model.add(myData, VCARD.FN, "Konstantin");
            model.add(myData, VCARD.Region, "Europe");
            model.add(myData, VCARD.Country, "North Macedonia");
            model.add(myData, FOAF.gender, "male");
            model.add(myData, FOAF.firstName, "Konstantin");
            model.add(myData, FOAF.lastName, "Bogdanoski");
            model.add(myData, FOAF.age, "22");
            StmtIterator itr = model.listStatements();

            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.listStatements();");
            while (itr.hasNext())
                System.out.println(itr.nextStatement());
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.write() in RDF/XML");
            model.write(System.out, "RDF/XML");
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with RDFDataMgr.write() in Pretty RDF/XML");
            RDFDataMgr.write(System.out, model, RDFFormat.RDFXML_PRETTY);
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.write() in N-TRIPLES");
            model.write(System.out, "NT");
            System.out.println("=====================================\n\n\n\n");


            System.out.println("\n\n\n\n=====================================");
            System.out.println("Printing with model.write() in TURTLE");
            model.write(System.out, "TTL");
            System.out.println("=====================================\n\n\n\n");
        }
    }

    public static class Task3 {
        void doStuff() throws FileNotFoundException {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();
            InputStream is = new FileInputStream("/home/k-bogdanoski/Desktop/FCSE/КНИ 7 Семестар/Веб Базирани Системи/Вежби/Homeworks/src/main/resources/H01_Task3.xml");
            model.read(is, "RDFXML");
            StmtIterator it = model.listStatements();
            while (it.hasNext())
                System.out.println(it.nextStatement());
        }
    }

    public static class Task4 {
        void doStuff() throws FileNotFoundException {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();
            InputStream is = new FileInputStream("/home/k-bogdanoski/Desktop/FCSE/КНИ 7 Семестар/Веб Базирани Системи/Вежби/Homeworks/src/main/resources/H01_Task3.xml");
            model.read(is, "RDFXML");
            Resource resource = model.getResource("https://www.linkedin.com/in/konstantin-bogdanoski/");
            System.out.println(resource.getProperty(FOAF.firstName));
            System.out.println(resource.getProperty(FOAF.age));
            System.out.println(resource.getURI());
        }
    }

    public static class Task5 {
        void task5_22() throws FileNotFoundException {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();

            InputStream is = new FileInputStream("/home/k-bogdanoski/Desktop/FCSE/КНИ 7 Семестар/Веб Базирани Системи/Вежби/Homeworks/hifm-dataset.ttl");
            model.read(is, RDFFormat.TURTLE.toString(), "TURTLE");

            ResIterator iterator = model.listResourcesWithProperty(RDFS.label);

            List<String> medicines = new ArrayList<>();

            while (iterator.hasNext())
                medicines.add(iterator.nextResource().getProperty(RDFS.label).getLiteral().getString());

            ArrayList<String> medicinesSorted = (ArrayList<String>) medicines.stream().distinct().collect(Collectors.toList());
            Collections.sort(medicinesSorted);

            System.out.println("\n\n\n\n=====================================\n\n\n\n");
            System.out.println("TASK 5.22 Printing ALL medicines (SORTED & DISTINCT)");
            System.out.println("\n\n\n\n=====================================");
            medicinesSorted.forEach(System.out::println);
            System.out.println("=====================================\n\n\n\n");
        }

        void task5_23() throws FileNotFoundException {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();

            InputStream is = new FileInputStream("/home/k-bogdanoski/Desktop/FCSE/КНИ 7 Семестар/Веб Базирани Системи/Вежби/Homeworks/hifm-dataset.ttl");
            model.read(is, RDFFormat.TURTLE.toString(), "TURTLE");

            StmtIterator iterator = model.listStatements();

            System.out.println("\n\n\n\n=====================================\n\n\n\n");
            System.out.println("TASK 5.23 Printing PARACETAMOL - relations and values");
            System.out.println("\n\n\n\n=====================================");

            while (iterator.hasNext()) {
                Statement statement = iterator.nextStatement();
                if (statement.getObject().isLiteral() && statement.getLiteral().getString().equals("Paracetamol"))
                    System.out.println(statement);
            }

            System.out.println("=====================================\n\n\n\n");
        }

        void task5_24() throws FileNotFoundException {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();

            InputStream is = new FileInputStream("/home/k-bogdanoski/Desktop/FCSE/КНИ 7 Семестар/Веб Базирани Системи/Вежби/Homeworks/hifm-dataset.ttl");
            model.read(is, RDFFormat.TURTLE.toString(), "TURTLE");

            Property similarTo = new PropertyImpl("http://purl.org/net/hifm/ontology#similarTo");
            AtomicReference<StmtIterator> iterator = new AtomicReference<>(model.listStatements());

            System.out.println("\n\n\n\n=====================================\n\n\n\n");
            System.out.println("TASK 5.23 Printing SALBUTAMOL - similarTo");
            System.out.println("\n\n\n\n=====================================");

            //List with the URIs of SALBUTAMOL
            List<Resource> salbutamol = new ArrayList<>();

            while (iterator.get().hasNext()) {
                Statement statement = iterator.get().nextStatement();
                if (statement.getObject().isLiteral() && statement.getLiteral().getString().contains("Salbutamol"))
                    salbutamol.add(statement.getSubject());
            }

            ArrayList<Resource> salbutamolDistinct = (ArrayList<Resource>) salbutamol.stream().distinct().collect(Collectors.toList());

            salbutamolDistinct.forEach(x -> {
                List<String> similarMeds = new ArrayList<>();
                String xName = x.getProperty(RDFS.label).getString();
                System.out.println("\n\n=====================================\nPRINTING FOR: " + xName + " URI:" + x.toString());
                iterator.set(model.listStatements());
                while (iterator.get().hasNext()) {
                    Statement statement = iterator.get().nextStatement();
                    //System.out.println(statement);
                    Statement med = statement.getSubject().getProperty(similarTo);
                    if (med != null) {
                        if (med.getSubject().equals(x)) {
                            String newLine = "similarTo "
                                    + med.getObject().asResource().getProperty(RDFS.label).getString()
                                    + " URI: " + med.getObject();
                            if (!similarMeds.contains(newLine))
                                similarMeds.add(newLine);
                        }
                    }
                }
                similarMeds.forEach(System.out::println);
            });
            System.out.println("=====================================\n\n\n\n");
        }

        void task5_25() throws FileNotFoundException {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();

            InputStream is = new FileInputStream("/home/k-bogdanoski/Desktop/FCSE/КНИ 7 Семестар/Веб Базирани Системи/Вежби/Homeworks/hifm-dataset.ttl");
            model.read(is, RDFFormat.TURTLE.toString(), "TURTLE");

            Property refPrice = new PropertyImpl("http://purl.org/net/hifm/ontology#refPriceWithVAT");
            StmtIterator iterator = model.listStatements();
            ResIterator priceIterator = model.listResourcesWithProperty(refPrice);

            List<Resource> salbutamol = new ArrayList<>();

            while (iterator.hasNext()) {
                Statement statement = iterator.nextStatement();
                if (statement.getObject().isLiteral() && statement.getLiteral().getString().contains("Salbutamol"))
                    salbutamol.add(statement.getSubject());
            }

            ArrayList<Resource> salbutamolDistinct = (ArrayList<Resource>) salbutamol.stream().distinct().collect(Collectors.toList());
            iterator = model.listStatements();

            for (Resource resource : salbutamolDistinct) {
                priceIterator = model.listResourcesWithProperty(refPrice);
                while (priceIterator.hasNext()) {
                    Resource temp = priceIterator.nextResource();
                    String medName = resource.getProperty(RDFS.label).getString();
                    //System.out.println(medName);
                    if (temp.getURI().equals(resource.toString()))
                        System.out.println(medName + " : " + temp.getProperty(refPrice).getString() + " den.");
                }
            }


        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //For you to test out the tasks, just uncomment the specific task you wish to test

        //Task2 task2 = new Task2();
        //task2.doStuff();

        //Task3 task3 = new Task3();
        //task3.doStuff();

        //Task4 task4 = new Task4();
        //task4.doStuff();

        //Task5 task5 = new Task5();
        //task5.task5_22();
        //task5.task5_23();
        //task5.task5_24();
        //task5.task5_25();
    }
}
