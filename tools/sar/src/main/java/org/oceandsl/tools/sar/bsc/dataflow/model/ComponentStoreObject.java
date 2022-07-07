package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentStoreObject {

    private String componentName;
    private List<String>  importedComponent = new ArrayList<>();
    private List<String> implementedRoutines = new ArrayList<>();
    private List<String> implementedCommonBlocks = new ArrayList<>();

    public ComponentStoreObject(String componentName){
        this.componentName = componentName;
    }

    public List<String> getImplementedCommonBlocks() {
        return implementedCommonBlocks;
    }

    public List<String> getImplementedRoutines() {
        return implementedRoutines;
    }

    public void setImplementedCommonBlocks(List<String> implementedCommonBlocks) {
        this.implementedCommonBlocks = implementedCommonBlocks;
    }

    public void setImplementedRoutines(List<String> implementedRoutines) {
        this.implementedRoutines = implementedRoutines;
    }

    public String getComponentName() {
        return componentName;
    }
    public void addRoutinetoRoutines(String routineIdent){
        this.implementedRoutines.add(routineIdent);
    }
    public void addCommontoCommons(String commonIdent){
        this.implementedCommonBlocks.add(commonIdent);
    }
    public void addImportToImports(String importedIdent){
        this.importedComponent.add(importedIdent);
    }
}
