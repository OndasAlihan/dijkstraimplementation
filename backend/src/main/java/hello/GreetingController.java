package hello;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin
    @GetMapping("/greeting")
    public Greeting greetingWithJavaconfig(@RequestParam(required=false, defaultValue="World") String name) {
        System.out.println("==== in greeting ====");
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }


    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<String>> findDistance(@RequestBody Coordinates coordinates){
        ShortestPath st = new ShortestPath();
        ArrayList<String> response = st.dijkstra(coordinates);

        System.out.println(response);
        return new ResponseEntity<ArrayList<String>>(response, HttpStatus.OK);
    }
}