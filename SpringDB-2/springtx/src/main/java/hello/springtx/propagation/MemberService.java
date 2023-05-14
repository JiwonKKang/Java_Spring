package hello.springtx.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final LogRepository logRepository;


    public void joinV1(String username) {
        Member member = new Member(username);
        Log terms = new Log(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(member);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logRepository 호출 시작 ==");
        logRepository.save(terms);
        log.info("== logRepository 호출 종료 ==");
    }

    @Transactional
    public void joinV2(String username) {
        Member member = new Member(username);
        Log terms = new Log(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(member);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logRepository 호출 시작 ==");
        try {
            logRepository.save(terms);
        } catch (RuntimeException e) {
            log.info("log 저장에 실패했습니다. logMessage={}", terms.getMessage()); //로그 저장이 실패할 경우에는 예외를 잡고 정상흐름 반환
            log.info("정상 흐름 반환");
        }

        log.info("== logRepository 호출 종료 ==");
    }
}
