package testingClickupApp.stepDefinitions;

import io.cucumber.java.en.*;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import testingClickupApp.domain.*;
import testingClickupApp.helpers.TestCaseContext;


import static testingClickupApp.clients.ClickupClient.*;
import static testingClickupApp.constants.ProjectConstants.TEST_SPACE_ID;
import static testingClickupApp.constants.ProjectConstants.TEST_SPACE_NAME;

public class ClickupSteps {

    @Given("Test space exists and contains the correct information")
    public void getSpaceAndCheckInfo() {
        Response response = getTestSpaceInfo(TEST_SPACE_ID);
        Space space = response.as(Space.class);
        TestCaseContext.setTestSpace(space);

        Assertions.assertThat(space.getId())
                .as("We assert that the Test Space Id is correct")
                .isEqualTo(TEST_SPACE_ID);

        Assertions.assertThat(space.getName())
                .as("We assert that the Test Space Name is correct")
                .isEqualTo(TEST_SPACE_NAME);

    }


    @When("User creates a new folder {string} in the existing test space and checks data")
    public void createNewFolderInSpaceAndCheckData( String name) {
        Response response = createNewFolder(name,TestCaseContext.getTestSpace().getId());
        Folder folder = response.as(Folder.class);
        TestCaseContext.setTestFolder(folder);

        Assertions.assertThat(folder.getName())
                .as("Test folder was created with name: " + name)
                .isEqualTo(name);


    }

    @When("User creates a new list {string} in the existing test folder")
    public void createNewListInExistingFolder(String name) {
        Response response = createNewList(name,TestCaseContext.getTestFolder().getId());
        List list = response.as(List.class);
        TestCaseContext.setTestList(list);

    }

    @And("User verifies the List name {string} is correct")
    public void checkListName(String name) {
        Response response = getTestList(TestCaseContext.getTestList().getId());
        List list = response.as(List.class);
        TestCaseContext.setTestList(list);

        Assertions.assertThat(list.getName())
                .as("Test list was created with name: " + name)
                .isEqualTo(name);

    }

    @When("User creates a new list comment {string} in the existing test list and verifies that comment is added")
    public void createNewCommentInExistingList(String text) {
        Response response = createListComment(text,TestCaseContext.getTestList().getId());
        Comment comment = response.as(Comment.class);
        TestCaseContext.setComment(comment);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    }


    @When("User creates a new list task with automatically generated unique name")
    public void createNewTaskInExistingList() {
        Response response = createListTask(TestCaseContext.getTestList().getId());
        Task task = response.as(Task.class);
        TestCaseContext.setTask(task);

    }


    @And("User verifies that task name is correct")
    public void checkTaskName() {

        Response response = getListTask(TestCaseContext.getTask().getId());
        Task task = response.as(Task.class);
        TestCaseContext.setTask(task);

        /** I couldn't find a way to assert that automatically generated unique name is correct! **/

    }


    @When("User adds one comment {string} in the existing list task and verifies that comment is added")
    public void addNewCommentInListTask(String text) {
        Response response = createTaskComment(text,TestCaseContext.getTask().getId());
        Comment comment = response.as(Comment.class);
        TestCaseContext.setComment(comment);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

    }


    @And("User removes the task  from the list")
    public void removeTaskFromList() {
       removeListTask(TestCaseContext.getTask().getId());

    }


    @Then("User verifies the task is deleted by getting message {string}")
    public void verifyTaskIsNoLongerPresent(String err) {
        Response response = getListTask(TestCaseContext.getTask().getId());
        Task task = response.as(Task.class);
        TestCaseContext.setTask(task);

        Assertions.assertThat(task.getErr())
                .as("Error message is: " + err)
                .isEqualTo(err);

    }

}


