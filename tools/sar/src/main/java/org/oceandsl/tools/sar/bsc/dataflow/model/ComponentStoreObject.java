package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentStoreObject {

    private final String componentName;
    private String componentPackage = "Global";
    private List<String> implementedOperations = new ArrayList<>();

    public ComponentStoreObject(final String componentName){
        this.componentName = componentName;
    }

    public List<String> getImplementedOperations() {
        return implementedOperations;
    }

    public void setImplementedOperations(final List<String> implementedOperations) {
        this.implementedOperations = implementedOperations;
    }

    public String getComponentName() {
        return componentName;
    }
    public void addOperationToOperations(final String operationIdent){
        this.implementedOperations.add(operationIdent);
    }

    public String getComponentPackage() {
        return componentPackage;
    }
    public void setComponentPackage(final String componentPackage) {
        this.componentPackage = componentPackage;
    }
}
