package online.luokd.controller;

import online.luokd.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @Autowired
    HelloRemote helloRemote;

    @RequestMapping(value = "ccc")
    public String hello(){
        String response = helloRemote.hello();
        return "ccc-->"+response;
    }
}
