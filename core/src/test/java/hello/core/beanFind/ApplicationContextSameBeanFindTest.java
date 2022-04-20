package hello.core.beanFind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("타입 조회시 같은 타입 둘 이상이면 조회 오류")
    public void findBeanByTypeDup(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class
        , () ->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("같은 타입일 경우 이름으로 조회 가능")
    public void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("같은 타입 모두 조회")
    public void findAllBeanSameType(){
        Map<String,MemberRepository> beans = ac.getBeansOfType(MemberRepository.class);
        for(String key : beans.keySet()){
            System.out.println("key : " + key + " // value : " + beans.get(key));
        }
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }

    @Configuration
    public static class TestConfig{
        @Bean
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
