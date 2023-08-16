package mindswap.academy.resource;

import com.thoughtworks.xstream.core.util.OrderRetainingMap;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderUpdatedDto;
import mindswap.academy.order.repository.OrderRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class OrderResourceTest {

    @Inject
    OrderRepository orderRepository;

    OrderCreateDto orderCreateDto = new OrderCreateDto(LocalDateTime.now());

    OrderCreateDto orderInvalidCreateDto = new OrderCreateDto();

    @BeforeEach
    @Transactional
    public void setUp(){
        orderRepository.deleteAll();
        orderRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Orders AUTO_INCREMENT = 1")
                .executeUpdate();
    }

    @Nested
    @Tag("crud")
    @DisplayName("Order CRUD tests")
    public class OrderCrudTest {


        @Test
        @DisplayName("Create an order with valid fields and return 200")
            public void createAnOrderWithValidFields200() {
                given()
                        .header("Content-Type", "application/json")
                        .body(orderCreateDto)
                        .when().post("/orders")
                        .then()
                        .statusCode(200)
                        .body("id", is(1));


                given()
                        .when().get("/orders/1")
                        .then()
                        .statusCode(200);
            }

        @Test
        @DisplayName("Create an order with invalid fields, and return 400")
        public void createAnOrderWithInvalidFields400(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderInvalidCreateDto)
                    .when().post("/orders")
                    .then()
                    .statusCode(400);
        }

        @Test
        @DisplayName("Get order with invalid id and return 404")
        public void getInvalidOrderId404(){
            given()
                    .when().get("/orders/1")
                    .then()
                    .statusCode(404);
        }


        @Test
        @DisplayName("Delete an valid Order and return 200")
        public void deleteAnOrderWithValidFields() {
            given()
                    .header("Content-Type", "application/json")
                    .body(orderCreateDto)
                    .when().post("/orders")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when().delete("/orders/1")
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/orders/1")
                    .then()
                    .statusCode(404);
        }


        @Test
        @DisplayName("Update an Order with valid fields and return 200")
        public void updateOrderWithValidFields200(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderCreateDto)
                    .when().post("/orders")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            OrderUpdatedDto orderUpdatedDto = new OrderUpdatedDto();

            // Esta a dar error por causa do orderUpdate nao ter id, e se meter tem de ser long
            LocalDate date = LocalDate.parse("2023-03-10");
            LocalDateTime dateTime = date.atStartOfDay();
            //mudar o shipping e a unica coisa que posso fazer!

            given()
                    .header("Content-Type", "application/json")
                    .body(orderUpdatedDto)
                    .when().put("/orders/1")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Update an Order with invalid fields and return 400")
        public void updateOrderWithInValidFields200(){
            given()
                    .header("Content-Type", "application/json")
                    .body(orderCreateDto)
                    .when().post("/orders")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            OrderUpdatedDto orderUpdatedDto = new OrderUpdatedDto();

            // Esta a dar error por causa do orderUpdate nao ter id, e se meter tem de ser long
            LocalDate date = LocalDate.parse("2023-03-12");
            LocalDateTime dateTime = date.atStartOfDay();
            orderUpdatedDto.setOrderDatetime(dateTime);

            given()
                    .header("Content-Type", "application/json")
                    .body(orderUpdatedDto)
                    .when().put("/orders/1")
                    .then()
                    .statusCode(400);
        }


    }


}