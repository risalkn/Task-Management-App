package task.management.TaskManagementApp.exception;

public class TaskNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5218143265247846948L;

    public TaskNotFoundException(String message) {
        super(message);
    }
}
