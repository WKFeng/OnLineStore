package main.com.store.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {
    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

    public static DataSource getDataSource() {
        return comboPooledDataSource;
    }

    public static Connection getConnection() {
        try {

            return comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)  {
        try {
            System.out.println(comboPooledDataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
