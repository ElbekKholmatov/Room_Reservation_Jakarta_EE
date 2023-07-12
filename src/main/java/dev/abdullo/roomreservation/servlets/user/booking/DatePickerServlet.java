package dev.abdullo.roomreservation.servlets.user.booking;

import dev.abdullo.roomreservation.utils.ThreadSafeContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "DatePickerServlet", value = "/book")
public class DatePickerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/user/booking/datepicker.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        String date = request.getParameter("date");
        LocalDate reservationDate = parseDate(date);  // 2023-03-02
        ThreadSafeContainer.reservationDates.put(Long.parseLong(userId), reservationDate);
        response.sendRedirect("/book/available");
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }
}
