<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 3/2/23
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Seats</title>
    <style>
        @import url("https://fonts.googleapis.com/css?family=Montserrat&display=swap");

        @import url("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css");

        body {
            font-family: "Montserrat", sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            background-color: #242333;    /*#DB4437*/
            color: #fff;
            margin: 0;
        }

        * {
            font-family: "Montserrat", sans-serif !important;
            box-sizing: border-box;
        }

        .movie-container {
            margin: 20px 0px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column
        }

        .movie-container select {
            appearance: none;
            -moz-appearance: none;
            -webkit-appearance: none;
            border: 0;
            padding: 5px 15px;
            margin-bottom: 40px;
            font-size: 14px;
            border-radius: 5px;
        }

        .container {
            perspective: 1000px;
            margin: 40px 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .seat {
            background-color: #444451;
            height: 24px;
            width: 30px;
            margin: 6px;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
        }

        .selected {
            background-color: #0081cb;
        }

        .occupied {
            background-color: #DB4437;
        }

        .seat:nth-of-type(2) {
            margin-right: 18px;
        }

        .seat:nth-last-of-type(2) {
            margin-left: 18px;
        }

        .seat:not(.occupied):hover {
            cursor: pointer;
            transform: scale(1.2);
        }

        .showcase .seat:not(.occupied):hover {
            cursor: default;
            transform: scale(1);
        }

        .showcase {
            display: flex;
            justify-content: space-between;
            list-style-type: none;
            background: rgba(0,0,0,0.1);
            padding: 5px 10px;
            border-radius: 5px;
            color: #777;
        }

        .showcase li {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 10px;
        }

        .showcase li small {
            margin-left: 2px;
        }

        .row {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .screen {
            background: #fff;
            height: 70px;
            width: 70%;
            margin: 15px 0;
            transform: rotateX(-45deg);
            box-shadow: 0 3px 10px rgba(255,255,255,0.7);
        }

        p.text {
            margin: 40px 0;
        }

        p.text span {
            color: #0081cb;
            font-weight: 600;
            box-sizing: content-box;
        }

        .credits a {
            color: #fff;
        }


    </style>
</head>
<body>
<div class="movie-container" id="seats">
    <label>Room</label>
    <select id="movie">
        <option value="250">${room.getName()}</option>
    </select>
    <input type="hidden" id="room" name="room" value="${room.getId()}">
    <ul class="showcase">
        <li>
            <div class="seat"></div>
            <small>N/A</small>
        </li>
        <li>
            <div class="seat selected"></div>
            <small>Selected</small>
        </li>
        <li>
            <div class="seat occupied"></div>
            <small>Occupied</small>
        </li>
    </ul>

    <div class="container">
        <c:forEach var="i" begin="1" end="5">
            <div class="row">
                <c:forEach var="j" begin="1" end="6">
                    <c:set var="seatId" value="${(i-1)*6+j}"/>
                    <c:choose>
                        <c:when test="${seats.contains(''.concat(seatId))}">
                            <div class="seat occupied" style="text-align: center; font-size: small">${seatId}</div>
                        </c:when>
                        <c:otherwise>
                            <div class="seat" style="text-align: center; font-size: small">${seatId}</div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </c:forEach>
    </div>

    <p class="text">
    <form id="booking-form" action="/book/result" method="post">
        <button class="btn btn-primary" type="submit">
            Continue
        </button>
    </form>
    </p>

</div>

<script>
    const room = document.getElementById('room');
    const seat = document.getElementById('seats');
    const container = document.querySelector('.container');
    const seats = document.querySelectorAll('.row .seat:not(.occupied)');
    const count = document.getElementById('count');
    const total = document.getElementById('total');
    const movieSelect = document.getElementById('movie');

    let ticketPrice = +movieSelect.value;


    const bookingForm = document.getElementById('booking-form');
    bookingForm.addEventListener('submit', e => {
        e.preventDefault(); // prevent form submission
        const selectedSeat = document.querySelector('.row .seat.selected');
        if (selectedSeat) {
            const seatId = selectedSeat.innerText;


            const xhr = new XMLHttpRequest();
            xhr.open('POST', '/book/result', true);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhr.onreadystatechange = function() {
            };
            xhr.send(JSON.stringify({ seatId: seatId, roomId : room.value }));

            seat.innerHTML = `
                <div class="alert alert-success" role="alert">
                    <h4 class="alert-heading">Success!</h4>
                    <p>Seat has been booked successfully.</p>
                    <hr>
                    <p class="mb-0">To go to the home page click home</p>
                    <hr>
                    <a href="/user" class="btn btn-primary">Home</a>
                </div>
            `;



        }
    });







    //Seat click event
    container.addEventListener('click', e => {
        if (e.target.classList.contains('seat') &&
            !e.target.classList.contains('occupied')) {
            const selectedSeat = document.querySelector('.row .seat.selected');
            if (selectedSeat && selectedSeat !== e.target) {
                selectedSeat.classList.remove('selected');
            }
            e.target.classList.toggle('selected');
        }
        updateSelectedCount();
    });






    // const container = document.querySelector('.container');
    // const seats = document.querySelectorAll('.row .seat:not(.occupied)');
    // const count = document.getElementById('count');
    // const total = document.getElementById('total');
    // const movieSelect = document.getElementById('movie');
    //
    // let ticketPrice = +movieSelect.value;
    //
    // //Update total and count
    // function updateSelectedCount() {
    //   const selectedSeats = document.querySelectorAll('.row .seat.selected');
    //   const selectedSeatsCount = selectedSeats.length;
    //   count.innerText = selectedSeatsCount;
    //   total.innerText = selectedSeatsCount * ticketPrice;
    // }
    //
    // //Movie Select Event
    // movieSelect.addEventListener('change', e => {
    //   ticketPrice = +e.target.value;
    //   updateSelectedCount();
    // });
    //
    // //Seat click event
    // container.addEventListener('click', e => {
    //   if (e.target.classList.contains('seat') &&
    //           !e.target.classList.contains('occupied')) {
    //     e.target.classList.toggle('selected');
    //   }
    //   updateSelectedCount();
    // });
</script>
</body>
</html>
