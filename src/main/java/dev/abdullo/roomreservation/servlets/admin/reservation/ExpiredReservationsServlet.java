package dev.abdullo.roomreservation.servlets.admin.reservation;

import dev.abdullo.roomreservation.dao.ReservationDAO;
import dev.abdullo.roomreservation.domains.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExpiredReservationsServlet", value = "/tafakkooradmin/expired")
public class ExpiredReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = ReservationDAO.getInstance().getExpiredReservations();
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/views/admin/expired.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
