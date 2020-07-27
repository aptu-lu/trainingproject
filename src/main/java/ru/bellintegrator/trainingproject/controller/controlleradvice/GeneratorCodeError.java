package ru.bellintegrator.trainingproject.controller.controlleradvice;

import java.util.concurrent.atomic.AtomicInteger;

public class GeneratorCodeError {

    private static volatile AtomicInteger codeError = new AtomicInteger();

    void init() {
        codeError.set(1);
    }

    int getCodeError() {
        return codeError.incrementAndGet();
    }
}
