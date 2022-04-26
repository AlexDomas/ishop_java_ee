<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="ishop" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="order">
	<c:if test="${CURRENT_MESSAGE != null }">
		<div style="background:#00CED1; color: #000000; opacity: .7" class="alert alert-success hidden-print" role="alert">${CURRENT_MESSAGE }</div>
	</c:if>
	<h4 style="font-style: sans-serif; " class="text-center">Order # ${order.id }</h4>
	<hr />
        <ishop:product_table items="${order.items }" totalCost="${order.totalCost }" showActionColumn="false" />
	<div class="row hidden-print">
		<div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
			<a href="/my-orders" class="btn btn-outline btn-block">Go to My orders</a>
		</div>
	</div>
</div>



