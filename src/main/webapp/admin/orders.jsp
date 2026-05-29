<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="admin-header.jsp" />

<h2 class="mb-4">Tüm Siparişler</h2>

<c:if test="${param.success != null}">
    <div class="alert alert-success">Sipariş durumu başarıyla güncellendi.</div>
</c:if>

<div class="table-responsive">
    <table class="table table-bordered table-striped align-middle">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tarih</th>
                <th>Toplam Tutar</th>
                <th>Mevcut Durum</th>
                <th>Durum Güncelle</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${allOrders}">
                <tr>
                    <td>#${order.id}</td>
                    <td><fmt:formatDate value="${order.orderDate}" pattern="dd.MM.yyyy HH:mm"/></td>
                    <td><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₺"/></td>
                    <td>
                        <span class="badge bg-secondary">${order.status}</span>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/orders" method="post" class="d-flex">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <select name="status" class="form-select form-select-sm me-2">
                                <option value="Beklemede" ${order.status == 'Beklemede' ? 'selected' : ''}>Beklemede</option>
                                <option value="Hazırlanıyor" ${order.status == 'Hazırlanıyor' ? 'selected' : ''}>Hazırlanıyor</option>
                                <option value="Kargoya Verildi" ${order.status == 'Kargoya Verildi' ? 'selected' : ''}>Kargoya Verildi</option>
                                <option value="Tamamlandı" ${order.status == 'Tamamlandı' ? 'selected' : ''}>Tamamlandı</option>
                                <option value="İptal Edildi" ${order.status == 'İptal Edildi' ? 'selected' : ''}>İptal Edildi</option>
                            </select>
                            <button type="submit" class="btn btn-sm btn-primary">Kaydet</button>
                        </form>
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