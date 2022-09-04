package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Controller라는 어노테이션이 있으면, 스프링이 스프링 컨테이너(스프링 뜰 때 생김)에 객체를 생성해서 넣어두고 관리 == 스프링 컨테이너에서 스프링 빈이 관리된다
public class MemberController {
    /*
    컨트롤러가 MemberService를 가져다 써야하는데..

    private final MemberService memberService = new MemberService();
    스프링이 관리를 하게 되면, 다 스프링 컨테이너에 등록을 하고, 스프링 컨테이너로부터 받아서 쓰도록 바꾸어야 함
    이런 식으로 new 해서 인스턴스를 직접 생성해서 쓴다면, 다른 여러 컨트롤러가 MemberService를 가져다 쓰는 등의 상황에서 각각 다른 인스턴스를 사용하는 것이 되기 때문에
    하나만 생성해놓고 공유해서 쓰는 것이 좋다.
     */

    private final MemberService memberService;

    @Autowired
    /*
    1. 스프링이 뜸
    2. 스프링 컨테이너 생김
    3. 생성자 호출
    4. 스프링이 스프링 컨테이너에 있는 memberService를 컨트롤러에 자동으로 연결해줌(Autowired)
     */
    public MemberController(MemberService memberService) {  // 오류 뜨는 이유: 서비스에 어노테이션이 등록되어 있지 않다.
        this.memberService = memberService;
    }
}
