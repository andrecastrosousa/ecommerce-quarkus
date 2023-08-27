package mindswap.academy.stock.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.repository.ItemCategoryRepository;
import mindswap.academy.item.repository.ItemRepository;
import mindswap.academy.stock.model.Stock;
import mindswap.academy.stock.repository.StockRepository;
import mindswap.academy.supplier.model.Supplier;
import mindswap.academy.supplier.repository.SupplierRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;



@QuarkusTest
class StockResourceTest {

    @Inject
    ItemRepository itemRepository;
    @Inject
    SupplierRepository supplierRepository;
    @Inject
    ItemCategoryRepository itemCategoryRepository;
    @Inject
    StockRepository stockRepository;



    ItemCategory itemCategory = new ItemCategory();
    Item item = Item.builder()
            .withPrice(2.0)
            .withItemCategory(itemCategory)
            .build();

    List<Item> items = List.of(item);

    Supplier supplier = Supplier.builder().build();

    Stock stock = new Stock(item, supplier, 10);


    @BeforeEach
    @Transactional
    public void setup(){
        stockRepository.deleteAll();
        stockRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Stock AUTO_INCREMENT = 1")
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
        itemCategory.setItems(items);
        supplierRepository.deleteAll();
        supplierRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Supplier AUTO_INCREMENT = 1")
                .executeUpdate();
        supplierRepository.persist(supplier);
        stockRepository.persist(stock);
    }

    @Nested
    @Tag("crud")
    @DisplayName("Stock CRUD tests")
    public class StockTests {

        @Test
        @DisplayName("Get an item stock and return 200")
        public void getAnItemStock200(){
            given()
                    .when().get("/items/1/stock")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Try to get a stock from an invalid item and return 404")
        public void tryGetStockFromInvalidItem404(){
            given()
                    .when().get("/items/2/stock")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Get a stock from a correct supplier and return 200")
        public void getStockFromSupplier200(){
            given()
                    .when().get("/supplier/1/stock")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Get a stock from an incorrect supplier and return 404")
        public void getStockFromInvalidSupplier404(){
            given()
                    .when().get("/supplier/2/stock")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Get stock of a valid item from a valid supplier return 200")
        public void getStockItemFromValidSupplier200(){
            given()
                    .when().get("/supplier/1/items/1/stock")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Get stock of an invalid item from a valid supplier return 404")
        public void getStockInvalidItemFromValidSupplier404(){
            given()
                    .when().get("/supplier/1/items/2/stock")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Get stock of a valid item from a invalid supplier return 404")
        public void getStockValidItemFromInValidSupplier404(){
            given()
                    .when().get("/supplier/2/items/1/stock")
                    .then()
                    .statusCode(404);
        }
    }

}