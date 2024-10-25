package com.javarush.balyuke.simplequest.controller;

import com.javarush.balyuke.simplequest.entities.Answer;
import com.javarush.balyuke.simplequest.entities.Question;

import com.javarush.balyuke.simplequest.repositories.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {

    private final Repository repository = new Repository();
    private final int defaultIdQuestion = 201;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String answerId = request.getParameter("answerid");
        Question question;
        if (answerId != null) {
            question = repository.getAnswerById(Integer.parseInt(answerId)).getTo();
        } else {
            question = repository.getQuestionById(defaultIdQuestion);
        }
        Collection<Answer> answers = repository.getAnswersByFromQuestionId(question.getId());
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        addStatistics(request);
        request.getRequestDispatcher("/quest.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    private void addStatistics(HttpServletRequest request) {
        request.setAttribute("ip", request.getRemoteAddr());        // не работает как надо

        HttpSession session = request.getSession();
        request.setAttribute("userName", session.getAttribute("name"));

        Cookie[] cookies = request.getCookies();
        request.setAttribute("attempt", findCookiesValueByName("gameAttempt", cookies));
    }

    private String findCookiesValueByName(String name, Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
