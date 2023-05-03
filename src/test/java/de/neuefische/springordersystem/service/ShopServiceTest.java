package de.neuefische.springordersystem.service;

import de.neuefische.springordersystem.model.Order;
import de.neuefische.springordersystem.model.Product;
import de.neuefische.springordersystem.repo.OrderRepo;
import de.neuefische.springordersystem.repo.ProductRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShopServiceTest {
    OrderRepo orderRepo = mock (OrderRepo.class);
    ProductRepo productRepo = mock (ProductRepo.class);
    ShopService shopService = new ShopService(productRepo,orderRepo);
    @Test
    void getOrder(){
        //GIVEN
        int orderId = 1;
        Order order = new Order(1, List.of(new Product(1, "Apfel" ), new Product(2, "Banane")));

        //WHEN
        when(orderRepo.getOrder(orderId)).thenReturn(order);
        Order actual = shopService.getOrder(orderId);
        //THEN
        assertEquals(order, actual);
        verify(orderRepo).getOrder(orderId);
    }

}