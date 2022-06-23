package org.oceandsl.tools.sar.bsc.dataflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionLookup {

    Map<String, List<String>> lookupTable = new HashMap<>();
    List<String> components = new ArrayList<>();

    public List<String> getContentOfComponent(String component){
        return lookupTable.get(component);
    }

    public void putContentToComponent(String component, String newContent){
        List<String> content = lookupTable.get(component);
        if(content != null){
            List<String> contentNew = new ArrayList<>(content);
            contentNew.add(newContent);
            lookupTable.put(component,contentNew);

        } else {
            components.add(component);
            content = List.of(newContent);
            lookupTable.put(component,content);
        }
    }

    public boolean isPartOfComponent(String component, String maybeContent){
        List<String> content = lookupTable.get(component);
        return content.contains(maybeContent);
    }
    public int getSizeOfTable(){
        return lookupTable.size();
    }
    public List<String> getComponents(){
        return this.components;
    }
}
