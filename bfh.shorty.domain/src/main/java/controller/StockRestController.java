package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StockService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon.schmid on 02.11.2017.
 */
@Controller
public class StockRestController {
    @Autowired
    StockService stockService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public @ResponseBody
    List<String> listProducts() {
        return new ArrayList<String>(stockService.listProducts());
    }

    @RequestMapping(value = "/products/{index}", method = RequestMethod.GET)
    public @ResponseBody String getById(@PathVariable int index) {
        return stockService.getProduct(index);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@RequestBody String product) {
        stockService.addProduct(product);
    }

    @RequestMapping(value = "/product/{index}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable int index) {
        stockService.deleteProduct(index);
    }

    @RequestMapping(value = "/product/{index}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int index,@RequestBody String product) {
        stockService.updateProduct(index, product);
    }
}
