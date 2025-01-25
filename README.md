# PetStore API Tests

This repository contains a comprehensive suite of automated API tests for the **PetStore** endpoints provided by [Swagger PetStore API](https://petstore.swagger.io/). The tests cover **CRUD operations** (Create, Read, Update, Delete) with both **positive** and **negative** scenarios, ensuring robust validation of the API's functionality.

---

## **Project Structure**

The project is organized as follows:

```
src
├── main
│   └── java
│       └── org.example.petstore
│           ├── PetStoreUtils.java  # Utility methods for CRUD operations
├── test
    └── java
        └── petstore
            └── PetStoreTests.java  # Test cases for positive and negative scenarios
```

- **`PetStoreUtils.java`:**
  Contains reusable methods for CRUD operations (Create, Read, Update, Delete) on the PetStore API.
  - Example Methods:
    - `createPet()`
    - `getPet()`
    - `updatePet()`
    - `deletePet()`

- **`PetStoreTests.java`:**
  Includes test cases for both **positive** and **negative** scenarios.

---

## **Setup and Installation**

Follow these steps to set up and run the project:

1. Clone the repository:
   ```bash
   git clone https://github.com/remvrdn/iremVardan-PetStore-API-Tests.git
   cd iremVardan-PetStore-API-Tests
   ```

2. Install dependencies using Maven:
   ```bash
   mvn clean install
   ```

3. Run the tests:
   ```bash
   mvn test
   ```

---

## **Key Features**

### 1. Positive Scenarios
- **Create Pet:** Add a pet with valid data and verify its creation.
- **Read Pet:** Fetch details of an existing pet and validate the response.
- **Update Pet:** Update pet details and ensure the changes are reflected.
- **Delete Pet:** Delete an existing pet and confirm the deletion.

### 2. Negative Scenarios
- **Invalid Data Handling:** Send incomplete or invalid payloads to the API.
- **Non-existent Resources:** Attempt to fetch, update, or delete a pet that does not exist.
- **Error Code Validation:** Ensure appropriate error codes (404, 400, etc.) are returned for invalid requests.

---

## **Example Test Cases**

### Positive Scenario: Create a New Pet
```java
@Test
public void testCreatePetPositive() {
    Response response = PetStoreUtils.createPet(12345, "Fluffy", "available");
    Assert.assertEquals(response.statusCode(), 200);
    Assert.assertEquals(response.jsonPath().getString("name"), "Fluffy");
}
```

### Negative Scenario: Attempt to Fetch a Non-existent Pet
```java
@Test
public void testCreatePetNegative() {
    Response response = PetStoreUtils.notCreatePet( null, null); // Geçersiz veri
    Assert.assertEquals(response.statusCode(), 405);
}
```

---

## **Test Execution**

### Running Tests with Maven
To execute all tests:
```bash
mvn test
```

### Viewing Test Results
After running the tests, results will be displayed in the terminal. If any test fails, detailed logs will be provided for debugging.

---

## **Future Enhancements**

- Add support for authentication if required.
- Extend test coverage for edge cases.
- Integrate with CI/CD pipelines for continuous testing (e.g., GitHub Actions, Jenkins).
- Generate detailed test reports using tools like **Allure** or **ExtentReports**.

---

## **Contributors**

- **Irem Vardan**
  - API Testing Expert
  - [GitHub Profile](https://github.com/remvrdn)

---

## **License**

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
