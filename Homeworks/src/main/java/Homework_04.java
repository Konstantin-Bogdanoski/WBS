import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.log4j.BasicConfigurator;

import java.io.UnsupportedEncodingException;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public class Homework_04 {
    public static class Task_1 {
        public void task_1() throws UnsupportedEncodingException {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();
            /*based on Exercises
            RDFParser
                    .source("http://dbpedia.org/resource/Red_Hot_Chili_Peppers")
                    .httpAccept("application/ld+json")
                    .parse(model.getGraph());
                    */
            /*based on Task
            model.read("http://dbpedia.org/data/Red_Hot_Chili_Peppers", "RDF/XML");
            model.write(System.out, "TURTLE");*/


            //Reified data from pyRdfa & pyMicrodata
            String s1 = "https://www.w3.org/2012/pyMicrodata/extract?format=turtle&uri=http%3A%2F%2Fdbpedia.org%2Fresource%2FRed_Hot_Chili_Peppers";
            String s2 = "https://www.w3.org/2012/pyRdfa/extract?format=turtle&uri=http%3A%2F%2Fdbpedia.org%2Fresource%2FRed_Hot_Chili_Peppers";

            Model m1 = ModelFactory.createDefaultModel();
            m1.read(s1, "TURTLE");
            Model m2 = ModelFactory.createDefaultModel();
            m2.read(s2, "TURTLE");
            Model m = m1.union(m2);
            //System.out.println("=================================================");
            //System.out.println("Printing Red Hot Chili Peppers RDF Graph - Turtle");
            //System.out.println("=================================================");
            //m.write(System.out, "TURTLE");
            //System.out.println(m.getResource("http://dbpedia.org/resource/Dark_Necessities"));

            System.out.println("=================================================");
            System.out.println("Printing Dark Necessities RDF Graph - Turtle");
            System.out.println("=================================================");
            Resource darkN = m.getResource("http://dbpedia.org/resource/Dark_Necessities");
            Model m3 = ModelFactory.createDefaultModel();
            m3.read(darkN.getURI());
            //m3.write(System.out, "TURTLE");

            Resource brendansDeathSong = m3.getResource("http://dbpedia.org/resource/Brendan\u0027s_Death_Song");
            //System.out.println(brendansDeathSong);

            Model m4 = ModelFactory.createDefaultModel();
            m4.read(brendansDeathSong.getURI());
            //m4.write(System.out, "TURTLE");

            Property mainName = m4.getProperty("foaf:name");
            //Loop name
            m4.listResourcesWithProperty(mainName).forEachRemaining(System.out::println);

            Property writer = m4.getProperty("dbo:writer");
            //Loop that will print all the writers of the song
            m4.listResourcesWithProperty(writer).forEachRemaining(System.out::println);

            Property recorded = m4.getProperty("dbp:recorded");
            //Loop that will print all values for the property: dbo:recorded
            m4.listResourcesWithProperty(recorded).forEachRemaining(System.out::println);
        }
    }

    public static class Task_2 {
        public void task_2() {
            BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();
            String file = "/home/konstantin/Desktop/FCSE/WBS/Labs/Homeworks/hifm-dataset.ttl";
            String SparqlEndpoint = "http://wifo5-04.informatik.uni-mannheim.de/drugbank/sparql";
            model.read(file, "TURTLE");
            model.write(System.out, "TURTLE");
            String queryString = "SELECT * " +
                    "WHERE { " +
                    "?drug rdfs:label \"Cefuroxime\"; " +
                    "rdfs:label ?label. " +
                    "} ";
            Query query = QueryFactory.create(queryString);
            //try (QueryExecution queryExecution = new QueryExecutionFactory.sparqlService(SparqlEndpoint, query)) {
            //    ResultSet resultSet = queryExecution.execSelect();
            //    while (resultSet.hasNext()) {
            //        QuerySolution querySolution = resultSet.nextSolution();
            //        System.out.println("==============================================");
            //        System.out.println("Drug: " + querySolution.get("drug").toString());
            //        System.out.println("Label: " + querySolution.get("label").toString());
            //    }
            //}
        }
    }

    public static void main(String[] args) {
        Task_1 task_1 = new Task_1();
        Task_2 task_2 = new Task_2();
        try {
            //task_1.task_1();
            task_2.task_2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
