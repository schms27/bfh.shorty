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
@RequestMapping("/products")
public class StockRestController {
    @Autowired
    StockService stockService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<String> listProducts() {
        return stockService.listProducts();
    }

    @RequestMapping(value = "{index}", method = RequestMethod.GET)
    public @ResponseBody String getProduct(@PathVariable int index) {
        return stockService.getProduct(index);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String addProduct(@RequestBody String product) {
        return stockService.addProduct(product);
    }

    @RequestMapping(value = "{index}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteProduct(@PathVariable int index) {
        stockService.deleteProduct(index);
    }

    @RequestMapping(value = "{index}", method = RequestMethod.PUT)
    public @ResponseBody void updateProduct(@PathVariable int index,@RequestBody String product) {
        stockService.updateProduct(index, product);
    }
}
