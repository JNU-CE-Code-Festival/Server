package com.example.jnucecodefestival.controller;

import com.example.jnucecodefestival.connectionmaker.ConnectionMaker;
import com.example.jnucecodefestival.connectionmaker.JNUCodeFstvConnectionMaker;
import com.example.jnucecodefestival.dao.ProblemDao;
import com.example.jnucecodefestival.service.Problem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping("/403")
public class MainPageController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) throws SQLException, ClassNotFoundException {
        /*String str = "Hello World";

        model.addAttribute("problem", str);
*/
        ConnectionMaker connectionMaker = new JNUCodeFstvConnectionMaker();

        ProblemDao problemDao = new ProblemDao(connectionMaker);
        Problem problem = problemDao.get(1);

        System.out.println(problem.getId());
        System.out.println(problem.getGrade());
        System.out.println(problem.getProblemNum());
        System.out.println(problem.getProblemTitle());
        System.out.println(problem.getProblemContent());
        System.out.println(problem.getProblemTestCase());
        System.out.println(problem.getProblemTestCaseAnswer());
        System.out.println(problem.getProblemInput());
        System.out.println(problem.getProblemAnswer());
        System.out.println(problem.getProblemInputDescription());
        System.out.println(problem.getProblemOutputDescription());

        return "main";
    }
}
