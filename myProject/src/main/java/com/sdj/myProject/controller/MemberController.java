package com.sdj.myProject.controller;

import com.sdj.myProject.domain.Member;
import com.sdj.myProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createNew(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String membersList(Model model){
        List<Member> list = memberService.findAllMember();
        model.addAttribute("memberList",list);
        return "members/memberList";
    }

}
