package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;  // junit 프레임워크로 테스트

import java.util.List;

import static org.assertj.core.api.Assertions.*;    // static import를 통해 assertThat을 바로 쓸 수 있다.

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.
    public void afterEach() {
        repository.clearStore();    // 한 번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 따라서 메모리 DB에 저장된 데이터를 삭제해준다.
    }

    @Test
    public void save(){
        Member member  = new Member();
        member.setName("spring");   // 1. 이름을 세팅하고

        repository.save(member);    // 2. member repository에 저장한 뒤

        Member result = repository.findById(member.getId()).get();  // 3. id로 찾아낸 member와 비교
        // System.out.println("result = " + (result == member));   // 4. 비교 결과를 콘솔에 출력..할 수도 있지만 항상 콘솔에서 볼 수는 없으니
        //Assertions.assertEquals(result, member);    // 5. junit에서 제공하는 assertions를 통해 검증(앞: expected, 뒤: actual)

        assertThat(member).isEqualTo(result);   // 5. assertj에서 제공하는 assertThat을 사용
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();

        // then
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
