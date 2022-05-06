package hello.springmvc.basic.request;

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
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    /**
     * MVC파라미터 -> InputStream : HTTP요청메세지 바디내용 직접조회 지원
     *              OutputStream : HTTP응답메세지 바디에 직접 결과출력 지원
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws  IOException{

        String messageBody = StreamUtils.copyToString(inputStream,StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity : HTTP Header, body 정보를 편리하게 조회
     *  - 메세지 바디정보 직접 조회(@RequestParam, @ModelAttribute)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * 응답도 HttpEntity 사용 가능
     *  - 메세지 바디 정보 직접 반환환(view 조회x
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}",messageBody);
        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody
     *  - 메세지 바디 정보 직접 변환(view조회 x)
     *  - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * 헤더정보가 필요할 땐 HttpEntity를 사용하거나 @RequestHeader 사용
     * 메세지 바디 직접조회 - 요청파라미터 조회 관련 전혀 없다
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){

        log.info("messageBody={}",messageBody);
        return "ok";
    }
}
