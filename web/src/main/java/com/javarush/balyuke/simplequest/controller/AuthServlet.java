package com.javarush.balyuke.simplequest.controller;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        int attempt = 1;
        Cookie[] cookies = req.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("gameAttempt")) {
                    attempt = Integer.parseInt(cookie.getValue());
                    attempt++;
                    cookie.setValue("" + attempt);
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        if (attempt == 1) {
            Cookie cookie = new Cookie("gameAttempt", "" + attempt);
            resp.addCookie(cookie);
        }

        HttpSession session = req.getSession(true);
        String name = req.getParameter("name");
        session.setAttribute("name", name);
        resp.sendRedirect(req.getContextPath() + "/quest");
    }
}
