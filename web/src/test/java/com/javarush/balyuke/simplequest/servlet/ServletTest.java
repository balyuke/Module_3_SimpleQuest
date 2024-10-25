package com.javarush.balyuke.simplequest.servlet;

import com.javarush.balyuke.simplequest.controller.QuestServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServletTest {

    @Test
    public void testDoPost() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("attempt")).thenReturn(2);
//        Mockito.when(session.getAttribute("name")).thenReturn(false);

        QuestServlet servlet = new QuestServlet();
        servlet.doPost(request, response);

        assertEquals(2, (int) session.getAttribute("attempt"));
    }
}