package ru.bellintegrator.trainingproject.controller.controlleradvice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.trainingproject.view.ResponseResult;
import ru.bellintegrator.trainingproject.view.SuccessResult;

@RestControllerAdvice
public class CustomResponseBody implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            SuccessResult result = new SuccessResult();
            return result;
        }
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(o);
        return responseResult;
    }
}
