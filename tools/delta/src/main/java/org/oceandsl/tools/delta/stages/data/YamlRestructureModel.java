package org.oceandsl.tools.delta.stages.data;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class YamlRestructureModel {

    @Getter
    private final String name;

    @Getter
    private final Map<String, YamlComponent> components = new HashMap<>();

    public YamlRestructureModel(final String name) {
        this.name = name;
    }

}
