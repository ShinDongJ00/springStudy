package com.sdj.myProject.repository;

import com.sdj.myProject.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private Map<Long, Member> memberList;
    private long sequence;

    public MemoryMemberRepository(){
        memberList = new HashMap<>();
        sequence = 0;
    }

    public void clearList(){
        memberList.clear();
        sequence = 0;
    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        memberList.put(member.getId(), member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberList.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return memberList.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberList.values());
    }
}
