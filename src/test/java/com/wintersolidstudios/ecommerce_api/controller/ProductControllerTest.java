package com.wintersolidstudios.ecommerce_api.controller;


import com.wintersolidstudios.ecommerce_api.dto.CreateProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateProduct() throws Exception {

        CreateProductRequest request = CreateProductRequest.builder()
                .name("Keyboard")
                .description("Mechanical Keyboard")
                .price(99.99)
                .category("Electronics")
                .stockQuantity(10)
                .build();

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.category").value("Electronics"));
    }

    @Test
    void shouldGetAllProducts() throws Exception {

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetProductById() throws Exception {

        // create product
        CreateProductRequest request = CreateProductRequest.builder()
                .name("Mouse")
                .description("Gaming Mouse")
                .price(49.99)
                .category("Electronics")
                .stockQuantity(5)
                .build();

        String response = mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // extract id
        JsonNode node =
                objectMapper.readTree(response);

        Long id = node.get("id").asLong();

        mockMvc.perform(get("/api/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mouse"));
    }

  //  @Test
 //  void shouldDeleteProduct() throws Exception {

 //      CreateProductRequest request = CreateProductRequest.builder()
 //              .name("Headset")
 //              .description("Gaming Headset")
 //              .price(79.99)
 //              .category("Electronics")
 //              .stockQuantity(3)
 //              .build();

 //      String response = mockMvc.perform(post("/api/products")
 //                      .contentType(MediaType.APPLICATION_JSON)
 //                      .content(objectMapper.writeValueAsString(request)))
 //              .andReturn()
 //              .getResponse()
 //              .getContentAsString();

 //      Long id = objectMapper.readTree(response).path("id").asLong();

 //      mockMvc.perform(delete("/api/products/" + id))
 //              .andExpect(status().isNoContent());

 //      mockMvc.perform(get("/api/products/" + id))
 //              .andExpect(status().isNotFound());
 //  }
}