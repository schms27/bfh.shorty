import config.AppConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import service.Consumer;
import service.Producer;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


//Load Spring contexte
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class ConsumerTest {

    // Create Mock
    //@InjectMocks
    //@Autowired
    //private Producer producer;

    //@InjectMocks
    //@Autowired
    //private Consumer consumer;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //producer.setProduct("Produkt 1");
        //consumer.consume();
    }

    @Test
    public void testConsumer(){

        // specify mock behave when method called
        //when(producer.produce().thenReturn("product_1");

        //Assert.assertNotNull(consumer);

        //Assert.assertTrue(consumer.getConsumedProduct(0).equals("Produkt 1"));


    }

}
