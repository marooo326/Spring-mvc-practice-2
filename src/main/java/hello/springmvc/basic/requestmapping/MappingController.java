package hello.springmvc.basic.requestmapping;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

/* 메모
일반 Controller는 반환값이 String 일 경우 뷰 이름으로 인식하고 뷰를 찾는다.
그러나 RestController는 HTTP 메세지 body에 바로 값을 입력한다.
 */
@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello-basic")
    public String helloBasicPost() {
        log.info("hellobasic");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("userId = {}", userId);
        return "ok";
    }

    @GetMapping("mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("userId = {} / orderId = {}", userId, orderId);
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 Media Type
     * 요청헤더의 Content-Type
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * 요청 헤더의 Accept
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
            public String mappingProduces(){
            log.info("mappingProduces");
            return"ok";
    }
}
