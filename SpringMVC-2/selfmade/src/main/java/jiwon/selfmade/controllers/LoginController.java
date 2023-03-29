package jiwon.selfmade.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jiwon.selfmade.SessionConst;
import jiwon.selfmade.dto.LoginDTO;
import jiwon.selfmade.entity.Member;
import jiwon.selfmade.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginDTO loginDTO) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String signIn(@Validated @ModelAttribute("loginForm") LoginDTO loginDTO, BindingResult bindingResult,
                         HttpServletRequest request,
                         @RequestParam(defaultValue = "/") String redirectURL) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginDTO);

        if (loginMember == null) {
            bindingResult.reject("noMember", "회원을 찾을수 없습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        log.info("session={}", session.getAttribute(SessionConst.LOGIN_MEMBER));

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        session.invalidate();

        return "redirect:/";
    }
}
