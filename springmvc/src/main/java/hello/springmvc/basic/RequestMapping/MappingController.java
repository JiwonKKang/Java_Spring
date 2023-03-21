package hello.springmvc.basic.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello-basic")
    public String helloBasic() {
        log.info("hello basic");
        return "ok";
    }

    @GetMapping(value = "mapping-param", params = "mode!=debug")
    public String mapping() {
        return "ok";
    }
}
