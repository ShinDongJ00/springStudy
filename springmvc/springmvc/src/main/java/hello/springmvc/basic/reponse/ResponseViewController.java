package hello.springmvc.basic.reponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello");
        mav.addObject("data","asdasd123123");
        return mav;
    }

    /**
     * 리턴값이 String 인 경우
     *  - @ResponseBody가 없을 시 뷰리졸버가 실행되어 뷰를 찾고 렌더링
     *  - 있을 시 Http메세지 바디에 직접 리턴값 입력
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","v2test");
        return "response/hello";
    }

    /**
     * 리턴값이 void 인 경우
     *  - Http 메세지 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해 논리뷰 이름으로 사용
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello");
    }
}
