package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    // Contoller에서 리턴 값으로 문자를 반환하여
    // viewResolver에서
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    @GetMapping("hello-mvc") //템플릿 엔진
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody // http의 응답 Body부분에 직접 넣어주겠다는 것
    // @ResponseBody를 사용할 시 뷰 viewResolver를 사용하지 않음
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    // api방식을 사용하는 이유는 데이터를 주고 받기 위함
    // JSON 방식으로 내려줌
    // JSON은 key,value로 이루어진 데이터 오브젝트
    // Spring에서 기본적으로 JSON으로 반환 함
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class  Hello{
        // static 클래스로 만들시 클래스 안에서 만들수 있음
        // java에서 정식으로 지원하는 문법
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }
}
