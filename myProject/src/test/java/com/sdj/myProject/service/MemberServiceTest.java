package com.sdj.myProject.service;

import com.sdj.myProject.domain.Member;
import com.sdj.myProject.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    private MemoryMemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        this.memberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearList();
    }

    @Test
    void join() {
        //given
        Member member1 = new Member();
        member1.setName("aaa");
        Long saveId = memberService.join(member1);

        //when
        Member result = memberRepository.findById(saveId).get();

        //then
        assertThat(result.getName()).isEqualTo(member1.getName());
    }

    @Test
    void join2(){
        //given
        Member member1 = new Member();
        member1.setName("aaa");
        memberService.join(member1);

        //when
        Member member2 = new Member();
        member2.setName("aaa");
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    void findAllMember() {
        //given
        Member member1 = new Member();
        member1.setName("aaa");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("bbb");
        memberService.join(member2);

        //when
        List<Member> memberList = memberService.findAllMember();

        //then
        assertThat(memberList.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
        //given
        Member member1 = new Member();
        member1.setName("aaa");
        Long mem1Id = memberService.join(member1);

        Member member2 = new Member();
        member2.setName("bbb");
        Long mem2Id = memberService.join(member2);

        //when
        Member result = memberService.findOne(mem1Id).get();

        //then
        assertThat(result.getName()).isEqualTo(member1.getName());
    }
}