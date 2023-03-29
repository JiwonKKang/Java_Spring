package jiwon.selfmade.service;

import jiwon.selfmade.dto.LoginDTO;
import jiwon.selfmade.entity.Member;
import jiwon.selfmade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(LoginDTO loginDTO) {
        String loginId = loginDTO.getLoginId();
        String password = loginDTO.getPassword();

        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
