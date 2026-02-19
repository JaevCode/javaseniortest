package com.jaevcode.invex.employees.config.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class MaskedFieldService {

    private final RequestLoggingProperties properties;
    private final ResourceLoader resourceLoader;
    private final Set<String> maskedFields = new HashSet<>();

    public MaskedFieldService(RequestLoggingProperties properties, ResourceLoader resourceLoader) {
        this.properties = properties;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void loadMaskedFields() throws IOException {
        if (properties.getMaskedFieldsConfig() == null)
            return;

        Resource resource = resourceLoader.getResource(properties.getMaskedFieldsConfig());
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        MaskedFieldConfig config = mapper.readValue(resource.getInputStream(), MaskedFieldConfig.class);

        config.getMaskedFields().forEach(field -> maskedFields.add(field.toString().toLowerCase()));
    }

    public String maskJsonFields(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            maskMapFields(map);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            return json; // no se puede parsear → devolver igual
        }
    }

    private void maskMapFields(Map<String, Object> map) {
        for (String key : map.keySet()) {
            if (key == null)
                continue;

            Object value = map.get(key);
            if (value instanceof Map) {
                maskMapFields((Map<String, Object>) value);
            } else if (maskedFields.contains(key.toLowerCase())) {
                map.put(key, "***");
            }
        }
    }
}
