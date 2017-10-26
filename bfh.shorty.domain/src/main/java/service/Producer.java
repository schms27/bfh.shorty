package service;

import org.springframework.stereotype.Service;

/**
 * Created by simon.schmid on 19.10.2017.
 */
@Service
public class Producer implements ProducerService{

    private String product;

    @Override
    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String produce() {
        return product;
    }
}
