package ru.chernyshev.MySixthTestAppSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationResult {

    private boolean success;
    private String message;
    private Object data;

    public static OperationResult success(String message, Object data) {
        return new OperationResult(true, message, data);
    }

    public static OperationResult failure(String message) {
        return new OperationResult(false, message, null);
    }
}