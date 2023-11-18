package testingClickupApp.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import testingClickupApp.helpers.TestCaseContext;

import static testingClickupApp.clients.ClickupClient.createNewFolder;

import static testingClickupApp.clients.ClickupClient.deleteFolder;
import static testingClickupApp.constants.ProjectConstants.TEST_SPACE_ID;

public class Hooks {

    @Before
    public  void  beforeHook(){
        TestCaseContext.init();
        System.out.println("Scenario has started");
    }

    @After
    public  void  afterHook(){

       //deleteFolder(TestCaseContext.getTestFolder().getId());
        System.out.println("Scenario has finished");
    }

}
