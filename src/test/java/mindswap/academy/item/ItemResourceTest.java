package mindswap.academy.item;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.item.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

@QuarkusTest
@DisplayName("Item resource test")
public class ItemResourceTest {
    @Inject
    ItemRepository itemRepository;

    @BeforeEach
    @Transactional
    void setup(){
        itemRepository.deleteAll();
        itemRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE Items AUTO_INCREMENT = 1")
                .executeUpdate();
    }

    @Test
    public void testGetEndpoint() {

        given()
                .when().get("/item")
                .then()
                .statusCode(200);
    }
}
