package Api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;




public class UserApi extends ApiUrls {


    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return spec()
                .body(user)
                .post(CREATE_USER)
                .then().log().all();
    }



    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return spec()
                .auth().oauth2(accessToken.substring(accessToken.indexOf(" ") + 1))
                .delete(CHANGE_USER)
                .then().log().all();
    }



}
