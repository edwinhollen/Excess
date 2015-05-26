import edwinhollen.excess.Component;
import edwinhollen.excess.Entity;
import edwinhollen.excess.Excess;
import org.junit.Assert;
import edwinhollen.excess.System;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Edwin on 5/26/2015.
 */
public class BasicTest {
    @Test
    public void test(){

        Excess excess = new Excess();

        // this system accepts entities with A, B, and CComponent
        System abcSystem = new ABCSystem();
        excess.addSystem(abcSystem);

        // this entity has all of the components required for ABCSystem - accepted
        excess.addEntity(new Entity(Arrays.asList(new AComponent(), new BComponent(), new CComponent())));

        // this entity has 1 of the components required for ABCSystem - rejected
        excess.addEntity(new Entity(Arrays.asList(new AComponent())));

        // this entity has 2 of the components required for ABCSystem - rejected
        excess.addEntity(new Entity(Arrays.asList(new BComponent(), new CComponent())));

        // organize entities
        Map<System, List<Entity>> organized = excess.getOrganizedEntities();

        Assert.assertEquals(organized.get(abcSystem).size(), 1);
    }

    class ABCSystem implements System {
        @Override
        public List<Class<? extends Component>> getAcceptedComponents() {
            return Arrays.asList(AComponent.class, BComponent.class, CComponent.class);
        }
    }

    class AComponent implements Component {
        public String greeting = "Howdy, I'm AComponent!";
    }
    class BComponent implements Component {
        public String greeting = "Hello, this is BComponent!";
    }
    class CComponent implements Component {
        public String greeting = "Hi, my name is CComponent!";
    }
}
