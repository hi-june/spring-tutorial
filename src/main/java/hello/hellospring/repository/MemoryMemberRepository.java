package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는 동시성 문제가 있을 수 있어서 이렇게 공유되는 변수일 때는...
    private static Map<Long, Member> store = new HashMap<>();   // ConcurrentHashMap을 사용함
    private static long sequence = 0L;  // AtomicLong을 사용함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   // sequence로 id 설정
        store.put(member.getId(), member);  // store라는 map 객체에 <id, member>인 객체 추가
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // 해당 id를 가진 객체가 없는데 조회를 할 경우, null 처리를 위해 ofNullable로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // 3. 반환
                .filter(member -> member.getName().equals(name))    // 1. member.getName() 과 name이 같은지 확인하여
                .findAny(); // 2. 같은 걸 하나라도 찾으면
    }

    @Override
    public List<Member> findAll() { // store는 map인데, 반환은 리스트로 되어 있음 -> java에서 실무할 땐 루프 돌리기도 쉽고 해서 리스트를 많이 쓴다고 함
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
