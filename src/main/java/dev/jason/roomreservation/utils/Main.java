package dev.jason.roomreservation.utils;

import java.util.Scanner;

public class Main {
//    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(Encrypt.hashPassword("ADMIN"));

//    RoomDAO.getInstance().getReservedSeatsOnDate(LocalDate.of(2023, 03, 10), RoomDAO.getInstance().findById(1L)).forEach(System.out::println);


//        Field backend = Field.builder()
//                .name("Frontend")
//                .build();
//
//        FieldDAO.getInstance().save(backend);


//        User user = User.builder()
//                .username("user")
//                .email("strengthnumberone@gmail.com")
//                .password("12345")
//                .field(FieldDAO.getInstance().findById(2L))
//                .build();
//        UserDAO.getInstance().save(user);


//        Room twitter = Room.builder()
//                .name("Instagram")
//                .capacity(10)
//                .field(FieldDAO.getInstance().findById(2L))
//                .build();
//
//        RoomDAO.getInstance().save(twitter);


//        Reservation build = Reservation.builder()
//                .room(RoomDAO.getInstance().findById(1L))
//                .user(UserDAO.getInstance().findById(4L))
//                .reservationDate(LocalDate.now().plusDays(10))
//                .seatNumber(10)
//                .build();
//        ReservationDAO.getInstance().save(build);
//
//        List<Reservation> activeReservations = ReservationDAO.getInstance().getActiveReservations();
//        activeReservations.forEach(System.out::println);


//        ReservationDAO.getInstance().getAvailableSeats(LocalDate.now().plusDays(10), 2L).forEach(System.out::println);


//        System.out.print("Enter the email address: ");
//        String to = scanner.nextLine();
//        System.out.print("Enter the subject: ");
//        String subject = scanner.nextLine();
//        System.out.print("Enter the body text: ");
//        String bodyText = scanner.nextLine();
//        EmailService.send(to, subject, bodyText);

//
//        ExecutorService   service = Executors.newWorkStealingPool();
//
//        HttpClient client = HttpClient.newHttpClient();
//        try {
//            for (int i = 0; i < 100000000; i++) {
//                System.out.println(i);
//               service.submit(()-> client.send(HttpRequest.newBuilder()
//                        .uri(URI.create("http://192.168.108.168:8080"))
//                        .build(), HttpResponse.BodyHandlers.ofString()));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
