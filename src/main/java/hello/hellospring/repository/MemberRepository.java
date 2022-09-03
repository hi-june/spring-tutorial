package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // 회원의 id나 name이 없을 경우, 단순히 null을 반환하는 대신, optional로 감싸서 반환하는 방식이 요즘 더 선호됨
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
