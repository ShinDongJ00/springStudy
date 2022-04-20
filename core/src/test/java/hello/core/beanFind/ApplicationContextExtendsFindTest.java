package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfigB.class);

    @Test
    @DisplayName("부모타입 조회시 자식타입 둘이상이면 오류")
    public void findBeanByParentTypeDup(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class
        , () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입 조회시 빈 이름 지정하면 가능")
    public void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위타입으로 조회")
    public void findBeanBySubType(){
        FixDiscountPolicy fixDiscountPolicy = ac.getBean("fixDiscountPolicy",FixDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회")
    public void findAllBeanByParentType(){
        Map<String,DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        for(String key : beans.keySet()){
            System.out.println("key : " + key + " // value : " + beans.get(key));
        }
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    public static class TestConfigB{
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
