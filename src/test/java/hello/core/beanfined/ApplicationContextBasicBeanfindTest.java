package hello.core.beanfined;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicBeanfindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() {
        // given
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // when

        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("빈 이름없이 타입으로으로만 조회")
    @Test
    void findBeanByType() {
        // given
        MemberService memberService = ac.getBean(MemberService.class);

        // when

        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("구체 타입으로 조회")
    @Test
    void findBeanByName2() {
        // given
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        // when

        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("빈 이름으로 조회X")
    @Test
    void findBeanByNameX() {
        // given

        // when

        // then
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));

    }
}
