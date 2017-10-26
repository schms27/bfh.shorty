import config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.Consumer;
import service.ConsumerService;
import service.ConsumerTester;

/**
 * Created by simon.schmid on 19.10.2017.
 */
public class Application {

    @Autowired
    ConsumerService service;

    public static void main(String[] args){
        /*ConsumerTester tester = new ConsumerTester();
        tester.test();*/

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsumerService consumerService = context.getBean(ConsumerService.class);
        consumerService.consume();

        ApplicationContext context2 = new ClassPathXmlApplicationContext("serviceConfig.xml");
        ConsumerService consumerService2 = context2.getBean(ConsumerService.class);
        consumerService2.consume();
    }

}

