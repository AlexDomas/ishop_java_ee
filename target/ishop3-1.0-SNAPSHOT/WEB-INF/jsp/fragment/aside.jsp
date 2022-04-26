<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="ishop" tagdir="/WEB-INF/tags"%>

<div class="visible-xs-block xs-option-container">
    <a style="color: #00CED1" class="pull-right" data-toggle="collapse" href="#productCatalog">Product catalog <span class="caret"></span></a>
    <a data-toggle="collapse find" href="#findProducts">Find products <span class="caret"></span></a>
</div>

<!-- Search form-->

<form class="search" action="/search">
    <div id="findProducts" class="panel panel-default collapse">
        <div class="panel-heading"> Find products</div>
        <div class="panel-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Search query" value="${searcherProductForm.query}">
                <span class="input-group-btn">
                    <a id="goSearch" class="btn btn-default">Search</a>
                </span>
            </div>
            <div class="more-options">
                <a class="filter" data-toggle="collapse" href="#searchOptions">More filters<span class="caret">

                    </span></a>
            </div>
        </div>
        <div id="searchOptions" class="collapse ${searcherProductForm.categoriesNotEmpty or searcherProductForm.producersNotEmpty ? 'in' : ''}" >
            <ishop:category-filter categories="${CATEGORY_LIST}" searcherProductForm = "${searcherProductForm}"/>
            <ishop:producer-filter producers="${PRODUCER_LIST}" searcherProductForm = "${searcherProductForm}"/>
        </div>



    </div>


</form>
<!-- Search form-->

<!-- Categories-->
<div id="productCatalog" class="panel panel-default collapse">
    <div style="font-weight: bold;" class="panel-heading"> Product catalog</div>
    <div class="list-group">
        <c:forEach var="category" items="${CATEGORY_LIST}">
            <a href="/products${category.url}" class="list-group-item ${selectedCategoryUrl == category.url ? 'list-group-item-light' : ''}">
                <span class="badge">${category.productCount}</span> ${category.name}
            </a>
        </c:forEach>
    </div>
</div>

<div id="productCatalog" class="panel panel-default collapse ${(CURRENT_ACCOUNT.role == 2 || CURRENT_ACCOUNT == null)  ? 'hidden' : ''} ">
    <div style="font-weight: bold;" class="panel-heading"> Administration Tools</div>
    <div class="list-group">
        <a style="text-align: center; " href="/add-product" class="list-group-item ">Add product</a>
        <a style="text-align: center;" href="/delete-product" class="list-group-item">Delete product</a>
        <a style="text-align: center;" href="/user-info" class="list-group-item">Users Info</a>
    </div>
</div>

