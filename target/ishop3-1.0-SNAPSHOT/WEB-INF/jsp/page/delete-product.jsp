<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
    </div>
    <form action="/delete-product" method="POST" class="addProductForm">
        <h2>Delete Product From Catalog</h2>
        <ul class="noBullet">
            <li>
                <label for="id_pr"></label>
                <input type="text" class="inputFields" id="id_pr" name="id_pr" placeholder="Product ID" required/>
            </li>

            <li id="center-btn">
                <input type="submit" id="join-btn" name="delete" value="Delete Product">
            </li>
        </ul>
    </form>
</div>