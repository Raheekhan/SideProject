package apitesting;

import common.Base;
import common.Info;
import common.PostsTwo;
import common.constants.EndPoints;
import common.constants.Path;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Main extends Base {

    /**
     * Start The Server From Terminal:
     * $ json-server --watch db.json
     */

    @Test
    public void test() {
       GET();
    }

    @Test
    public void testTwo() {
        Info info = new Info();
        info.setEmail("rahee@gmail.com");
        info.setPhone("123456");
        info.setAddress("GitHub Street");

        PostsTwo posts = new PostsTwo();
        posts.setId(2);
        posts.setTitle("json-server");
        posts.setAuthor("Rahee");
        posts.setInfo(info);

        Response res =
        given().when().contentType(ContentType.JSON)
                .body(posts).post(Path.BASE_URI + EndPoints.POSTS);

        System.out.println(res.asString());
    }
}