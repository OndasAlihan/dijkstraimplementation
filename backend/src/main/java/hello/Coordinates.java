package hello;


import java.util.ArrayList;
import java.util.Map;

public class Coordinates {

    private String start;
    private String end;
    private ArrayList<String> arrayOfCoordinates = new ArrayList<>();
    private ArrayList<Map<String, Map<String, Integer>>> distances = new ArrayList<>();

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }


    public ArrayList<String> getArrayOfCoordinates() {
        return arrayOfCoordinates;
    }

    public void setArrayOfCoordinates(ArrayList<String> arrayOfCoordinates) {
        this.arrayOfCoordinates = arrayOfCoordinates;
    }

    public ArrayList<Map<String, Map<String, Integer>>> getDistances() {
        return distances;
    }

    public void setDistances(ArrayList<Map<String, Map<String, Integer>>> distances) {
        this.distances = distances;
    }
}
