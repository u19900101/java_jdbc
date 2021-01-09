package test;

import com.alibaba.druid.util.JdbcUtils;
import org.junit.Test;
import utils.DBUtils;

import java.sql.Connection;

/**
 * @author lppppp
 * @create 2020-12-31 16:46
 */
public class _1_JDBCTest {
    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++){
            Connection connection = DBUtils.getConn();
            System.out.println(connection);
        }
    }
}
