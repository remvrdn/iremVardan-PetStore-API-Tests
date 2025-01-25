package petstore;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PetStoreUtils {
    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    public static Response createPet(int petId, String name, String status) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"id\": " + petId + ", \"name\": \"" + name + "\", \"status\": \"" + status + "\"}")
                .when()
                .post("/pet");
    }
    public static Response notCreatePet( String name, String status) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body("")
                .when()
                .post("/pet");
    }

    public static Response getPet(int petId) {
        return RestAssured.given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}");
    }

    public static Response updatePet(int petId, String name, String status) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"id\": " + petId + ", \"name\": \"" + name + "\", \"status\": \"" + status + "\"}")
                .when()
                .put("/pet");
    }
    public static Response notUpdatePetwithNull(int petId, String name, String status) {
        return RestAssured.given()
                .header("Content-Type", "application/xml")
                .body("{, \"name\": \"" + null + "\", \"status\": \"" + null + "\"}")
                .when()
                .put("/pet");
        }

    public static Response deletePet(int petId) {
        return RestAssured.given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}");
    }

    public static Response notDeletePet() {
        return RestAssured.given()
                    .when()
                    .delete("/pet/");


    }
}
