// package com.example.jnucecodefestival.dao;

// import com.example.jnucecodefestival.service.Problem;

// import java.sql.*;

// public class ProblemDao {
//     public Problem get(String id) throws ClassNotFoundException, SQLException {
//         Class.forName("org.mariadb.jdbc.Driver");
//         Connection connection = DriverManager.getConnection(
//                 "jdbc:mariadb://172.18.102.128/programming_contest", "contest", "sslab08295860");

//         PreparedStatement preparedStatement = connection.prepareStatement(
//                 "select * from users where id = ?");
//         preparedStatement.setString(1, id);

//         ResultSet resultSet = preparedStatement.executeQuery();
//         resultSet.next();

//         resultSet.toString();
//         // Problem problem = new Problem();

//         // problem.setProblemNum(resultSet.getString("problemNum"));
//         // problem.set (resultSet.getString("id"));
//         // problem.setPassword(resultSet.getString("password"));
//         // problem.setName(resultSet.getString("name"));
//         // problem.setGrade(resultSet.getInt("grade"));
//         // problem.setScoreGet(resultSet.getInt("score_get"));
//         // problem.setFirstBool(resultSet.getBoolean("first_bool"));
//         // problem.setSecondBool(resultSet.getBoolean("second_bool"));
//         // problem.setThirdBool(resultSet.getBoolean("third_bool"));

//         resultSet.close();
//         preparedStatement.close();
//         connection.close();

//         return problem;
//     }
// }
