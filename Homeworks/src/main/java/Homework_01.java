import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;
import org.apache.log4j.BasicConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
            InputStream is = new FileInputStream("/home/k-bogdanoski/Desktop/FCSE/КНИ 7 Семестар/Веб Базирани Системи/Вежби/Homeworks/src/main/resources/H01_Task3.ttl");
            model.read(is, "TURTLE", RDFFormat.TURTLE.toString());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Task2 task2 = new Task2();
        //task2.doStuff();

        Task3 task3 = new Task3();
        //TODO: Fix ERROR: [[ Failed to determine the RDF syntax (.lang or .base required) ]]
        task3.doStuff();
    }
}
