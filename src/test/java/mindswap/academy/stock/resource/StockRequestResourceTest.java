package mindswap.academy.stock.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.repository.ItemRepository;
import mindswap.academy.stock.dto.StockRequestCreateDto;
import mindswap.academy.stock.model.Stock;
import mindswap.academy.stock.repository.StockRepository;
import mindswap.academy.stock.repository.StockRequestRepository;
import mindswap.academy.supplier.model.Supplier;
import mindswap.academy.supplier.repository.SupplierRepository;
import mindswap.academy.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StockRequestResourceTest {

    @Inject
    StockRequestRepository stockRequestRepository;
    @Inject
    ItemRepository itemRepository;
    @Inject
    StockRepository stockRepository;

    @Inject
    SupplierRepository supplierRepository;
    Item item = new Item();
    Supplier supplier = new Supplier();
    StockRequestCreateDto stockRequestCreateDto = new StockRequestCreateDto(supplier, item);
    Stock stock = new Stock(item, supplier);
    @BeforeEach
    @Transactional
    public void setup(){
        item.setPrice(2.5);

        stockRequestRepository.deleteAll();
        stockRequestRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE StockRequest AUTO_INCREMENT = 1")
                .executeUpdate();
        stockRepository.deleteAll();
        stockRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Stock AUTO_INCREMENT = 1")
                .executeUpdate();
        supplierRepository.deleteAll();
        supplierRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Supplier AUTO_INCREMENT = 1")
                .executeUpdate();
        supplierRepository.persist(supplier);
        itemRepository.deleteAll();
        itemRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Items AUTO_INCREMENT = 1")
                .executeUpdate();
        itemRepository.persist(item);
        stockRepository.persist(stock);
    }

    @Nested
    @Tag("crud")
    @DisplayName("Stock request CRUD tests")
    public class StockRequestCrudTest {
        @Test
        @DisplayName("Create an Stock Request with valid fields and return 200")
        public void creatAnStockRequestWithValidField200() {
            given()
                    .header("Content-Type", "application/json")
                    .body(stockRequestCreateDto)
                    .when()
                    .post("/stockRequest")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when()
                    .get("/stockRequest/1")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Create an Stock Request with invalid fields and return 400")
        public void createAnStockRequestWithInvalidFields400() {
            given()
                    .header("Content-Type", "application/json")
                    .body(supplier == null)
                    .when()
                    .post("/stockRequest")
                    .then()
                    .statusCode(400);

        }

        @Test
        @DisplayName("Get an Stock Request with invalid id and return 404")
        public void getInvalidStockRequest404() {
            given()
                    .when()
                    .get("/stockRequest/2")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Delete an valid Stock Request and return 200")
        void deleteAnStockRequestWithValidFields() {
            given()
                    .header("Content-Type", "application/json")
                    .body(stockRequestCreateDto)
                    .when()
                    .post("/stockRequest")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));
            given()
                    .when()
                    .delete("/stockRequest/1")
                    .then()
                    .statusCode(204);

            given()
                    .when()
                    .get("/stockRequest/1")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Deleted an nonexistent Stock Request and return 404")
        public void deleteAnNonexistentStockRequest(){
            given()
                    .header("Content-Type","application/json");
        }

        @Test
        @DisplayName("Update an Stock Request with valid fields and return 200")
        public void updateAnStockRequestWithValidFields200() {
            given()
                    .header("Content-Type", "application/json")
                    .body(stockRequestCreateDto)
                    .when()
                    .post("/stockRequest")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

        }

    }
}