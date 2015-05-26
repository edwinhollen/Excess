# Excess

Excess is a component-entity system for Java. It takes advantage of some Java 8 features, such as parallel streams, and lambdas. 

## Usage example

    // define a component or two
    class PositionComponent implements Component{
        public double x = 0;
        public double y = 0;
    }
    class VelocityComponent implements Component{
        public double velocityX = 0;
        public double velocityY = 0;
    }

    // define a system
    class PhysicsSystem implements System{
        @Override
        public List<Class<? extends Component>> getAcceptedComponents(){
            // specify what types of components this system accepts
            return Arrays.asList(PositionComponent.class, VelocityComponent.class);
        }
        
        public void update(List<Entity> entities){
            // entities will contain entities which have PositionComponent and VelocityComponent
            entities.forEach(entity -> {
                entity.getComponent(PositionComponent.class).x += entity.getComponent(VelocityComponent.class).velocityX;
                entity.getComponent(PositionComponent.class).y += entity.getComponent(VelocityComponent.class).velocityY;
            });
        }
    }
    
    // make an Excess object
    Excess excess = new Excess();
    
    // add system(s)
    excess.addSystem(new PhysicsSystem());
    
    // add entities
    excess.addEntity(Arrays.asList(new PositionComponent());
    excess.addEntity(Arrays.asList(new VelocityComponent());
    // both of the above entities will be rejected by PhysicsSystem,
    // the below entity will be accepted by PhysicsSystem
    excess.addEntity(Arrays.asList(new PositionComponent(), new VelocityComponent()));
    
    // let Excess organize entities into the systems
    // returns a map of systems and a list of their respective entities
    excess.getOrganizedEntities().forEach((system, entities) -> {
        system.update(entities);
    });
    
    

    
    