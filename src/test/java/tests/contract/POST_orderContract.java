package tests.contract;

import factory.orderDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Order;
import tests.AbstractFuncTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class POST_orderContract extends AbstractFuncTest {

    Order orderbody = orderDataFactory.criaOrder();

    @Test
    @DisplayName("API Contract Validation - POST/orders")
    public void postOrdersContractValidation() throws Exception {

        orderbody.setId(123654122);
        given().header("Content-Type", "application/json").body(orderbody)
                .when().post(baseUrl + version + api + "/order")
                .then().statusCode(200).body(matchesJsonSchemaInClasspath("schemas/schema_POST_order.json"));

    }
}