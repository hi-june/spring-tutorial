package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); // 기존 방식: MemberService가 MemoryMemberRepository를 직접 생성하게 함
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {   // MemberService 코드를 DI 가능하게 변경한다.
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())   // 회원 리포지토리에서 멤버 이름으로 찾아서
                .ifPresent(m -> {   // 만약 존재한다면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");  // 이미 존재한다고 예외를 던지게끔 설계
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}