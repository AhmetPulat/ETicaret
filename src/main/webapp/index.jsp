<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

<h2 class="mb-4">Tüm Ürünler</h2>

<div class="row">
    <c:forEach var="product" items="${products}">
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <img src="${not empty product.imageUrl ? product.imageUrl : 'https://via.placeholder.com/300x200'}" class="card-img-top" alt="${product.name}">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text text-muted">${product.description}</p>
                    
                    <h4 class="text-primary">
                        <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₺" maxFractionDigits="2"/>
                    </h4>
                    
                    <p class="card-text">
                        <c:choose>
                            <c:when test="${product.stock > 0}">
                                <span class="badge bg-success">Stok: ${product.stock} adet</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">Stokta Yok</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <div class="card-footer bg-transparent border-top-0 d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="btn btn-outline-info">Detay</a>
                    
                    <form action="${pageContext.request.contextPath}/cart" method="post" class="d-inline">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="quantity" value="1">
                        <button type="submit" class="btn btn-primary" ${product.stock == 0 ? 'disabled' : ''}>
                            Sepete Ekle
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</div> <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>