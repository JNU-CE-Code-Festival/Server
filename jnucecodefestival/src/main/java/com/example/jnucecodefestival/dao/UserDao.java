package com.example.jnucecodefestival.dao;

import com.example.jnucecodefestival.connectionmaker.ConnectionMaker;
import com.example.jnucecodefestival.service.User;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.makeConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users where id = ?");
        preparedStatement.setString(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setGrade(resultSet.getInt("grade"));
        user.setScoreGet(resultSet.getInt("score_get"));
        user.setFirstBool(resultSet.getBoolean("first_bool"));
        user.setSecondBool(resultSet.getBoolean("second_bool"));
        user.setThirdBool(resultSet.getBoolean("third_bool"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }
}
