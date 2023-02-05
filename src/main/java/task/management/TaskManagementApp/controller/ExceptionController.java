package task.management.TaskManagementApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import task.management.TaskManagementApp.exception.ErrorCode;
import task.management.TaskManagementApp.exception.TaskNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity<Object> TaskNotFoundException(TaskNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorCode(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }


}
