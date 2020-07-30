package ru.bellintegrator.trainingproject.view;

/**
 * Представление ошибки
 */
public class ErrorData {

    /**
     * Текст ошибки
     */
    private String error;

    /**
     * Код ошибки
     */
    private String codeError;

    public ErrorData(String error, String codeError) {
        this.error = error;
        this.codeError = codeError;
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
}
