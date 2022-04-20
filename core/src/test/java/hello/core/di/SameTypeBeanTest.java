package hello.core.di;

import hello.core.discount.DiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SameTypeBeanTest {

    //@Primary 사용
    //private DiscountPolicy discountPolicy;
    //@Qualifier 사용
    @Autowired @Qualifier("fixDiscountPolicy")
    private DiscountPolicy discountPolicy;

    @Test
    @DisplayName("조회 빈이 두개 이상이다")
    public void sameTypeBeanTest(){
        System.out.println(discountPolicy.getClass());
    }
}
