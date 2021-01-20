package com.example.practice1.repository;

import com.example.practice1.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa는 entity manager라는 걸로 모든걸 동작
    //build.gradle에서 starter-data-jpa를 통해 스프링 부트가 EntityManager를 자동으로 생성해줌
    //내부적으로 datasource를 가지고 있어서 db와의 통신등을 내부에서 처리가능

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //jpql
                .getResultList();
    /*
    *"select m from Member m" -> jpql 쿼리
    * table대상이 아니라 객체(Member m)대상으로 query를 날림 (정확히는 entity에)
    * sql로 번역됨
    */
    }
}
