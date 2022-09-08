package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA가 관리하는 엔티티라고 표시함
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db에 추가하면 id를 자동으로 만들어주는 전략(strategy)을 Identity라고 함
    private Long id;    // 사용자 지정이 아닌 시스템이 지정해주는 id
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
