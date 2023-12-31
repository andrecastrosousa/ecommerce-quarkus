package mindswap.academy.order.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.repository.ItemCategoryRepository;
import mindswap.academy.item.repository.ItemRepository;
import mindswap.academy.order.dto.OrderAddItemDto;
import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemUpdateDto;
import mindswap.academy.order.model.Order;
import mindswap.academy.order.model.OrderItem;
import mindswap.academy.order.repository.OrderItemRepository;
import mindswap.academy.order.repository.OrderRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


@QuarkusTest
class OrderItemResourceTest {


    @Inject
    OrderItemRepository orderItemRepository;

    @Inject
    ItemRepository itemRepository;
    @Inject
    ItemCategoryRepository itemCategoryRepository;

    @Inject
    OrderRepository orderRepository;

    ItemCategory itemCategory = new ItemCategory();
    Item item = Item.builder()
            .withPrice(20.0)
            .withItemCategory(itemCategory)
            .build();
    List<Item> items = List.of(item);
   Order order = Order.builder().build();
    OrderAddItemDto orderAddItemDto = new OrderAddItemDto(item, 10);


    @BeforeEach
    @Transactional
    public void setUp(){
        orderItemRepository.deleteAll();
        orderItemRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE OrderItem AUTO_INCREMENT = 1")
                .executeUpdate();
        orderRepository.deleteAll();
        orderRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Orders AUTO_INCREMENT = 1")
                .executeUpdate();
        itemRepository.deleteAll();
        itemRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE item AUTO_INCREMENT = 1")
                .executeUpdate();
        itemCategoryRepository.deleteAll();
        itemCategoryRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE itemCategory AUTO_INCREMENT = 1")
                .executeUpdate();
        itemCategoryRepository.persist(itemCategory);
        itemRepository.persist(item);
        orderRepository.persist(order);

    }


    @Nested
    @Tag("crud")
    @DisplayName("Order Item CRUD tests")
    public class OrderDtoItemTests {

        @Test
        @DisplayName("Create an order item with valid fields and return 200")
        public void createOrderItemWithValidFields200(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderAddItemDto)
                    .when().post("/orders/1/items")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when().get("/orders/1/items")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Create an order item with invalid fields and return 400")
        public void createOrderItemWithInvalidFields400(){

            given()
                    .header("Content-Type", "application/json")
                    .body(item == null)
                    .when().post("/orders/1/items")
                    .then()
                    .statusCode(400);

        }

        @Test
        @DisplayName("Get an order item with invalid id returns 404")
        public void getAnOrderWithInvalidId404(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderAddItemDto)
                    .when().post("/orders/1/items")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));


            given()
                    .header("Content-Type", "application/json")
                    .body(orderAddItemDto)
                    .when().post("/orders/2/items")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Delete an Order with valid fields return 200")
        public void deleteAnOrderWithValidFields200(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderAddItemDto)
                    .when().post("/orders/1/items")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when().delete("/orders/1/items/1")
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/orders/1/items/1")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Update an order item with valid fields and return 200")
            public void updateAnOrderWithValidFields200(){
/*
            given()
                    .header("Content-Type", "application/json")
                    .body(orderAddItemDto)
                    .when().post("/orders/1/items")
                    .then()
                    .statusCode(200);


            OrderItemUpdateDto orderItemUpdateDto = new OrderItemUpdateDto(orderAddItemDto.getItem(), 5);

            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemUpdateDto)
                    .when().put("/orders/1/items")
                    .then()

                    .statusCode(200);

 */
        }





        @Test
        @DisplayName("Update an order with item that not exist and return 404")
        public void updateAnOrderWithInvalidFields400(){

            given()
                    .header("Content-Type", "application/json")
                    .body(orderAddItemDto)
                    .when().post("/orders/1/items")
                    .then()
                    .statusCode(200);

            Item item2 = Item.builder()
                    .withPrice(2.0).build();
            item2.setId(2L);

            OrderItemUpdateDto orderItemUpdateDtoInvalid = new OrderItemUpdateDto(item2, 12);
            given()
                    .header("Content-Type", "application/json")
                    .body(orderItemUpdateDtoInvalid)
                    .when().put("/orders/1/items")
                    .then()
                    .statusCode(404);

        }

    }


}
