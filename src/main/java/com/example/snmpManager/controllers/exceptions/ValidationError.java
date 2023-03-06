package com.example.snmpManager.controllers.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
    List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErros() {
        return errors;
    }

    public void addError(String field, String message) {
        errors.add(new FieldMessage(field, message));
    }
}
