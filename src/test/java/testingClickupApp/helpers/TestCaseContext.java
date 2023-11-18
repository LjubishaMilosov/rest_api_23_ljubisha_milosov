package testingClickupApp.helpers;

import testingClickupApp.domain.*;

public class TestCaseContext {

private static Space space;
private  static Folder folder;
private static List list;
private static Comment comment;
private static Task task;

public static void init(){
    space = null;
    folder = null;
    list = null;
    comment = null;
    task = null;

}

    public static Space getTestSpace() {
        return space;
    }

    public static void setTestSpace(Space space) {
        TestCaseContext.space = space;
    }

    public static Folder getTestFolder() {
        return folder;
    }

    public static void setTestFolder(Folder folder) {
        TestCaseContext.folder = folder;
    }

    public static List getTestList() {
        return list;
    }

    public static void setTestList(List list) {
        TestCaseContext.list = list;
    }

    public static Comment getComment() {
        return comment;
    }

    public static void setComment(Comment comment) {
        TestCaseContext.comment = comment;
    }

    public static Task getTask() {
        return task;
    }

    public static void setTask(Task task) {
        TestCaseContext.task = task;
    }
}
