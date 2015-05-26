import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 5/26/2015.
 */

public interface System{
    List<Class<? extends Component>> getAcceptedComponents();
}