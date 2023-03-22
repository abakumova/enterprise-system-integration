package com.esi.week4refactored;

import com.esi.week4refactored.orders.controller.OrderController;
import com.esi.week4refactored.orders.model.Order;
import com.esi.week4refactored.products.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/* 
@WebMvcTest annotation that can be used for a Spring MVC test that focuses only on Spring MVC components.
Using this annotation will disable full autoconfiguration and instead apply only configuration relevant to MVC tests
(i.e. @Controller,  @JsonComponent, WebMvcConfigurer  beans but not @Service or @Repository beans). 
*/

//Using OrderController.class as parameter, we are asking to initialize only one web controller
//if we did not specify a Controller.class, it will scan all Controller classes in the application
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@WebMvcTest(OrderController.class)
class OrderControllerTests {
    @MockBean
    private OrderController orderController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllOrdersApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/{productId}/orders", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/{productId}/orders/{orderId}", 1, 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void postOrderApiTest() throws Exception {
        Order order = new Order();
        order.setOrder_id("100");
        order.setQuantity(10);
        order.setProduct(new Product());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products/{productId}/orders", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(order))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrderApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/products/orders/{orderId}", 15))
                .andExpect(status().isOk());
    }

    @Test
    void putOrderApiTest() throws Exception {
        Order order = new Order();
        order.setOrder_id("100");
        order.setQuantity(10);
        order.setProduct(new Product());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/products/{productId}/orders/{orderId}", 100, 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(order))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}