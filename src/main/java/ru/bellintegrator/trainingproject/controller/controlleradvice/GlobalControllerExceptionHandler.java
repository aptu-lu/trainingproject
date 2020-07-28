package ru.bellintegrator.trainingproject.controller.controlleradvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.trainingproject.view.ErrorData;

/**
 * Перехватывает и обрабатывает исключения из контроллеров
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * Логгер
     */
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * Перехватывает все необработанные исключения, логирует сообщение ошибки и возвращает сообщение с кодом ошибки
     *
     * @param ex исключение
     * @return {@link ErrorData} сообщение с кодом ошибки
     */
    @ExceptionHandler(Exception.class)
    public ErrorData defaultErrorHandler(Exception ex) {
        ErrorData errorData = new ErrorData("Ошибка сервера.");
        log.info(ex.getMessage()+ " Code error: " + errorData.getCodeError());
        return errorData;
    }
}