// A Java program for Dijkstra's single source shortest path algorithm. 
// The program is for adjacency matrix representation of the graph
package hello;
import java.util.*; 
import java.lang.*; 
import java.io.*; 
import static java.lang.System.in;

public class ShortestPath 
{ 
    // A utility function to find the vertex with minimum distance value, 
    // from the set of vertices not yet included in shortest path tree

    String minDistance(Map<String, Integer> neighborVertexes, ArrayList<String> visited) {
        int min = Integer.MAX_VALUE;
        String min_neighbor = "";
        for (Map.Entry<String, Integer> entry : neighborVertexes.entrySet()) {
            if (!visited.contains(entry.getKey()) && entry.getValue() <= min) {
                min = entry.getValue();
                min_neighbor = entry.getKey();
            }
        }
        //System.out.println("Chosen min: " + min_neighbor + " with distance: " + neighborVertexes.get(min_neighbor));
        return min_neighbor;
    }
    void printResult(Map<String, Integer> distances, Map<String, String> prevVertexes) {
        distances.forEach((key, value) -> {
            if (prevVertexes.containsKey(key)) {
                System.out.println(key + "\t" + value + "\t" + prevVertexes.get(key));
            }
            else {
                System.out.println(key + "\t" + value);
            }
        });
        System.out.println("PreviousVertexes");
        prevVertexes.forEach((key, value) -> {
            System.out.println(key + "\t" + value);
        });
    }
    void getSPSequenceUtil(String start, String end, Map<String, String> prevVertexes, ArrayList<String> SPSequence) {
        if (end.equals(start)) return;
        if (prevVertexes.containsKey(end)) {
            getSPSequenceUtil(start, prevVertexes.get(end), prevVertexes, SPSequence);
            SPSequence.add(end);
        }
    }
    ArrayList<String> getSPSequence(String start, String end, Map<String, String> prevVertexes) {
        ArrayList<String> SPSequence = new ArrayList<>();
        SPSequence.add(start);
        getSPSequenceUtil(start, end, prevVertexes, SPSequence);
        return SPSequence;
    }

    public ArrayList<String> dijkstra(Coordinates coordinates) {
        int vertexCount = coordinates.getArrayOfCoordinates().size();
        int dist[] = new int[vertexCount];
        ArrayList<String>  visited = new ArrayList<>();
        int visitCounter = 0;
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> prevVertex = new HashMap<>();
        for (int i = 0; i < vertexCount; i++) {
            distances.put(coordinates.getArrayOfCoordinates().get(i), Integer.MAX_VALUE);
        }
        //System.out.println("Distances: " + distances);
        distances.put(coordinates.getStart(), 0);
        for (int count = 0; count < vertexCount; count++) 
        {
            String u = minDistance(distances, visited);
            visited.add(u);
            //System.out.println(visited.toString());
            //System.out.println(distances.toString());
                for (int j = 0; j < coordinates.getDistances().size(); j++) {
                    if (coordinates.getDistances().get(j).containsKey(u) && coordinates.getArrayOfCoordinates().contains(u)) {
                        for (Map.Entry<String, Integer> entry : coordinates.getDistances().get(j).get(u).entrySet()) {
                            //System.out.println(entry.getKey() + " " + entry.getValue());
                            //System.out.println("vertex: " + u + " to " + entry.getKey() + " visited: " + visited.contains(entry.getKey()) + ", distances= " +  distances.get(u) + ", sum of two values= " + (distances.get(u) + entry.getValue()) + " comparing with " + distances.get(entry.getKey()));
                            if (coordinates.getArrayOfCoordinates().contains(entry.getKey()) && !visited.contains(entry.getKey()) && distances.get(u) != Integer.MAX_VALUE && distances.get(u) + entry.getValue() < distances.get(entry.getKey())) {
                                //System.out.println("Changing " + entry.getKey() + " from " + distances.get(entry.getKey()) + " to " + (distances.get(u) + entry.getValue()));
                                distances.put(entry.getKey(), distances.get(u) + entry.getValue());
                                prevVertex.put(entry.getKey(), u);
                            }
                        }
                    }
                }
            }
        printResult(distances, prevVertex);
        //System.out.println("Sequence array: " + getSPSequence(coordinates.getStart(), coordinates.getEnd(), prevVertex));
        return getSPSequence(coordinates.getStart(), coordinates.getEnd(), prevVertex);
    }
    public static void main (String[] args) { 
        ShortestPath st = new ShortestPath(); 
        //t.dijkstra(graph, 0); 
        Coordinates coor = new Coordinates();
        coor.getArrayOfCoordinates().add("0");
        coor.getArrayOfCoordinates().add("1");
        coor.getArrayOfCoordinates().add("2");
        coor.getArrayOfCoordinates().add("3");
        
        coor.setStart("0");
        coor.setEnd("3");
        //for 0
        Map<String, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        distances.put("1", 20);
        distances.put("2", 30);
        map.put("0", distances);
        coor.getDistances().add(map);
        //for 1
        Map<String, Map<String, Integer>> map1 = new HashMap<>();
        Map<String, Integer> distances1 = new HashMap<>();
        distances1.put("0", 20);
        distances1.put("3", 30);
        distances1.put("2", 30);
        map1.put("1", distances1);
        coor.getDistances().add(map1);
        //for 2
        Map<String, Map<String, Integer>> map2 = new HashMap<>();
        Map<String, Integer> distances2 = new HashMap<>();
        distances2.put("0", 30);
        distances2.put("3", 10);
        distances2.put("1", 30);
        map2.put("2", distances2);
        coor.getDistances().add(map2);
        //for 3
        Map<String, Map<String, Integer>> map3 = new HashMap<>();
        Map<String, Integer> distances3 = new HashMap<>();
        distances3.put("1", 30);
        distances3.put("2", 10);
        map3.put("3", distances3);
        coor.getDistances().add(map3);
        System.out.println(coor.getDistances().toString());
        
        st.dijkstra(coor);
    }
} 
