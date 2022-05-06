package hello.springmvc.basic.request;

import hello.springmvc.basic.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    /**
     * 반환 타입이 없으면서 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     * @RequestParam 사용
     * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xxx") 생략가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age){

        log.info("username={}, age={}",username,age);
        return "ok";
    }

    /**
     * @RequestParam 사용
     * String, int 등의 단순 타입은 @RequestParam생략가능
     * 어노테이션 생략시 required=false 자동 적용
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username={}, age={}",username,age);
        return "ok";
    }

    /**
     * required=true -> username이 없을 시 예외
     * /request-param-required?username= -> 빈문자로 통과
     * int age -> int는 null을 받을수 없어서 500에러 -> Integer로 받음 or defaultValue 사용
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true)String username
            , @RequestParam(required = false)Integer age){

        log.info("username={}, age={}",username, age);
        return "ok";
    }

    /**
     * defaultValue 사용 (빈 문자의 경우도 적용됨)
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest")String username
                                      , @RequestParam(required = false, defaultValue = "-1")int age){

        log.info("username={}, age={},",username, age);
        return "ok";
    }

    /**
     * Map으로 조회
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){

        log.info("username={}, age={}",paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * @ModelAttribute 사용 -> model.addAttribute 자동적용
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute UserInfo userInfo){

        log.info("username={}, age={}", userInfo.getUsername(), userInfo.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(UserInfo userInfo){

        log.info("username={}, age={}", userInfo.getUsername(), userInfo.getAge());
        return "ok";
    }
}
