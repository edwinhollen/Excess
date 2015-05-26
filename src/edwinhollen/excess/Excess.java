package edwinhollen.excess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Edwin on 5/26/2015.
 */
public class Excess {
    private List<System> systems = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();

    public Map<System, List<Entity>> getOrganizedEntities(){
        Map<System, List<Entity>> returnMap = new HashMap<>();
        systems.parallelStream().forEach(system -> returnMap.put(system, entities.parallelStream()
            //.filter(entity -> system.getAcceptedComponents().containsAll(entity.getComponentsAsClasses()))
            .filter(entity -> entity.getComponentsAsClasses().containsAll(system.getAcceptedComponents()))
            .collect(Collectors.toList())
        ));
        return returnMap;
    }

    public void addSystem(System system){
        this.systems.add(system);
    }

    public void removeSystem(System system){
        this.systems.remove(system);
    }

    public void addEntity(Entity entity){
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity){
        this.entities.remove(entity);
    }

}
