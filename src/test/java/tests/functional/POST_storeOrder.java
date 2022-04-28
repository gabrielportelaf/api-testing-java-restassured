package tests.functional;

import factory.orderDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Order;
import tests.AbstractFuncTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class POST_storeOrder extends AbstractFuncTest {

    Order orderbody = orderDataFactory.criaOrder();

    @Test
    @DisplayName("Validates the ID field")
    public void testOrderCreationValidatingIdField() throws Exception {

        orderbody.setId(123654122);
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(200).body("id", is(123654122)).body("status", containsString("placed"));

        orderbody.setId(null);
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(400);
        //should return status code 400, but there is no validation in the backend

    }

    @Test
    @DisplayName("Validates the Status field")
    public void testOrderCreationValidatingStatusField() throws Exception {

        orderbody.setStatus("placed");
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(200).body("id", is(1)).body("status", containsString("placed"));

        orderbody.setStatus("approved");
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(200).body("id", is(1)).body("status", containsString("approved"));

        orderbody.setStatus("delivered");
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(200).body("id", is(1)).body("status", containsString("delivered"));

        orderbody.setStatus("anything");
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(400);
        //should return status code 400, but there is no validation in the backend

        orderbody.setStatus("");
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(400);
        //should return status code 400, but there is no validation in the backend

        orderbody.setStatus(null);
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(400);
        //should return status code 400, but there is no validation in the backend

    }
}
