package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentLookup {

    private final Map<String, ComponentStoreObject> lookupTable = new HashMap<>();
    private final List<String> components = new ArrayList<>();

    public void putOperationsToComponent(final String componentIdent,final String operationIdent){
        final ComponentStoreObject component = lookupTable.get(componentIdent);
        if(component != null){
            final List<String> contentNew = new ArrayList<>(component.getImplementedOperations());
            contentNew.add(operationIdent);
            component.setImplementedOperations(contentNew);
            lookupTable.put(componentIdent,component);

        } else {
            components.add(componentIdent);
            final ComponentStoreObject newComponent = new ComponentStoreObject(componentIdent);
            newComponent.addOperationToOperations(operationIdent);
            lookupTable.put(componentIdent,newComponent);
        }
    }

    public boolean isPartOfComponent(final String componentIdent,final String maybeContent){
        return callsOperation(componentIdent, maybeContent);
    }

    public boolean callsOperation(final String componentIdent,final String content){
        final List<String> operations = lookupTable.get(componentIdent).getImplementedOperations();
        return operations.contains(content);
    }

    public String getComponentIdent(final String operation){
        for(final String component: components){
            if(isPartOfComponent(component, operation)){
                return component;
            }
        }
        return "";
    }

    public void setPackageToComponent(final String component,final String componentPackage){
        ComponentStoreObject componentStoreObject = this.lookupTable.get(component);
        if(componentStoreObject != null){
            componentStoreObject.setComponentPackage(componentPackage);
        }
    }
    public String getPackageToComponent(final String component){
        ComponentStoreObject componentStoreObject = this.lookupTable.get(component);
        if(componentStoreObject != null) {
            return componentStoreObject.getComponentPackage();
        } else {
            return "null";
        }
    }

    public int getSizeOfTable(){
        return lookupTable.size();
    }
    public List<String> getComponents(){
        return this.components;
    }
}
