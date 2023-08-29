package br.com.fatec.FatecProjectRestAPI.controller;

import br.com.fatec.FatecProjectRestAPI.entity.Product;
import br.com.fatec.FatecProjectRestAPI.exception.ResponseGenericException;
import br.com.fatec.FatecProjectRestAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/list")
    public ResponseEntity<Object> list(){
        List<Product> result = productService.getInfoProducts();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseGenericException.response(result));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product){
        Product result = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseGenericException.response(result));
    }
}
