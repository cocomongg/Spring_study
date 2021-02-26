package jpabook.jpashop.service;

import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //jpa가 조회하는 곳에서 성능 최적화
@RequiredArgsConstructor //생성자 만들어줌(final키워드만, lombok)
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
        /**
         * memberRepository.save(member) -> em.persist(member): 영속성 컨텍스트에 member객체를 올림
         * 영속성 컨텍스트에는 key와 value가 있음, key는 member의 id가 된다.
         * db에 저장된 시점이 아니여도 id(pk값)은 생성됨, member.getId()에 항상 값이 있는게 보장됨
         */
    }

    private void validateDuplicateMember(Member member) { // 이 방법은 안전하지 않음, name을 unique하게 설정하면 됨
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findeOne(memberId);
    }
}
