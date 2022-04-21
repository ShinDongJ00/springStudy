package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class NetworkClient2Test {
    @Test
    public void lifeCycleTest2(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig2.class);
        NetworkClient2 nc = ac.getBean(NetworkClient2.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig2{

        //초기화, 소멸 메소드 지정
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient2 networkClient(){
            NetworkClient2 networkClient = new NetworkClient2();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}