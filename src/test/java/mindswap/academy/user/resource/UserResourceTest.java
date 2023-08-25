package mindswap.academy.user.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import mindswap.academy.user.dto.UserCreateDto;
import mindswap.academy.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.is;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserResourceTest {


    @Inject
    UserRepository userRepository;

    UserCreateDto userCreateDto = new UserCreateDto();


    @BeforeEach
    @Transactional
    public void setUp(){
        userRepository.deleteAll();
        userRepository.getEntityManager()
                .createNativeQuery("ALTER TABLE User AUTO_INCREMENT = 1")
                .executeUpdate();
    }

    @Nested
    @Tag("crud")
    @DisplayName("User CRUD tests")
    public class UserCrudTest {

        @Test
        @DisplayName("Create an user with valid fields and return 200")
        public void creatAnUserWithValidField200() {
            given()
                    .header("Content-Type", "application/json")
                    .body(userCreateDto)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when()
                    .get("/users/1")
                    .then()
                    .statusCode(200);
        }

        @Test
        @DisplayName("Create an user with invalid field, and return 400")
        public void createAnUserWithInvalidFields400() {
            given()
                    .header("Content-Type", "application/json")
                    .body(userCreateDto == null)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(400);
        }

        @Test
        @DisplayName("Get an user with invalid id and return 404")
        public void getInvalidUser404() {
            given()
                    .when()
                    .get("/users/4")
                    .then()
                    .statusCode(404);

        }

        @Test
        @DisplayName("Delete an valid User and return 200")
        public void deleteAnUserWithValidFields() {
            given()
                    .header("Content-Type", "application/json")
                    .body(userCreateDto)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

            given()
                    .when()
                    .delete("/users/1")
                    .then()
                    .statusCode(204);

            given()
                    .when()
                    .get("/users/1")
                    .then()
                    .statusCode(404);
        }

        @Test
        @DisplayName("Deleted an nonexistent user and return 404")
        public void deleteAnNonexistentUser(){
            given().header("Content-Type","application/json");
        }

        @Test
        @DisplayName("Update an User with valid fields and return 200")
        public void updateAnUserWithValidFields200() {
            given()
                    .header("Content-Type", "application/json")
                    .body(userCreateDto)
                    .when()
                    .post("/users")
                    .then()
                    .statusCode(200)
                    .body("id", is(1));

        }
    }
}

