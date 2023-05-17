package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class UnCheckedAppTest {

    @Test
    void unchecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(RuntimeException.class);
    }

    static class Controller {
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient {
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }
    }

    /**
     * 예외 전환
     * 리포지토리에서 체크 예외인 SQLException 이 발생하면 런타임 예외인 RuntimeSQLException 으로
     * 전환해서 예외를 던진다.
     * 참고로 이때 기존 예외를 포함해주어야 예외 출력시 스택 트레이스에서 기존 예외도 함께 확인할 수
     * 있다.
     */
    static class Repository {
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
                // SQLException RuntimeException 으로 예외 처리 (stackTrace를 확인하기 위해 e 를 넘겨준다)
                throw new RuntimeSQLException(e);
            }
        }

        public void runSQL() throws SQLException {
//            throw new SQLException("ex");

        }

    }

    static class RuntimeConnectException extends RuntimeException {
        public RuntimeConnectException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    }
}
