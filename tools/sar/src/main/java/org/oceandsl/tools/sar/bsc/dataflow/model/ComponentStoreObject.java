package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.List;

public class ComponentStoreObject {

    private final String componentName;
    private List<String>  implementedFunctions = new ArrayList<>();
    private List<String> implementedRoutines = new ArrayList<>();
    private List<String> implementedCommonBlocks = new ArrayList<>();

    public ComponentStoreObject(final String componentName){
        this.componentName = componentName;
    }

    public List<String> getImplementedCommonBlocks() {
        return implementedCommonBlocks;
    }

    public List<String> getImplementedRoutines() {
        return implementedRoutines;
    }

    public List<String> getImplementedFunctions() {
        return implementedFunctions;
    }


    public void setImplementedCommonBlocks(final List<String> implementedCommonBlocks) {
        this.implementedCommonBlocks = implementedCommonBlocks;
    }

    public void setImplementedRoutines(final List<String> implementedRoutines) {
        this.implementedRoutines = implementedRoutines;
    }

    public void setImplementedFunctions(final List<String> implementedFunctions){
        this.implementedFunctions = implementedFunctions;
    }

    public String getComponentName() {
        return componentName;
    }
    public void addRoutinetoRoutines(final String routineIdent){
        this.implementedRoutines.add(routineIdent);
    }
    public void addCommontoCommons(final String commonIdent){
        this.implementedCommonBlocks.add(commonIdent);
    }
    public void addFunctionToFunctions(final String funcIdent){
        this.implementedFunctions.add(funcIdent);
    }
}
