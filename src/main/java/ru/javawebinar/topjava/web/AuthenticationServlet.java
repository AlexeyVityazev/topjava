package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class AuthenticationServlet extends HttpServlet {
    private static final Logger log = getLogger(AuthenticationServlet.class);

    @Override
    //  /auth?email=[email]
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("аутентификация");
        String email = request.getParameter("email");
        User user = InMemoryUserRepository.getRepository().getByEmail(email);
        SecurityUtil.setUser(request, user);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }
}
