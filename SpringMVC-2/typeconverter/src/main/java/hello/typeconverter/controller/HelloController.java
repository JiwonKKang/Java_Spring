package hello.typeconverter.controller;

import hello.typeconverter.IpPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/ip-port")
    public ResponseEntity<IpPort> ipPort(@RequestParam IpPort ipPort) {
        return ResponseEntity.ok().body(ipPort);
    }
}
