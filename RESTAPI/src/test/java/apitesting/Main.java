package apitesting;

import common.Base;
import common.Info;
import common.Posts;
import common.RestUtilities;
import common.constants.EndPoints;
import common.constants.Path;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Main extends Base {

    /**
     * Start The Server From Terminal:
     * $ json-server --watch db.json
     */

    @Test
    public void testTwo() {
        Response res =
        given().when().get(Path.BASE_URI + EndPoints.POSTS);

        long time = res.then().extract().time();
        System.out.println("Response time is " + time + " milliseconds");

        given().when().get(Path.BASE_URI + EndPoints.POSTS)
                .then().and().time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    public void complexPost() {
        Info info1 = new Info();
        info1.setEmail("rahee@gmail.com");
        info1.setPhone("123456");
        info1.setAddress("GitHub Street");

        Info info2 = new Info();
        info2.setEmail("ibra@gmail.com");
        info2.setPhone("123456");
        info2.setAddress("Grubhub Street");

        Posts posts = new Posts();
        posts.setId(2);
        posts.setTitle("json-server");
        posts.setAuthor("Rahee");
        posts.setInfo(new Info[]{info1, info2});

        Response res =
        given().when().contentType(ContentType.JSON)
                .body(posts).post(Path.BASE_URI + EndPoints.POSTS);

        System.out.println(res.asString());
    }
}