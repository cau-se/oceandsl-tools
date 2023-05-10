package org.oceandsl.tools.delta.stages.data;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class YamlComponent {

    @Getter
    private final String name;

    @Getter
    private final Map<String, YamlOperation> operations = new HashMap<>();

    public YamlComponent(final String name) {
        this.name = name;
    }
}
