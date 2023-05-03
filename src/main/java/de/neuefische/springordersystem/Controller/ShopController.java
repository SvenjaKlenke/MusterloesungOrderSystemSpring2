package de.neuefische.springordersystem.Controller;

import de.neuefische.springordersystem.model.Order;
import de.neuefische.springordersystem.model.Product;
import de.neuefische.springordersystem.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping ("products")
    public List allProducts (){
        return shopService.listProducts();

    }

    @GetMapping ("products/{id}")
    public Product getOneProduct (@PathVariable int id){
        return shopService.getProduct(id);

    }

    @GetMapping ("orders")
    public List allOrders (){
        return shopService.listOrders();

    }

    @GetMapping ("orders/{id}")
    public Order getOneOrder (@PathVariable int id){
        return shopService.getOrder(id);

    }

    @PostMapping ("orders/{id}()")
    public void addOrder (@PathVariable int id, @RequestBody List<Integer> productIds){
          shopService.addOrder(id, productIds);

    }







}
