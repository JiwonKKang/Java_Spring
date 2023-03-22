package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member("Jiwon", 25);
        Member saveMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId());

        assertThat(findMember).isSameAs(saveMember);

        System.out.println("findMember = " + findMember.toString());
        System.out.println("saveMember = " + saveMember.toString());
    }

    @Test
    void findAll() {

        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 21);

        Member saveMember1 = memberRepository.save(member1);
        Member saveMember2 = memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();

        assertThat(memberList.size()).isEqualTo(2);
    }
}