package online.luokd.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(value = "hi")
    public String hello(){
        return "hello,from COMMON";
    }
}
