package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j//자동으로 세팅
@RestController//view이름이 아니라 스트링이 반환
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        //모두 다 출력
        System.out.println("name = " + name);//과거

        //레벨 순(기본값은 info) 파일을 남긴다
        log.trace("trace log = ",name);//로컬
        log.debug("debug log = ",name);//개발
        log.info("info log = ",name);//운영
        log.warn("warn log = ",name);//경고
        log.error("error log = ",name);//에러

        //차이점
        log.trace("trace log = ",name);
        log.trace("trace log = " + name);//연산하고 로그에 출력
        return "ok";
    }
}
