package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 가 자동으로 ID를 만들어 주는 것을 IDENTITY 라고 한다
    private Long id;
    @Column(name = "name")
    private String name;

}
