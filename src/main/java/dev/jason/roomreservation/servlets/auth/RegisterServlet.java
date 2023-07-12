package dev.jason.roomreservation.servlets.auth;

import dev.jason.roomreservation.dao.FieldDAO;
import dev.jason.roomreservation.domains.Field;
import dev.jason.roomreservation.services.UserService;
import dev.jason.roomreservation.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Field> fields = FieldDAO.getInstance().findAll();
        fields.forEach(System.out::println);
        request.setAttribute("fields", fields);
        request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getPathInfo());
        Map<String, String> errors = UserValidator.validate(request);

        if (errors.isEmpty()) {
            UserService.getInstance().register(request, response);
        } else {
            //print errors
            errors.forEach((key, value) -> System.out.println(key + " " + value));
            UserValidator.setErrorAttributes(request, errors);
            List<Field> fields = FieldDAO.getInstance().findAll();
            fields.forEach(System.out::println);
            request.setAttribute("fields", fields);
            request.getRequestDispatcher("views/auth/register.jsp").forward(request, response);
        }

    }
}
