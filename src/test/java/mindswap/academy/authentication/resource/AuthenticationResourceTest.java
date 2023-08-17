package mindswap.academy.authentication.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.authentication.dto.AuthRegisterDto;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AuthenticationResourceTest {

    AuthRegisterDto authRegisterDto = new AuthRegisterDto("andre@gmail.com", "123", "andre", "sousa");

    @Test
    public void test() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authRegisterDto)
                .when()
                .post("/register")
                .then()
                .statusCode(204);
    }
}
