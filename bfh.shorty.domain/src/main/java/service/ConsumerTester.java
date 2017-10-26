package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by simon.schmid on 19.10.2017.
 */
@Component
public class ConsumerTester {

    @Autowired
    ConsumerService consumer;

    public void test(){
        consumer.consume();
        //System.out.println(consumer.getConsumedProduct(0));
    }
}
