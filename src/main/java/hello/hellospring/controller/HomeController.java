package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 스프링 실행시 스프링에 컨테이너안에 먼저 등록 되어있는 것을 먼저 실행하기 때문에
    // static에 있는 html은 무시된다.

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
