<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="admin-header.jsp" />

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Ürün Yönetimi</h2>
    <a href="${pageContext.request.contextPath}/admin/products?action=add" class="btn btn-success">Yeni Ürün Ekle</a>
</div>

<c:if test="${param.success != null}">
    <div class="alert alert-success">${param.success}</div>
</c:if>

<div class="table-responsive">
    <table class="table table-bordered table-striped align-middle">
        <thead class="table-dark">
            <tr>
                <th>Görsel</th>
                <th>ID</th>
                <th>Ürün Adı</th>
                <th>Fiyat</th>
                <th>Stok</th>
                <th>Durum</th>
                <th>İşlem</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><img src="${not empty product.imageUrl ? product.imageUrl : 'https://via.placeholder.com/50'}" width="50" height="50" class="img-thumbnail" alt="Ürün"></td>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₺"/></td>
                    <td>${product.stock}</td>
                    <td>
                        <span class="badge ${product.active ? 'bg-success' : 'bg-danger'}">
                            ${product.active ? 'Aktif' : 'Pasif'}
                        </span>
                    </td>
                    <td>
                        <button class="btn btn-sm btn-warning">Düzenle</button>
                        <button class="btn btn-sm btn-danger">Sil</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>