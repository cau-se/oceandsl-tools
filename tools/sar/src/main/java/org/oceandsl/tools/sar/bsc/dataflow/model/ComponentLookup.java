package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentLookup {

    private final Map<String, ComponentStoreObject> lookupTable = new HashMap<>();
    private final List<String> components = new ArrayList<>();

    public ComponentStoreObject getContentOfComponent(final String component){
        return lookupTable.get(component);
    }

    public void putRoutineToComponent(final String componentIdent,final String routineIdent){
        final ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            final List<String> contentNew = new ArrayList<>(component.getImplementedRoutines());
            contentNew.add(routineIdent);
            component.setImplementedRoutines(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            final ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addRoutinetoRoutines(routineIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }
    public void putFunctionToComponent(final String componentIdent,final String functionIdent){
        final ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            final List<String> contentNew = new ArrayList<>(component.getImplementedFunctions());
            contentNew.add(functionIdent);
            component.setImplementedFunctions(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            final ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addFunctionToFunctions(functionIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }

    public void putCBlockToComponent(final String componentIdent,final String cblockIdent){
        final ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            final List<String> contentNew = new ArrayList<>(component.getImplementedCommonBlocks());
            contentNew.add(cblockIdent);
            component.setImplementedCommonBlocks(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            final ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addCommontoCommons(cblockIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }

    public boolean isPartOfComponent(final String componentIdent,final String maybeContent){
        return callsOperation(componentIdent, maybeContent) || callsCommon(componentIdent, maybeContent);
    }

    public boolean callsOperation(final String componentIdent,final String content){
        final List<String> operations = lookupTable.get(componentIdent).getImplementedRoutines();
        operations.addAll(lookupTable.get(componentIdent).getImplementedFunctions());
        return operations.contains(content);
    }
    public boolean callsCommon(final String componentIdent,final String content){
        final List<String> cblocks = lookupTable.get(componentIdent).getImplementedCommonBlocks();
        return cblocks.contains(content);
    }

    public String getComponentIdent(final String operation){
        for(final String component: components){
            if(isPartOfComponent(component, operation)){
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
