package com;

import com.zaxxer.hikari.pool.HikariProxyConnection;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionCreator;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JavaPostgreSqlVersion implements java.sql.Driver {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "Aa123456";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("CREATE TABLE [Books] ")) {

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSqlVersion.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return DriverManager.getConnection(url, info);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return true;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return true;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}