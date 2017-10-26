package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon.schmid on 19.10.2017.
 */
@Service
public class Consumer implements ConsumerService{

    @Autowired
    private ProducerService producerService;

    private List<String> consumed = new ArrayList<String>();

    @Override
    public void consume() {
        consumed.add(producerService.produce());
    }

    public String getConsumedProduct(int index){
        return consumed.get(index);
    }
}
