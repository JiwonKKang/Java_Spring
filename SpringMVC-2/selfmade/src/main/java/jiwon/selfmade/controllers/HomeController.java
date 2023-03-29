package jiwon.selfmade.controllers;

import jiwon.selfmade.argumentResolver.Login;
import jiwon.selfmade.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(@Login Member member, Model model) {

        if (member == null) {
            return "home";
        }


        model.addAttribute("member", member);
        return "loginHome";
    }
}
