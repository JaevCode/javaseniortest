package com.jaevcode.invex.employees.config.logging;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MaskedFieldConfig {
    private List<String> maskedFields;
}
