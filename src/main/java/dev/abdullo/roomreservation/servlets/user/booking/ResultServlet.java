package dev.abdullo.roomreservation.servlets.user.booking;

import com.google.gson.Gson;
import dev.abdullo.roomreservation.dao.ReservationDAO;
import dev.abdullo.roomreservation.dao.RoomDAO;
import dev.abdullo.roomreservation.dao.UserDAO;
import dev.abdullo.roomreservation.domains.Reservation;
import dev.abdullo.roomreservation.utils.ThreadSafeContainer;
import dev.abdullo.roomreservation.utils.javaxmail.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ResultServlet", value = "/book/result")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        Gson gson = new Gson();
        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        Response response1 = gson.fromJson(requestBody, Response.class);
        String roomId = response1.roomId();
        long rooId = Long.parseLong(roomId);
        Integer seat = Integer.parseInt(response1.seatId());
        long uId = Long.parseLong(userId);
        LocalDate date = ThreadSafeContainer.reservationDates.get(uId);
        Reservation build = Reservation.builder()
                .user(UserDAO.getInstance().findById(uId))
                .seatNumber(seat)
                .reservationDate(date)
                .room(RoomDAO.getInstance().findById(rooId))
                .build();
        ReservationDAO.getInstance().save(build);
        System.out.println("Saved!");
    }
}
