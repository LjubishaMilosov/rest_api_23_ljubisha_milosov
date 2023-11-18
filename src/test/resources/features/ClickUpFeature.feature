Feature: This feature tests ClickUp API

  Scenario: ClickUp Folder and List Test
    Given Test space exists and contains the correct information

    When User creates a new folder "New Test Folder" in the existing test space and checks data
    When User creates a new list "Test List" in the existing test folder
    And User verifies the List name "Test List" is correct
    When User creates a new list comment "List comment created by Ljubisha" in the existing test list and verifies that comment is added
    When User creates a new list task with automatically generated unique name
    And User verifies that task name is correct
    When User adds one comment "Task Comment" in the existing list task and verifies that comment is added
    And User removes the task  from the list
    Then User verifies the task is deleted by getting message "Task not found, deleted"

