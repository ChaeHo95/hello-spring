package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// 스프링 컨테이너가 @Controller 에노테이션이 있을시 스프링이 스프링 컨테이너에 등록하고 관리함
public class MemberContriller {

    private final MemberService memberService;

    @Autowired
    // 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
    // 객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입
    public MemberContriller(MemberService memberService) {
        this.memberService = memberService;
    }
}
