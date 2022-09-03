package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")    // '/hello'로 접근하면 밑에 메소드가 실행됨
    public String hello(Model model){
        // model에 key(name): data, value: hello!!인 attribute 추가
        model.addAttribute("data", "hello!!");

        // 컨트롤러에서 리턴 값으로 문자("hello")를 반환하면 뷰 리졸버('viewResolver')가 화면을 찾아서 처리한다.
        // 스프링 부트 템플릿엔진은 기본적으로 viewName 매핑 방식(따라서 hello.html을 찾아내서 처리함
        return "hello";
    }
}
