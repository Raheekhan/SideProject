package common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class RestUtilities {

    public static String ENDPOINT;
    public static RequestSpecBuilder REQUEST_BUILDER;
    public static RequestSpecification REQUEST_SPEC;
    public static ResponseSpecBuilder RESPONSE_BUILDER;
    public static ResponseSpecification RESPONSE_SPEC;

    public static void setEndPoint(String endPoint) {
        ENDPOINT = endPoint;
    }

    public static RequestSpecification getRequestSpecification() {
        return REQUEST_SPEC;
    }

    public static ResponseSpecification getResponseSpecification() {
        return RESPONSE_SPEC;
    }

    public static RequestSpecification createQueryParam(RequestSpecification rspec, String param, String value) {
        return rspec.queryParam(param, value);
    }

    public static RequestSpecification createPathParam(RequestSpecification rspec, String param, String value) {
        return rspec.pathParam(param, value);
    }

    public static Response getResponse() {
        return given().get(ENDPOINT);
    }

    public static Response getResponse(RequestSpecification reqSpec, String type) {
        REQUEST_SPEC.spec(reqSpec);
        Response response = null;
        if (type.equalsIgnoreCase("get")) {
            response = given().spec(REQUEST_SPEC).get(ENDPOINT);
        } else if (type.equalsIgnoreCase("post")) {
            response = given().spec(REQUEST_SPEC).post(ENDPOINT);
        } else if (type.equalsIgnoreCase("put")) {
            response = given().spec(REQUEST_SPEC).put(ENDPOINT);
        }  else if (type.equalsIgnoreCase("delete")) {
            response = given().spec(REQUEST_SPEC).delete(ENDPOINT);
        } else {
            System.out.println("Type Not Supported");
        }
        response.then().spec(RESPONSE_SPEC);
        return response;
    }

    public static JsonPath getJsonPath(Response res) {
        String path = res.asString();
        return new JsonPath(path);
    }

    public static XmlPath getXmlPath(Response res) {
        String path = res.asString();
        return new XmlPath(path);
    }

    public static void setContentType(ContentType type) {
        given().contentType(type);
    }
}
