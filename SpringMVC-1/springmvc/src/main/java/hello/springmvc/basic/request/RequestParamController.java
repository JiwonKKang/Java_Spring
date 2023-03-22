package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
public class RequestParamController {

    @ResponseBody//return을 http 응답 바디에 넣어서 보내버린다
    @RequestMapping("/request-param-v3")
    public String requestParmV3(@RequestParam(required = true) String username,
                                @RequestParam(required = false) Integer age) throws IOException {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody//return을 http 응답 바디에 넣어서 보내버린다
    @RequestMapping("/request-param-map")//파라미터의 값이 1개가 확실하다면 Map을 쓰고 그 이상이면 MultiValueMap을 사용하자
    public String requestParmMap(@RequestParam Map<String, Object> paramMap) throws IOException {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @GetMapping("/model-attribute-v1")
    @ResponseBody
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        String username = helloData.getUsername();
        int age = helloData.getAge();
        log.info("HelloData = {}", helloData);

        return "ok";
    }

    @PostMapping("/request-body-string-v4")
    @ResponseBody//리턴을 메세지바디에 넣어준다
    public String response(@RequestBody String message) {//요청메세지바디를 바로 파라미터로 받을수있다.
        log.info("MessageBody = {}", message);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("username = {} age = {}", data.getUsername(), data.getAge());
        return data;
    }
}
