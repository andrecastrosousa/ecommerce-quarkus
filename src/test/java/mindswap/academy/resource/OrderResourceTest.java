package mindswap.academy.resource;

import com.thoughtworks.xstream.core.util.OrderRetainingMap;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.order.repository.OrderRepository;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class OrderResourceTest {

    @Inject
    OrderRepository orderRepository;


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
                        .when().get("/orders")
                        .then()
                        .statusCode(200);
            }


    }


}