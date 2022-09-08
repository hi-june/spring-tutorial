package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional  // 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. --> 이렇게 하면 DB에 데이터가 남지 않기 때문에 다음 테스트에 영향을 주지 않음
class MemberServiceIntegrationTest {
    @Autowired  // test를 다른 데서 가져다 쓸 게 아니니까 그냥 필드 injection으로 autowired해준다.
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {   // join()
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {   // validateDuplicateMember() 검증
        // given
        Member member1 = new Member();
        member1.setName("spring test");

        Member member2 = new Member();
        member2.setName("spring test");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 예외 메시지를 변수 e로 받아서

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}