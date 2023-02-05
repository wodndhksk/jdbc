package hello.jdbc.repository;

import hello.jdbc.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryVoTest {

    MemberRepositoryVo memberRepositoryVo = new MemberRepositoryVo();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV0", 10000);
        Member save = memberRepositoryVo.save(member);
        System.out.println(save);
    }

}