package common;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Base {

    public void GET() {
        Response res =
                given().when().get("http://localhost:3000/posts");

        System.out.println("Get Response is: " + res.asString());
    }

    public void GET(int id) {
        Response res =
                given().when().get("http://localhost:3000/posts/" + id);

        System.out.println("Get Response Is: " + res.asString());
    }

    public void POST(int id, String title, String author) {
        Posts posts = new Posts();
        posts.setId(id);
        posts.setTitle(title);
        posts.setAuthor(author);

        given().when().contentType(ContentType.JSON)
                .body(posts).post("http://localhost:3000/posts");
    }

    public void PUT(int id, String newTitle) {
        Response res =
                given()
                        .body("{ \"title\":\"" + newTitle + "\"}")
                        .when().contentType(ContentType.JSON)
                        .patch("http://localhost:3000/posts/" + id);


        System.out.println(res.asString());


    }

    public void PUT(int id, String newTitle, String newAuthor) {
        Posts posts = new Posts();
        posts.setId(id);
        posts.setTitle(newTitle);
        posts.setAuthor(newAuthor);

        Response res =
                given().when().
                        contentType(ContentType.JSON)
                        .body(posts)
                        .put("http://localhost:3000/posts/" + id);

        System.out.println(res.asString());
    }

    public void DELETE(int id) {
        Posts posts = new Posts();
        posts.setId(id);

        given().when()
                .contentType(ContentType.JSON)
                .body(posts)
                .delete("http://localhost:3000/posts/" + id);
    }
}
