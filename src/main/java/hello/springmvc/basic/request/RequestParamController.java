package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //기본적으로 제공해주는 방식
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    //@RequestParam 을 사용해서 파라미터 받기
    @ResponseBody //@RestController와 같은 효과
    @RequestMapping("/request-param-v2")
    public String RequestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    //변수명 생략
    @ResponseBody //@RestController와 같은 효과
    @RequestMapping("/request-param-v3")
    public String RequestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //@RequestParam 생략
    @ResponseBody //@RestController와 같은 효과
    @RequestMapping("/request-param-v4")
    public String RequestParamV4(
            String username,
            int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //파라미터 값 필수 설정
    @ResponseBody //@RestController와 같은 효과
    @RequestMapping("/request-param-v5")
    public String requestParamRequired(
            //int = null 이 않됨 컴파일 오류, 빈 문자열이 들어오면 true
            @RequestParam(required = false) String username, //500 에러를 줌 스팩대로 줬는데 null이 들어왔음
            @RequestParam(required = true) int age //무조건 있어야 함 스팩이 없기 때문에 400
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //파라미터 값 필수 설정
    @ResponseBody //@RestController와 같은 효과
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //빈 문자열까지 처리해줌
            @RequestParam(required = false, defaultValue = "-1") String username, //500 에러를 줌 스팩대로 줬는데 null이 들어왔음
            @RequestParam(required = true, defaultValue = "guest") int age //무조건 있어야 함 스팩이 없기 때문에 400
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //Map으로 한꺼번에 받기 요청 파라미터가 같으면 MultiValueMap
    @ResponseBody //@RestController와 같은 효과
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    //ModelAttribute로 파리미터 받기
    //타입 안맞으면 에러, 애노테이션 생략 가능
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("hello={}", helloData);//ToString을 자동으로 만들어줌

        return "ok";
    }
}
