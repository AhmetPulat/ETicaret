<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

<div class="row mt-4">
    <div class="col-md-5">
        <img src="${not empty product.imageUrl ? product.imageUrl : 'https://via.placeholder.com/500x400'}" class="img-fluid rounded" alt="${product.name}">
    </div>
    <div class="col-md-7">
        <h2>${product.name}</h2>
        <h3 class="text-primary mt-3">
            <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₺" maxFractionDigits="2"/>
        </h3>
        <p class="mt-4">${product.description}</p>
        
        <div class="mt-4">
            <c:choose>
                <c:when test="${product.stock > 0}">
                    <div class="alert alert-success d-inline-block">Stokta Var (${product.stock} adet)</div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-danger d-inline-block">Stokta Yok</div>
                </c:otherwise>
            </c:choose>
        </div>

        <form action="${pageContext.request.contextPath}/cart" method="post" class="mt-4">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="productId" value="${product.id}">
            
            <div class="row align-items-center">
                <div class="col-auto">
                    <input type="number" name="quantity" class="form-control" value="1" min="1" max="${product.stock}" ${product.stock == 0 ? 'disabled' : ''}>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-lg btn-primary" ${product.stock == 0 ? 'disabled' : ''}>
                        Sepete Ekle
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

</div>
</body>
</html>