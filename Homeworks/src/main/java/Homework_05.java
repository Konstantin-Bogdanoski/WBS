import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.log4j.BasicConfigurator;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public class Homework_05 {
    public static class Task_2 {
        public void task_2() {
            //BasicConfigurator.configure();
            Model model = ModelFactory.createDefaultModel();
            model.read("https://www.dropbox.com/s/e1zxx3fbv1n25t1/foaf.ttl?dl=1", "TURTLE");
            //model.write(System.out, "TURTLE");
            model.listResourcesWithProperty(model.createProperty("foaf:knows")).forEachRemaining(System.out::println);
        }
    }

    public static void main(String[] args) {
        Task_2 task_2 = new Task_2();
        task_2.task_2();
    }
}
