package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentStoreObject {

    private final String componentName;
    private String componentPackage = "Global";
    private List<String> implementedOperations = new ArrayList<>();
    private List<String> implementedCommonBlocks = new ArrayList<>();

    public ComponentStoreObject(final String componentName){
        this.componentName = componentName;
    }

    public List<String> getImplementedCommonBlocks() {
        return implementedCommonBlocks;
    }

    public List<String> getImplementedOperations() {
        return implementedOperations;
    }


    public void setImplementedCommonBlocks(final List<String> implementedCommonBlocks) {
        this.implementedCommonBlocks = implementedCommonBlocks;
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
    public void addCommonToCommons(final String commonIdent){
        this.implementedCommonBlocks.add(commonIdent);
    }

    public String getComponentPackage() {
        return componentPackage;
    }
    public void setComponentPackage(String componentPackage) {
        this.componentPackage = componentPackage;
    }
}
