package com.example.jnucecodefestival.dao;

import com.example.jnucecodefestival.connectionmaker.ConnectionMaker;
import com.example.jnucecodefestival.service.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RankDao {
    private ConnectionMaker connectionMaker;

    public RankDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void addRank(Rank rank) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.makeConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rank(id, userName, problemNum, submitCount, timeStamp, language, score) VALUES(?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setInt(1,rank.getId());
        preparedStatement.setString(2, rank.getUserName());
        preparedStatement.setInt(3, rank.getProblemNum());
        preparedStatement.setInt(4, rank.getSubmitCount());
        preparedStatement.setString(5, rank.getTimestamp().toString());
        preparedStatement.setString(6, rank.getLanguage());
        preparedStatement.setBoolean(7, rank.getScore());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
