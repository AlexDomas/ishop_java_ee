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
        
    </div>

    <form action="/add-product" method="POST" class="addProductForm" name="addform" enctype="multipart/form-data">
        <h2>Add Product To Catalog</h2>
        <ul class="noBullet">
            <li>
                <label for="id"></label>
                <input type="text" class="inputFields" id="id" name="id" placeholder="Product ID" required/>
            </li>
            <li>
                <label for="name"></label>
                <input type="text" class="inputFields" id="password" name="name" placeholder="Name"/>
            </li>
            <li>
                <label for="desc"></label>
                <input type="text" class="inputFields" id="desc" name="desc" placeholder="Description"/>
            </li>
            <li>


                <input class="fileInput" type="file" name="image" accept=".jpeg, .jpg, .png" required>

            </li>

            <li>
                <input class = "inputFields" type="text" id="price" name="price" placeholder="Price" required>
            </li>

            <li>
                <select class = "inputFields" name="category">
                    <c:forEach var="category" items="${CATEGORY_LIST}">

                        <option style="background: #101010" value="${category.name}"> ${category.name}</option>

                    </c:forEach>
                </select>
            </li>

            <li>
                <select class = "inputFields" name="producer">
                    <c:forEach var="producer" items="${PRODUCER_LIST}">

                        <option  style="background: #101010" value="${producer.name}"> ${producer.name}</option>

                    </c:forEach>
                </select>
            </li>


            <li id="center-btn">
                <input type="submit" id="join-btn" name="add" value="Add Product">
            </li>
        </ul>
    </form>

    
</div>



