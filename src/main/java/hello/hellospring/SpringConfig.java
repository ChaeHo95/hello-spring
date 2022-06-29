package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplatMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
/* @Service ,@Repository, @Autowired 에노테이션을 제거 후
 순수 자바 코드로 직접 스프링 빈 주입 시 config 클래스 작성하다
 @Configuration 적어 준 후 @Bean을 이용하여 스프링빈에 등록 된다.
 */
public class SpringConfig {

    final private DataSource dataSource;
/*
    @Autowired
*/
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        메모리에 저장 하는 방식

        // Jdbc를 이용하여 DB를 사용하는 방식
//        return new JdbcMemberRepository(dataSource);

        // jdbcTemplat 이용하여 DB를 사용하는 방식
        return new JdbcTemplatMemberRepository(dataSource);
    }
}
// DI에는 필드 주입,setter, 생성자 주입 3가지 법법이 존재
// DI(Dependency Injection)란 의존성 주입
// 의존관계가 실행 중 동적으로 변하는 경우는 대다수 없으므로 생성자 주입을 권장
//
// 실무에서는 정형환된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용
// 정형화 되지 않거나 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록