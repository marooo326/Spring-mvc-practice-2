package hello.springmvc.basic.request;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String username = request.getParameter("username");
            int age = Integer.parseInt(request.getParameter("age"));
            log.info("username={}, age={}", username, age);
        } catch (NumberFormatException e) {
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("messageBody={}", messageBody);
        } finally {
            response.getWriter().write("ok");
        }
    }

    @ResponseBody //RestController랑 같은효과: view 조회를 진행하지 않는다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age) {
        log.info("username={}, age={}", username, age);
        return "ok"; //ok라는 view를 찾게된다.
    }


    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok"; //ok라는 view를 찾게된다.

    }

    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {    // 완전 생략 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //default value를 설정하면 required가 의미 없다. 또한, 빈 문자의 경우에도 설정값이 적용된다.
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // Map or MultiMap 으로도 가능하다.
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("paramMap={}",paramMap);
        return "ok";

    }



}
