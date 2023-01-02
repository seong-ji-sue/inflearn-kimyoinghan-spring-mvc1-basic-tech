package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,//요청
                          HttpServletResponse response,//응답
                          HttpMethod httpMethod,//요청 메서드 출력
                          Locale locale,//지역 출력
                          @RequestHeader MultiValueMap<String, String> headerMap,//헤더 정보 전부 출력
                          @RequestHeader("host") String host,//호스트 URL 출력
                          @CookieValue(value = "myCookie", required = false) String cookie//쿠키값 출력
                          ) {
        log.info("request={}", request);//request=org.apache.catalina.connector.RequestFacade@5e6dfada
        log.info("response={}", response);//response=org.apache.catalina.connector.ResponseFacade@21cb20d5
        log.info("httpMethod={}", httpMethod);//httpMethod=GET
        log.info("locale={}", locale);//locale=ko_KR
        log.info("headerMap={}", headerMap);//headerMap={host=[localhost:8080], connection=[keep-alive],...
        log.info("header host={}", host);//header host=localhost:8080
        log.info("myCookie={}", cookie);//myCookie=null
        return "ok";
    }
}
