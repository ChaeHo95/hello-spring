package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 실무에서는 공유되는 변수일 시 동시성 문제가 있을 수 있어 HashMap 대신 ConcurrnetHashMap을 사용한다.
    // HashMap은 thread-safe 하지 않았기 때문에,
    // MultiThread 환경에서 동기화 처리가 필요하다면 ConcurrentHashMap을 사용하는 것이 안정적이기 때문
    private static long sequence = 0L;
    // 실무에서는 공유되는 변수일 시 동시성 문제가 있을 수 있어 Long 대신 AtomicLong를 사용한다.
    // 멀티 쓰레드 환경에서 동기화 문제를 별도의 synchronized 키워드 없이 해결하기 위해서 고안된 방법



    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // Optional.ofNullable()를 이용하여 null이여도 반환할수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStrore(){
        store.clear();
    }
}
