package com.jaevcode.invex.users.config.logging;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MaskedFieldConfig {
    private List<String> maskedFields;
}
