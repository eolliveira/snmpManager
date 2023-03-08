package com.example.snmpManager.controllers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationError extends StandardError implements Serializable {
    List<FieldMessage> errors = new ArrayList<>();

    public void addError(String field, String message) {
        errors.add(new FieldMessage(field, message));
    }
}
