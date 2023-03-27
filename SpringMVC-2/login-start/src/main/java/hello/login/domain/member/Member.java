package hello.login.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Member {

    private Long id;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    public Member() {
    }

    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
