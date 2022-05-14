package hello.thymeleafbasic.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA",20);
        User userB = new User("userB",30);
        List<User> userList = new ArrayList<>();
        userList.add(userA);
        userList.add(userB);

        Map<String,User> userMap = new HashMap<>();
        userMap.put("userA",userA);
        userMap.put("userB",userB);

        model.addAttribute("user",userA);
        model.addAttribute("users",userList);
        model.addAttribute("userMap",userMap);
        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData","세션데이터");
        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello " + data;
        }
    }

    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age){
            this.username=username;
            this.age=age;
        }
    }
}
