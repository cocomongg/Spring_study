package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositoryForTest {

    @PersistenceContext  //@PersistenceContext 애노테이션을 보고 entitymanager를 주입해준다.
    EntityManager em; //build.gradle에 라이브러리(jpa)과 application.yml를 통해 entitymanager가 자동으로 생성됨

    public Long save(MemberTest member){
        em.persist(member);
        return member.getId();
        /*
        * 아이디만 반환하는 이유?
        * 커맨드와 쿼리를 분리
        * save함수는 커맨드이기 때문에 리턴값을 거의 안만듬
        */
    }

    public MemberTest find(Long id){
        return em.find(MemberTest.class, id);
    }
}
