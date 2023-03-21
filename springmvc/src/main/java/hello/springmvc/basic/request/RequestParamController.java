package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
