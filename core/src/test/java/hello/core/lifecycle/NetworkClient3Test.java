package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class NetworkClient3Test {

    @Test
    public void lifeCycleTest3(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig3.class);
        NetworkClient3 nc = ac.getBean(NetworkClient3.class);
        ac.close();
    }

    static class LifeCycleConfig3{

        @Bean
        public NetworkClient3 networkClient(){
            NetworkClient3 networkClient = new NetworkClient3();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}