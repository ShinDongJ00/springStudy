package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("aaa",28);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("aaa",20);
        Member member2 = new Member("bbb",30);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Assertions.assertThat(memberList.size()).isEqualTo(2);
        Assertions.assertThat(memberList).contains(member1,member2);
    }
}