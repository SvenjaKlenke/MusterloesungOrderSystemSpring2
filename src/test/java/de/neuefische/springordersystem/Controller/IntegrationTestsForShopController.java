package de.neuefische.springordersystem.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc

class IntegrationTestsForShopController {
    @Autowired
    MockMvc mockMvc;

    @Test
    void allOrders_thenReturnEmptyOrderList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllOrders_thenReturnAllOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1,2]"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[3,4]"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"products\": [\n" +
                        "            {\n" +
                        "                \"id\": 1,\n" +
                        "                \"name\": \"Apfel\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"id\": 2,\n" +
                        "                \"name\": \"Banane\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"products\": [\n" +
                        "            {\n" +
                        "                \"id\": 3,\n" +
                        "                \"name\": \"Zitrone\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"id\": 4,\n" +
                        "                \"name\": \"Mandarine\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    @DirtiesContext
    void whenAddOrderWithProductIds_then200OK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1]"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                                  
                                  "products": [
                                      {
                                          "id": 1,
                                          "name": "Apfel"
                                      }
                                  ]
                        }
                        """)).andExpect(jsonPath("$.id").isNotEmpty());

    }

    @Test
    void allProducts_thenReturnAllProductsList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 4,\n" +
                        "        \"name\": \"Mandarine\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 3,\n" +
                        "        \"name\": \"Zitrone\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"name\": \"Banane\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Apfel\"\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    void getOneProduct_thenReturnOneProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Apfel\"\n" +
                        "    }\n"));
    }

    @Test
    void getOneOrder_thenReturnOneOrder() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1]"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"products\": [\n" +
                        "        {\n" +
                        "            \"id\": 1,\n" +
                        "            \"name\": \"Apfel\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"));
    }

}