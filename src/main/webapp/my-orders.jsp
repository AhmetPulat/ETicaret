<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

<h2 class="mb-4">Geçmiş Siparişlerim</h2>

<c:if test="${param.success != null}">
    <div class="alert alert-success">${param.success}</div>
</c:if>

<c:choose>
    <c:when test="${empty myOrders}">
        <div class="alert alert-warning">Henüz hiç sipariş vermemişsiniz.</div>
    </c:when>
    <c:otherwise>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Sipariş No</th>
                    <th>Tarih</th>
                    <th>Toplam Tutar</th>
                    <th>Durum</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${myOrders}">
                    <tr>
                        <td>#${order.id}</td>
                        <td><fmt:formatDate value="${order.orderDate}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <td><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₺"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${order.status == 'Beklemede'}"><span class="badge bg-warning text-dark">${order.status}</span></c:when>
                                <c:when test="${order.status == 'Hazırlanıyor'}"><span class="badge bg-info">${order.status}</span></c:when>
                                <c:when test="${order.status == 'Kargoya Verildi'}"><span class="badge bg-primary">${order.status}</span></c:when>
                                <c:when test="${order.status == 'Tamamlandı'}"><span class="badge bg-success">${order.status}</span></c:when>
                                <c:otherwise><span class="badge bg-secondary">${order.status}</span></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

</div>
</body>
</html>