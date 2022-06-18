package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository 구현체를 바꿔야하기 때문에 자바 코드로 스프링 빈을 등록하였음
// @Repository 에노테이션이 없을시 순수 자바 코드 이기때문에
// 스프링이 알 수 있는 방법이 없기 때문에
// @Repository에노테이션을 넣어 주어야함
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
