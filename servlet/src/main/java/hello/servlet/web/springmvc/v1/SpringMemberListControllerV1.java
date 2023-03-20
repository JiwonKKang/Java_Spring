package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
public class SpringMemberListControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelView process() {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("member-list");
        mv.getModel().put("members", members);
        return mv;
    }
}
