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
            newComponent.addRoutinetoRoutines(routineIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }
    public void putCBlockToComponent(String componentIdent, String cblockIdent){
        ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            List<String> contentNew = new ArrayList<>(component.getImplementedCommonBlocks());
            contentNew.add(cblockIdent);
            component.setImplementedCommonBlocks(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addCommontoCommons(cblockIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }

    public boolean isPartOfComponent(String componentIdent, String maybeContent){
        return callsOperation(componentIdent, maybeContent) || callsCommon(componentIdent, maybeContent);
    }

    public boolean callsOperation(String componentIdent, String content){
        List<String> routines = lookupTable.get(componentIdent).getImplementedRoutines();
        return routines.contains(content);
    }
    public boolean callsCommon(String componentIdent, String content){
        List<String> cblocks = lookupTable.get(componentIdent).getImplementedCommonBlocks();
        return cblocks.contains(content);
    }

    public String getComponentIdent(String subroutine){
        for(String component: components){
            if(isPartOfComponent(component, subroutine)){
                return component;
            }
        }
        return "";
    }

    public int getSizeOfTable(){
        return lookupTable.size();
    }
    public List<String> getComponents(){
        return this.components;
    }
}
