package service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon.schmid on 02.11.2017.
 */
@Service
public class StockServiceImpl implements StockService {
    private  List<String> products = new ArrayList<>();

    @Override
    public List<String> listProducts() {
        return products;
    }

    @Override
    public String getProduct(int position) {
        return products.get(position);
    }

    @Override
    public String addProduct(String todo) {
        products.add(todo);
        return todo;
    }

    @Override
    public void deleteProduct(int position) {
        products.remove(position);
    }

    @Override
    public void updateProduct(int position, String todo) {
        products.set(position,todo);
    }
}
