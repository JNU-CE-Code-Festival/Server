package com.example.jnucecodefestival.dao;

import com.example.jnucecodefestival.connectionmaker.ConnectionMaker;
import com.example.jnucecodefestival.service.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProblemDao {
    private ConnectionMaker connectionMaker;

    public ProblemDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public Problem get(int problemNum) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from where problemNum = ?");
        preparedStatement.setInt(1, problemNum);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Problem problem = new Problem();
        problem.setId(resultSet.getInt("id"));
        problem.setGrade(resultSet.getInt("grade"));
        problem.setProblemNum(resultSet.getInt("problemNum"));
        problem.setProblemTitle(resultSet.getString("problemTitle"));
        problem.setProblemContent(resultSet.getString("problemContent"));
        problem.setProblemTestCase(resultSet.getString("problemTestCase"));
        problem.setProblemTestCaseAnswer(resultSet.getString("problemTestCaseAnswer"));
        problem.setProblemInput(resultSet.getString("problemInput"));
        problem.setProblemAnswer(resultSet.getString("problemAnswer"));
        problem.setProblemInputDescription(resultSet.getString("problemInputDescription"));
        problem.setProblemOutputDescription(resultSet.getString("problemOutputDescription"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return problem;
    }
}
