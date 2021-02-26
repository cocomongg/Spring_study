package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberRepositoryForTestTest {

    @Autowired
    MemberRepositoryForTest memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        MemberTest member = new MemberTest();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        MemberTest findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

        System.out.println("findMember == member: " + (findMember == member));
        /*
        * findMember == member가 true인 이유?
        * 같은 transaction안에서 영속성 context에서 식별자(id)가 같으면
        * 같은 entity로 취급
        */
    }
}