package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")    // '/hello'로 접근하면 밑에 메소드가 실행됨
    public String hello(Model model) {
        // model에 key(name): data, value: hello!!인 attribute 추가
        model.addAttribute("data", "hello!!");

        // 컨트롤러에서 리턴 값으로 문자("hello")를 반환하면 뷰 리졸버('viewResolver')가 화면을 찾아서 처리한다.
        // 스프링 부트 템플릿엔진은 기본적으로 viewName 매핑 방식(따라서 hello.html을 찾아내서 처리함
        return "hello";
    }

    @GetMapping("hello-mvc")
    // RequestParam: http request(ex.get)로부터 인자값을 전달받는 것
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {   // required가 false면 값을 넘기지 않아도 됨
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // http protocol을 살펴보면 http의 head부와 body부가 있는데 body부에 이 data를 직접 넣어준다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // @ResponseBody를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됨(default)
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
