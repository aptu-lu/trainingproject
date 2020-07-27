package ru.bellintegrator.trainingproject.controller.controlleradvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.trainingproject.view.ErrorData;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ErrorData defaultErrorHandler(Exception ex) {
        GeneratorCodeError generatorCodeError = new GeneratorCodeError();
        int codeError = generatorCodeError.getCodeError();
        log.info(ex.getMessage()+ " Code error: " + codeError);
        ErrorData errorData = new ErrorData();
        errorData.setError("Ошибка сервера. Код ошибки: " + codeError);
        return errorData;
    }
}