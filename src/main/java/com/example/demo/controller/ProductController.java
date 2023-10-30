package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/products")
    public String  saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException, IOException {
        return productService.insert(product);
    }

    @PostMapping("/products/{name}")
    public Product getProduct(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productService.getProductDetails(name);
    }

    @PutMapping("/products")
    public String updateProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products/{name}")
    public String deleteProduct(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productService.deleteProduct(name);
    }

    @GetMapping("/products")
    public List<Product> getAll() throws ExecutionException, InterruptedException {
        return productService.getProductDetails();
    }

}
