<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ishop" tagdir="/WEB-INF/tags"%>





<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-main" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>

            </button>
            <a style="font-size: 22px; color: #20B2AA; font-family: 'Galada', cursive;" class="navbar-brand" href="/products">IShop Products</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-main">
            <u1 class="nav navbar-nav navbar-left">  
                <li><a style="color: #00CED1;" href="/products">Home</a></li>
                <li><a style="color: #00CED1;" href="/about-us">About us</a></li>
                <li role="separator" class="divider"></li>
                <li><a style="color: #00CED1;" href="#">Discounts</a></li>
                <li role="separator" class="divider"></li>
                <li><a style="color: #00CED1;" href="#">News</a></li>
                <li><a style="color: #00CED1;" href="#">Reviews</a></li>
            </u1>
            </li>



            <div class="collapse navbar-collapse" id="ishopNav">
                <c:choose>
                    <c:when test="${CURRENT_ACCOUNT != null}">
                <u1 id="currentShoppingCart" class="nav navbar-nav navbar-right ${(CURRENT_SHOPPING_CART == null) ? 'hidden' : ''} ">
                    <div class="dropdown">
                        <a style= "color: #00CED1; margin-left: 12px;" href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-shopping-cart" aria-hidden="true"></i> 
                            Shopping cart(<span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span>)
                            <span class="caret"></span>
                        </a>
                        <div class="dropdown-menu shopping-cart-desc">
                            Total count: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span><br>
                            Total cost: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost}</span><br>
                            <a href="/shopping-cart" class="btn btn-outline btn-block">View cart</a>
                        </div>
                    </div>
                </u1>
                            </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${CURRENT_ACCOUNT != null}">
                        <ul  class="nav navbar-nav navbar-right">
                            <li><a style="color: #00CED1;">Welcome ${CURRENT_ACCOUNT.description}</a></li>
                            <li><a  style="color: #00CED1;" href = "/my-orders">My orders</a></li>
                            <li><a  style="color: #00CED1;" href = "javascript:void(0);" 
                                    class="post-request" data-url="/sign-out">Sign out</a></li>
                        </ul>
                    </c:when> 
                    <c:when test="${CURRENT_REQUEST_URL != '/sign-in' and CURRENT_REQUEST_URL != '/shopping-car'}">
                        <ishop:sign-in-header classes="btn-block" />
                    </c:when>
                    
                </c:choose>
            </div>
        </div>

</nav>





