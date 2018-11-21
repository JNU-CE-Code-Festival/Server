package com.example.jnucecodefestival.connectionmaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JNUCodeFstvConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://172.18.102.128/programming_contest", "contest", "sslab08295860");
        return connection;
    }
}
