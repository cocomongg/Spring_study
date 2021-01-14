package hello.practice1.service;


import hello.practice1.domain.Member;
import hello.practice1.repository.MemberRepository;
import hello.practice1.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){ // DI, add for test
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){ //회원가입
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){ //회원 이름을 통해 중복회원 검증
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("아마 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){ //전체회원조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){ //id를 통해 회원 조회
        return memberRepository.findById(memberId);
    }

}
