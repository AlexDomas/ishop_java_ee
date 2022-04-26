<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="addProductSection">

    <div class="info">
        <h2>Additional information</h2>
        <i class="icon ion-ios-ionic-outline" aria-hidden="true"></i>
        <%if (request.getAttribute("errorId") != null) {%>
        <p style="color: rgba(10, 180, 180, 1);"> <%out.println(request.getAttribute("errorId"));%> </p>
        <%}%>

        <%if (request.getAttribute("errorIdRepeat") != null) {%>
        <p style="color: rgba(10, 180, 180, 1);"> <%out.println(request.getAttribute("errorIdRepeat"));%> </p>
        <%}%>   

        <p style="color: rgba(10, 180, 180, 1);">CURRENT ACCOUNT: </p>


        <p style="color: rgba(10, 180, 180, 1);">ID: ${CURRENT_ACCOUNT.id} | NAME: ${CURRENT_ACCOUNT.name} | EMAIL: ${CURRENT_ACCOUNT.email} | ROLE: ${CURRENT_ACCOUNT.role} </p>

        <p style="color: rgba(10, 180, 180, 1);">ALL USERS IN SYSTEM: </p>
        <c:forEach var="user" items="${allUsers}">
            <p style="color: rgba(10, 180, 180, 1);"> ID: ${user.id} | NAME: ${user.name} | EMAIL: ${user.email} | ROLE: ${user.role} </p>
        </c:forEach>

    </div>
    <form action="/user-info" method="POST" class="addProductForm">
        <h2>Delete User From System</h2>
        <ul class="noBullet">
            <li>
                <label for="id"></label>
                <input type="text" class="inputFields" id="id" name="id" placeholder="User ID" required/>
            </li>

            <li id="center-btn">
                <input type="submit" id="join-btn" name="delete" value="Delete User">
            </li>
        </ul>
    </form>
</div>
