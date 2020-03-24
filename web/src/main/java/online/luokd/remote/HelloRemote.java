package online.luokd.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "COMMON")
public interface HelloRemote {
    @RequestMapping(value = "hi")
    String hello();

    @RequestMapping(value = "showConfig")
    String showConfig();
}
