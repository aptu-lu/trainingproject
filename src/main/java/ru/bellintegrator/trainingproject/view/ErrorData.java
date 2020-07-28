package ru.bellintegrator.trainingproject.view;

/**
 * Представление ошибки
 */
public class ErrorData {

    /**
     * Текст ошибки
     */
    private String error;

    private String codeError;

    public ErrorData(String error) {
        this.error = error;
        this.codeError = generatorCodeError();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    private String generatorCodeError() {
        long max = 999999999;
        long min = 100000000;
        int result = (int)(Math.random() * ((max - min) + 1) + min);
        return "" + result;
    }
}
