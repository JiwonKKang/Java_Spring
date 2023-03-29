package jiwon.selfmade;

import jakarta.annotation.PostConstruct;
import jiwon.selfmade.entity.Item;
import jiwon.selfmade.entity.Member;
import jiwon.selfmade.repository.ItemRepository;
import jiwon.selfmade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestInit {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @PostConstruct
    private void init() {
        Member member = memberRepository.save(new Member("kjwp1", "wldnjsWkd1!", "강지원"));
        Item item1 = itemRepository.save(new Item("Spring", 10000, 1));
        Item item2 = itemRepository.save(new Item("JPA", 20000, 2));

        log.info("PostConstruct = {}", member);
    }
}
