package dev.jason.roomreservation.servlets.admin.reservation;

import dev.jason.roomreservation.dao.ReservationDAO;
import dev.jason.roomreservation.domains.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllReservationsServlet", value = "/sheengoadmin/all")
public class AllReservationsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reservation> reservations = ReservationDAO.getInstance().findAll();
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/views/admin/all.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
