package com.sdj.myProject.service;

import com.sdj.myProject.domain.Member;
import com.sdj.myProject.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//jpa를 사용하기 위해선 @Transactional 이 있어야한다
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //가입
    public Long join(Member member){
        validateMemberName(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복확인
    private void validateMemberName(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }

    //전체조회
    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }

    //1명조회
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
