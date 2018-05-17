package recognition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class test {

    @RequestMapping("/index")
    public String index() {
        return "test";
    }
}
