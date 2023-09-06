package hello.core.scan.filter;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {


    @Test
    void filterScan() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        // when
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        // then
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
        assertThat(beanA).isNotNull();

    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(/*type = FilterType.ANNOTATION,*/ classes = {MyIncludeComponent.class}), // 기본이 어노테이션이라 없어도 정상 동작함
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {MyExcludeComponent.class})
    )
    static class ComponentFilterAppConfig {

    }
}
