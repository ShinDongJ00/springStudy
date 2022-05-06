package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);
        UserInfo userInfo = objectMapper.readValue(messageBody, UserInfo.class);
        log.info("username={}, age={}",userInfo.getUsername(), userInfo.getAge());

        response.getWriter().write("ok");
    }

    /**
     * @RequestBody 문자변환
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{

        UserInfo userInfo = objectMapper.readValue(messageBody,UserInfo.class);
        log.info("username={}, age={}",userInfo.getUsername(), userInfo.getAge());
        return "ok";
    }

    /**
     * @RequestBody 객제변환
     * @ModelAttribute와 다르게 생략 불가능
     * 스프링은 @ModelAttribute, @RequestParam 생략시 단순타입, 나머지 에따라 자동으로 적용시켜줌
     * @RequestBody 생략시 자동으로 @ModelAttribute로 처리하므로 HTTP메세지 바디가 아닌 요청 파라미터를 처리함
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserInfo userInfo) {

        log.info("username={}, age={}", userInfo.getUsername(), userInfo.getAge());
        return "ok";
    }

    /**
     * HttpEntity 서용
     */

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<UserInfo> httpEntity){
        UserInfo userInfo = httpEntity.getBody();
        log.info("username={}, age-{}",userInfo.getUsername(), userInfo.getAge());
        return "ok";
    }

    /**
     * @RequestBody 생략 불가능(@ModelAttribute 가 적용되어 버림)
     * HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter (content-type: application/json)
     *
     * @ResponseBody 적용
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter 적용
        (Accept: application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public UserInfo requestBodyJsonV5(@RequestBody UserInfo userInfo){
        log.info("username={}, age={}",userInfo.getUsername(), userInfo.getAge());
        return userInfo;
    }
}
