package com.example.practice1.repository;


import com.example.practice1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);

    //spring data jpa가 jparepository를 상속하고 있으면 스프링 spring data jpa가 인터페이스를 보고
    // 구현체를 자동으로 만들어서 스프링빈에 등록해줌
}
