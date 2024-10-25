package com.javarush.balyuke.simplequest.controller;

import java.io.IOException;

import com.javarush.balyuke.simplequest.servlet.TestDBServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AuthServlet.class);

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

        HttpSession session = req.getSession(true); // req.getSession();
        String name = req.getParameter("name");
        session.setAttribute("name", name);
        log.info("Пользователь {} начал игру",name);
        resp.sendRedirect(req.getContextPath() + "/quest");
    }
}
