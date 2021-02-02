package hello.core.beanfind;

import hello.core.SpringConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);//타입을 지정안했기 때문에 Object로 받음
            System.out.println("name = " + beanDefinitionName + " object = "+bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//bean에 대한 메타데이터 정보
/*
*ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
*ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
*/
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                //스프링 내부 bean이 아니라 개발자가 애플리케이션 개발을 위해 직접 등록한 bean
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = "+bean);
            }

        }
    }
}
