package lzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author lzz
 * Date   2018/5/23
 */
@RestController
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public Map<String, Object> showIndex() {
        Map<String, Object> map = new HashMap<>();
        map.put("Project", "SpringBootMutil");
        map.put("Author", "lzz");
        map.put("Date", "2018-05-23");
        return map;
    }
}