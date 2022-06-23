package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentLookup {

    Map<String, ComponentStoreObject> lookupTable = new HashMap<>();
    List<String> components = new ArrayList<>();

    public ComponentStoreObject getContentOfComponent(String component){
        return lookupTable.get(component);
    }

    public void putRoutineToComponent(String componentIdent, String routineIdent){
        ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            List<String> contentNew = new ArrayList<>(component.getImplementedRoutines());
            contentNew.add(routineIdent);
            component.setImplementedRoutines(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addRoutinetoRoutines(componentIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }
    public void putCBlockToComponent(String componentIdent, String cblockIdent){
        ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            List<String> contentNew = new ArrayList<>(component.getImplementedCommonBlocks());
            contentNew.add(cblockIdent);
            component.setImplementedRoutines(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addCommontoCommons(componentIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }

    public void putImportToComponent(String componentIdent, String importIdent){
        ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            List<String> contentNew = new ArrayList<>(component.getImportedComponent());
            contentNew.add(importIdent);
            component.setImplementedRoutines(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addImportToImports(componentIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }

    public boolean isPartOfComponent(String componentIdent, String maybeContent){
        List<String> routines = lookupTable.get(componentIdent).getImplementedRoutines();
        List<String> cblocks = lookupTable.get(componentIdent).getImplementedCommonBlocks();
        return routines.contains(maybeContent) || cblocks.contains(maybeContent);
    }

    public int getSizeOfTable(){
        return lookupTable.size();
    }
    public List<String> getComponents(){
        return this.components;
    }
}
