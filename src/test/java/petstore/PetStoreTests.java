package petstore;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetStoreTests {
    private final int petId = 12345;

    @Test
    public void testCreatePetPositive() {
        Response response = PetStoreUtils.createPet(petId, "Fluffy", "available");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Fluffy");
    }

    @Test
    public void testCreatePetNegative() {
        Response response = PetStoreUtils.notCreatePet( null, null); // Geçersiz veri
        Assert.assertEquals(response.statusCode(), 405);
    }

    @Test
    public void testGetPetPositive() {
        PetStoreUtils.createPet(petId, "Fluffy", "available");
        Response response = PetStoreUtils.getPet(petId);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Fluffy");
    }

    @Test
    public void testGetPetNegative() {
        try{
            // Attempting to update a pet with invalid data to trigger a negative scenario
            PetStoreUtils.getPet(0);
        }catch (Exception e){
            // If the exception contains "404", it means the test passed as this is the expected behavior.
            String errorMessage = e.getMessage();
            if (errorMessage.contains("404")) {
                System.out.println("Test PASSED: Expected exception occurred -> " + errorMessage);
            } else {
                // If the exception contains anything other than "404", the test fails as it is an unexpected scenario.
                System.out.println("Test FAILED: Unexpected exception occurred -> " + errorMessage);
                Assert.fail("Test FAILED: Unexpected exception occurred -> " + errorMessage);
            }
        }
    }

    @Test
    public void testUpdatePetPositive() {
        PetStoreUtils.createPet(petId, "Fluffy", "available");
        Response response = PetStoreUtils.updatePet(petId, "Fluffy", "sold");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status"), "sold");

    }

    @Test
    public void testUpdatePetNegative() {
        try {
            // Attempting to update a pet with invalid data to trigger a negative scenario
            PetStoreUtils.notUpdatePetwithNull(petId,"Fluffy", "available");

        }catch (Exception e){
            String errorMessage = e.getMessage();
            // If the exception contains "400", it means the test passed as this is the expected behavior.
            if (errorMessage.contains("400")) {
                System.out.println("Test PASSED: Expected exception occurred -> " + errorMessage);
            } else {
                // If the exception contains anything other than "400", the test fails as it is an unexpected scenario.
                System.out.println("Test FAILED: Unexpected exception occurred -> " + errorMessage);
                Assert.fail("Test FAILED: Unexpected exception occurred -> " + errorMessage);
            }
        }
    }

    @Test
    public void testDeletePetPositive() {
        PetStoreUtils.createPet(petId, "Fluffy", "available");
        Response response = PetStoreUtils.deletePet(petId);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), String.valueOf(petId));
    }

    @Test
    public void testDeletePetNegative() {
        try{
            PetStoreUtils.notDeletePet();

        }catch (Exception e){
            // Attempting to update a pet with invalid data to trigger a negative scenario
            String errorMessage = e.getMessage();
            // If the exception contains "405", it means the test passed as this is the expected behavior.
            if (errorMessage.contains("405")) {
                System.out.println("Test PASSED: Expected exception occurred -> " + errorMessage);
            } else {
                // If the exception contains anything other than "405", the test fails as it is an unexpected scenario.
                System.out.println("Test FAILED: Unexpected exception occurred -> " + errorMessage);
                Assert.fail("Test FAILED: Unexpected exception occurred -> " + errorMessage);
            }
        }
    }
}
