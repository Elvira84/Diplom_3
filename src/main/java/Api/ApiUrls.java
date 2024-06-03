package Api;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiUrls {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String CREATE_USER = "/api/auth/register";
    public static final String CHANGE_USER = "/api/auth/user";

    public static RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }

}
