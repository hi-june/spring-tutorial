package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// spring data jpa가 jpaRepository를 갖고 있으면, 구현체를 자동으로 만들어주고 spring bean에 자동으로 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    Optional<Member> findByName(String name);
}