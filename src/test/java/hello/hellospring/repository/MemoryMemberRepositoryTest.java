package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest {
    // 개발한 기능을 실행해서 테스트 할때 자바의 main 메소드를 통해서 실행 또는
    // 웹 애플리케이션의 컨트롤를 통해 기능 실행하며, 시간이 너무 오래 걸리고, 반복 실행이 어렵기 때문에
    // 여러 테스트를 한번에 실행하기 어렵다는 단점을 가짐
    // 이러한 단점을 자바는 JUnit이라는 프레임워크로 통해 테스트를 실행해서 문제를 해결함
    // Test는 서로 순서의존도 관계없이 설계 되어야함
    // given(준비) when(실행) then(검증) 패턴
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    // Test 순서는 보장이 안되기때문에
    // 데이터가 이전에 저장되어 있을 수 있기 때문에
    // Test 하나하나가 끌난때마다 실행 시킬 수 있는 @AfterEach 사용
    private void afterEach(){
        repository.clearStrore();
    }
    @Test
    public void save(){
        // given(준비)
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        // when(실행)
        Member result = repository.findById(member.getId()).get();
        // Optional으로 값을 꺼낼 떄는 get으로 가능하지만 좋지 않은 방법

        // then(검증)
//        System.out.println("result = " + (result == member));
        // 출력도 가능하지만 출력값을 계속 볼수 가 없어
//        Assertions.assertEquals(member,result);
//        // org.junit.jupiter.api에서 제공하는 Assertions 기능을 사용해도 된다
        assertThat(member).isEqualTo(result);
        // org.assertj.core.api.Assertions은 static import로 들어 갈 수 있기 떄문에
        // 다음 사용 시 assertThat() 메소드를 바로 사용 가능하다.
        // 많이 사용하는 문법
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
