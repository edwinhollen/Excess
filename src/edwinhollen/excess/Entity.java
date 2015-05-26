package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Edwin on 5/26/2015.
 */

public class Entity{
    private List<Component> components = new ArrayList<>();
    public Entity(List<Component> components){
        this.components = components;
    }
    public Component getComponent(Class<? extends Component> componentClass){
        return this.components.parallelStream().filter(componentClass::isInstance).collect(Collectors.toList()).get(0);
    }
    public void addComponent(Component component){
        this.components.add(component);
    }
    public void removeComponent(Component component){
        this.components.remove(component);
    }
    public List<Class<? extends Component>> getComponentsAsClasses(){
        return this.components.parallelStream()
                .map(component -> component.getClass())
                .collect(Collectors.toList());
    }
}