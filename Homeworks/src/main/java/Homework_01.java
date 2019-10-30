import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;
import org.apache.log4j.BasicConfigurator;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */

public class Homework_01 {
    public static void main(String[] args) {
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
