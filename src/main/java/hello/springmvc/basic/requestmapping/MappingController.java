package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    //기본 URL 요청
    @RequestMapping("/hello-basic")//배열로 제공
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    //HTTP 메서드 추가
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    //HTTP 메서드 축약
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    //PathVariable 사용
    @GetMapping(value = "/mapping/{userId}")
    public String mappingPathV1(@PathVariable("userId") String data) {
        log.info("mappingPathV1 userId={}", data);
        return "ok";
    }

    //PathVariable 다중 사용
    @GetMapping(value = "/mapping/{userId}/order/{order}")
    public String mappingPathV2(@PathVariable String userId,@PathVariable Long orderId) {
        log.info("mappingPathV2 userId={}", userId);
        log.info("mappingPathV2 orderId={}", orderId);
        return "ok";
    }

    //특정 파라미터 조건 매핑
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    //특정 헤더 조건 매핑
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //미디어 타입 조건 매핑- HTTP 요청 Content-Type, consume
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    //미디어 타입 조건 매핑- HTTP 요청 Accept, produce
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
