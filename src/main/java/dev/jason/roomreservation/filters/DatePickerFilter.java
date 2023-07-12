package dev.jason.roomreservation.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebFilter(filterName = "DatePickerFilter", urlPatterns = "/book")
public class DatePickerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var res = (HttpServletResponse) response;
        String startDate = request.getParameter("date");

        LocalDate reservationDate = null;

        if (req.getMethod().equalsIgnoreCase("post")) {

            if (startDate != null) {
                reservationDate = parseDate(startDate);
            } else {
                req.setAttribute("date_error", "Choose a date");
                req.getRequestDispatcher("/views/user/booking/datepicker.jsp").forward(req, res);
            }

            if (getDifferenceBetweenDates(LocalDate.now(), reservationDate) < 0) {
                req.setAttribute("date_error", "You can't book a room for a date in the past");
                req.getRequestDispatcher("/views/user/booking/datepicker.jsp").forward(req, res);
            } else filterChain.doFilter(req, res);

        } else
            filterChain.doFilter(req, res);
    }

    public static int getDifferenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        return endDate.compareTo(startDate);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }
}
