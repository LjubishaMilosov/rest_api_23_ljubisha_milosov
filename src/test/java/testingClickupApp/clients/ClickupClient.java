package testingClickupApp.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static testingClickupApp.constants.ProjectConstants.AUTHORIZATION;

public class ClickupClient {

    private static RequestSpecification clickupSpec(){
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", AUTHORIZATION);
    }

    public static Response getTestSpaceInfo(String testSpaceId){
        return RestAssured
                .given(clickupSpec())
                .when()
                .get("https://api.clickup.com/api/v2/space/" + testSpaceId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createNewFolder(String folderName, String spaceId){
        String jsonBody = "{\"name\": \"" + folderName + "\"}";
        return  RestAssured
                .given(clickupSpec())
                .body(jsonBody)
                .when()
                .post("https://api.clickup.com/api/v2/space/" +spaceId + "/folder")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createNewList(String listName, String folderId){
        String jsonBody = "{" +
                "\"name\": \"Test List\"," +
                "\"content\": \"Test List\"," +
                "\"due_date\": 1567780450202," +
                "\"due_date_time\": false," +
                "\"priority\": 1," +
                "\"assignee\": 56590421," +
                "\"status\": \"red\"" +
                "}";
        return  RestAssured
                .given(clickupSpec())
                .body(jsonBody)
                .when()
                .post("https://api.clickup.com/api/v2/folder/" +folderId + "/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();

    }

    public static Response getTestList(String testListId){
        return RestAssured
                .given(clickupSpec())
                .when()
                .get("https://api.clickup.com/api/v2/list/" + testListId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createListComment(String commentText, String listId){
        String jsonBody = "{" +
                "\"comment_text\": \"List comment created by Ljubisha\"," +
                "\"assignee\": 56590421," +
                "\"notify_all\": true" +
                "}";
        return  RestAssured
                .given(clickupSpec())
                .body(jsonBody)
                .when()
                .post("https://api.clickup.com/api/v2/list/" +listId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract().response();

    }


    public static Response createListTask(String listId){

        String taskName = "List Task " + System.currentTimeMillis();

        String jsonBody = "{" +
                "\"assignees\": [56590421]," +
                "\"0\": 56590421," +
                "\"categoryId\": 90150389359," +
                "\"check_required_custom_fields\": true," +
                "\"followers\": []," +
                "\"group_assignees\": []," +
                "\"name\": \"" + taskName + "\"," +
                "\"priority\": \"none\"," +
                "\"projectId\": 90150263251," +
                "\"status\": \"to do\"," +
                "\"subcategoryId\": 901500694963," +
                "\"tags\": []," +
                "\"use_preset_template\": true," +
                "\"v2\": true" +
                "}";

        return  RestAssured
                .given(clickupSpec())
                .body(jsonBody)
                .when()
                .post("https://api.clickup.com/api/v2/list/" +listId + "/task/")
                .then().log().all()
                .statusCode(200)
                .extract().response();

    }
    public static Response getListTask(String taskId){
        return RestAssured
                .given(clickupSpec())
                .queryParam("custom_task_ids", false)
                .queryParam("include_subtasks", false)
                .when()
                .get("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .extract().response();
    }

    public static Response createTaskComment(String commentText, String taskId){
        String jsonBody = "{" +
                "\"comment_text\": \"" + commentText + "\"," +
                "\"assignee\": 56590421," +
                "\"notify_all\": true" +
                "}";
        return  RestAssured
                .given(clickupSpec())
                .body(jsonBody)
                .when()
                .post("https://api.clickup.com/api/v2/task/" +taskId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract().response();

    }

    public static Response removeListTask(String taskId){
        return RestAssured
                .given(clickupSpec())
                .queryParam("custom_task_ids", false)
                .when()
                .delete("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .extract().response();
    }








    public static Response deleteFolder(String id){
        return RestAssured
                .given(clickupSpec())
                .queryParam("closed", true)
                .when()
                .delete("https://api.clickup.com/api/v2/folder/" + id)
                .then().log().all()
                .extract().response();

    }




}
