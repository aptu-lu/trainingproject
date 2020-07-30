package ru.bellintegrator.trainingproject.controller.controlleradvice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.trainingproject.view.ErrorData;
import ru.bellintegrator.trainingproject.view.ResponseResult;
import ru.bellintegrator.trainingproject.view.SuccessResult;

/**
 * Позволяет настроить ответы контроллеров
 */
@RestControllerAdvice
public class CustomResponseBody implements ResponseBodyAdvice {

    /**
     * Определяет, что ответы всех методов контроллеров будут настроены
     *
     * @param methodParameter тип возврата метода контроллера
     * @param aClass выбранный тип конвертера
     * @return true если beforeBodyWrite должен быть взыван, false в противном случае
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * Если ответ контроллера содержит {@link ErrorData} то вернет исходный ответ, если
     * тело ответа пустое, то в тело записывается {@link SuccessResult},
     * иначе тело записывается в {@link ResponseResult} и возвращается
     *
     * @param o тело, которое будет записано
     * @param methodParameter тип возврата метода контроллера
     * @param mediaType тип контента
     * @param aClass выбранный тип конвертера
     * @param serverHttpRequest текущий запрос
     * @param serverHttpResponse текущий ответ
     * @return тело, оригинальный или измененный экземпляр
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorData) {
            return o;
        }
        ResponseResult responseResult = new ResponseResult();
        if (o == null) {
            SuccessResult result = new SuccessResult();
            responseResult.setData(result);
            return responseResult;
        }
        responseResult.setData(o);
        return responseResult;
    }
}
