# Excess

Excess is a component-entity system for Java. It takes advantage of some Java 8 features, such as parallel streams, and lambdas. 

## Installation

Using Maven: 

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    <repositories>

    <dependencies>
        ...
        <dependency>
            <groupId>com.github.edwinhollen</groupId>
            <artifactId>Excess</artifactId>
            <version>1.0.1</version>
        </dependency>
    </dependencies>
    
The version corresponds to any GitHub release. [See releases.](https://github.com/edwinhollen/Excess/releases)

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
    // entities simply hold a list of components
    excess.addEntity(new Entity(Arrays.asList(new PositionComponent()));
    excess.addEntity(new Entity(Arrays.asList(new VelocityComponent()));
    excess.addEntity(new Entity(Arrays.asList(new PositionComponent(), new VelocityComponent())));
    // the first two entities will be rejected by PhysicsSystem,
    // the third entity will be accepted
    
    // let Excess organize entities into the systems
    // returns a map of systems and a list of their respective entities
    excess.getOrganizedEntities().forEach((system, entities) -> {
        system.update(entities);
    });
    
    

    
    