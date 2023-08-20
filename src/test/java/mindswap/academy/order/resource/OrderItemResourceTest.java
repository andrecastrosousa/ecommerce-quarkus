package mindswap.academy.order.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.repository.ItemRepository;
import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemUpdatedDto;
import mindswap.academy.order.model.Order;
import mindswap.academy.order.model.OrderItem;
import mindswap.academy.order.repository.OrderItemRepository;
import mindswap.academy.order.repository.OrderRepository;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class OrderItemResourceTest {


    @Inject
    OrderItemRepository orderItemRepository;

    @Inject
    ItemRepository itemRepository;

    @Inject
    OrderRepository orderRepository;

   Item item = Item.builder()
           .withPrice(20.0).build();

   Order order = new Order();


   OrderItemCreateDto orderItemCreateDto = new OrderItemCreateDto(order, item, 10);

    OrderItemCreateDto orderItemInvalidCreateDto = new OrderItemCreateDto(order,null, 10);

    @BeforeEach
    @Transactional
    public void setUp(){
        orderItemRepository.deleteAll();
        orderItemRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE OrderItem AUTO_INCREMENT = 1")
                .executeUpdate();
        itemRepository.deleteAll();
        itemRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Items AUTO_INCREMENT = 1")
                .executeUpdate();
        itemRepository.persist(item);
        orderRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Orders AUTO_INCREMENT = 1")
                .executeUpdate();
        orderRepository.persist(order);
    }

    @Nested
    @Tag("crud")
    @DisplayName("Order Item CRUD tests")
    public class OrderItemTests {

        @Test
        @DisplayName("Create an order item with valid fields and return 200")
        public void createOrderItemWithValidFields200(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemCreateDto)
                    .when().post("/orders/orderItem")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when().get("/orders/orderItem/1")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Create an order item with invalid fields and return 400")
        public void createOrderItemWithInvalidFields400(){
                given()
                        .header("Content-Type", "application/json")
                        .body(orderItemInvalidCreateDto)
                        .when().post("/orders/orderItem")
                        .then()
                        .statusCode(400);
        }

        @Test
        @DisplayName("Get an order item with invalid id returns 404")
        public void getAnOrderWithInvalidId404(){
            given()
                    .when().get("/orders/orderItem/1")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Delete an Order with valid fields return 200")
        public void deleteAnOrderWithValidFields200(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemCreateDto)
                    .when().post("/orders/orderItem")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when().delete("/orders/orderItem/1")
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/orders/orderItem/1")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Update an order item with valid fields and return 200")
            public void updateAnOrderWithValidFields200(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemCreateDto)
                    .when().post("/orders/orderItem")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            OrderItemUpdatedDto orderItemUpdatedDto = new OrderItemUpdatedDto(1L, order, item, 15);

            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemUpdatedDto)
                    .when().put("/orders/orderItem/1")
                    .then()
                    .statusCode(200);

        }

        @Test
        @DisplayName("Update an order item with invalid fields and return 400")
        public void updateAnOrderWithInvalidFields400(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemCreateDto)
                    .when().post("/orders/orderItem")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            OrderItemUpdatedDto orderItemUpdatedDto = new OrderItemUpdatedDto(2L, order, item, 15);

            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemUpdatedDto)
                    .when().put("/orders/orderItem/1")
                    .then()
                    .statusCode(400);

        }

    }


}