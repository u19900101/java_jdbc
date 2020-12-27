import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lppppp
 * @create 2020-12-27 19:56
 */

public class _1_C3P0 {
    // 方式1——获取连接c3p0
    @Test
    public void getConnect() throws PropertyVetoException, SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&rewriteBatchedStatements=true");
        cpds.setUser("root");
        cpds.setPassword("kk");

        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }

    ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    @Test
    public void getConnect2() throws PropertyVetoException, SQLException {
        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }
}
