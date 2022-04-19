package com.sdj.myProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(@RequestParam(value = "name", required = false)String name, Model model){
        model.addAttribute("name",name);

        return "hello";
    }

    @GetMapping("Hello-api")
    public String HelloApi(@RequestParam("name")String name)
    {
        return "";
    }
    
    //깃허브 테스트용
}
