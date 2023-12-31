package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @DisplayName("싱글톤 방식의 주의점 - 다른 클라이언트가 변경 가능한 필드를 가진 경우")
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        int priceA = statefulService1.order("userA", 10000);

        // ThreadB : B사용자 20000원 주문
        int priceB = statefulService2.order("userB", 20000);

        // ThreadA : A사용자 주문 금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("priceA = " + priceA);
        System.out.println("priceB = " + priceB);

        //assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}