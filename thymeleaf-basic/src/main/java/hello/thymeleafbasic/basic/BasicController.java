package hello.thymeleafbasic.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data","hello basic thymeleaf");
        return "basic/text-basic";
    }
    
    //기본적으론 escape 처리를 하는게 맞다
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data","hello <b>text-unescaped</b>");
        return "basic/text-unescaped";
    }
}
