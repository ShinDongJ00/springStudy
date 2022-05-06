package hello.springmvc.basic.reponse;

import hello.springmvc.basic.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@RestController -> 모든 메소드에 @ResPonseBody가 적용 -> RestAPI(HTTP API)를 만들 때 사용하는 컨트롤러
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException{
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity, ResponseEntity(Http Status) 추가
     *  - HttpEntity는 HTTP메세지의 헤더,바디 정보를 가지고있음
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @ResponseBody를 사용해 Http메세지를 직접 입력
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }

    /**
     * HttpMessageConverter 를 통해 json형식으로 변환되어 반환
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<UserInfo> responseBodyJsonV1(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("user");
        userInfo.setAge(22);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    /**
     * @ResponseBody사용시 http응답코드 설정이 까다로움
     *  -> @ResponseStatus를 사용하여 응답코드 설정
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public UserInfo responseBodyJsonV2(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("userAAA");
        userInfo.setAge(23);
        return userInfo;
    }
}
