package hello;

import java.lang.reflect.Array;
import java.util.List;

public class Route {
    private List<List<String>> route;

    public void add(List<String> coordinates){
        route.add(coordinates);
    }

    public List<List<String>> getRoute(){
        return this.route;
    }
}
