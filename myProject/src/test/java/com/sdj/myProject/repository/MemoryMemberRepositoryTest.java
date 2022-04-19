package com.sdj.myProject.repository;

import com.sdj.myProject.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearList();
    }

    @Test
    void save() {
        //given
        Member member1 = new Member();
        member1.setName("abc123");
        memberRepository.save(member1);

        //when
        Member result = memberRepository.findByName(member1.getName()).get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findById() {
        //given
        Member member1 = new Member();
        member1.setName("aaa");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("bbb");
        memberRepository.save(member2);

        //when
        Member result = memberRepository.findById(Long.valueOf(1)).get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("aaa");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("bbb");
        memberRepository.save(member2);

        //when
        Member result = memberRepository.findByName("aaa").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("aaa");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("bbb");
        memberRepository.save(member2);

        List<Member> memList = new ArrayList<>();
        memList.add(member1);
        memList.add(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result).isEqualTo(memList);
    }
}