package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryV4_1 implements MemberRepository{


    private final DataSource dataSource;

    public Member save(Member member){

        String sql = "insert into member(member_id, money) values(?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;


        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            throw new MyDbException(e);
        } finally {
            close(con, pstmt, null);
        }
    }


    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";

        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;


        try {
            connection = getConnection();
            psmt = connection.prepareStatement(sql);
            psmt.setString(1, memberId);

            rs = psmt.executeQuery();
            if (rs.next()) {//처음에는 아무것도 안가르키다가 next를 한번 호출해주면 데이터가 있는지 없는지 확인
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }
        } catch (SQLException e) {
            throw new MyDbException(e);
        }  finally {
            close(connection,psmt,rs);
        }

    }


    public void update(String memberId, int money) {
        String sql = "update member set money=? where member_id=?";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize {}", resultSize);
        } catch (SQLException e) {
            throw new MyDbException(e);
        } finally {
            close(connection, pstmt, null);
        }
    }

    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize {}", resultSize);
        } catch (SQLException e) {
            throw new MyDbException(e);
        } finally {
            close(connection, pstmt, null);
        }
    }

    private void close(Connection connection, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        //트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야합니다.
        DataSourceUtils.releaseConnection(connection, dataSource);
        //트랜잭션 동기화 매니저에 보관되어 있는 커넥션이 있다면 닫지않고 그대로 유지해주고
        //아니라면 닫아버린다.
    }


    private Connection getConnection() throws SQLException {
        return DataSourceUtils.getConnection(dataSource);
        //트랜잭션 동기화 매니저가 관리하는 커넥션이 있다면 해당 커넥션을 반환한다.
    }
}
