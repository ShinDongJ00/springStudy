package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인")
    public void vipCustomer(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int result = discountPolicy.discount(member,10000);

        //then
        Assertions.assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC는 할인x")
    public void basicCustomer(){
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        int result = discountPolicy.discount(member,10000);

        //then
        Assertions.assertThat(result).isEqualTo(0);
    }
}