    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Reservation</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>

<h1 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Choose a date for your reservation</h1>

<form class="row g-3" method="post">

    <div class="col-md-3 offset-2">
        <label for="something" class="form-label">Reservation Date</label>
        <div class="input-group date" id="something">
            <input type="date" class="form-control" name="date" placeholder="03/04/2023" required/>
            <i class="fa fa-calendar"></i>
        </div>
        <c:if test="${date_error != null}">
            <snap class="small text-danger"><c:out value="${date_error}"></c:out></snap>
        </c:if>
    </div>

    <div class="col-12 offset-2">
        <button class="btn btn-primary" type="submit">Submit</button>
        <button class="btn btn-primary" type="button" onclick="location.href='/user'">Back</button>
    </div>

</form>
<jsp:include page="/fragments/css.jsp"/>
</body>
</html>