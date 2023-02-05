package hello.jdbc.repository;

import hello.jdbc.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryVoTest {

    MemberRepositoryVo memberRepositoryVo = new MemberRepositoryVo();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV3", 10000);

        //save
        Member save = memberRepositoryVo.save(member);

        //findById
        Member findMember = memberRepositoryVo.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member == findMember {}", member == findMember);
        log.info("member equals findMember {}", member.equals(findMember));
        log.info("member={}, findMember={}", member.hashCode(), findMember.hashCode());

        Assertions.assertThat(findMember).isEqualTo(member);
    }

}