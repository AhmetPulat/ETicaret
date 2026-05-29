<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

<h2>Alışveriş Sepetim</h2>

<c:if test="${param.error != null}">
    <div class="alert alert-danger">${param.error}</div>
</c:if>

<c:choose>
    <c:when test="${empty sessionScope.cart}">
        <div class="alert alert-info mt-4">Sepetiniz şu an boş. Alışverişe başlamak için <a href="${pageContext.request.contextPath}/home">tıklayın</a>.</div>
    </c:when>
    <c:otherwise>
        <div class="table-responsive mt-4">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Ürün Adı</th>
                        <th>Birim Fiyat</th>
                        <th>Adet</th>
                        <th>Ara Toplam</th>
                        <th>İşlem</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="totalSum" value="0" />
                    <c:forEach var="item" items="${sessionScope.cart}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td><fmt:formatNumber value="${item.product.price}" type="currency" currencySymbol="₺"/></td>
                            <td>${item.quantity}</td>
                            <td><fmt:formatNumber value="${item.subtotal}" type="currency" currencySymbol="₺"/></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/cart" method="post" class="d-inline">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="productId" value="${item.product.id}">
                                    <button type="submit" class="btn btn-sm btn-danger">Çıkar</button>
                                </form>
                            </td>
                        </tr>
                        <c:set var="totalSum" value="${totalSum + item.subtotal}" />
                    </c:forEach>
                    <tr>
                        <td colspan="3" class="text-end"><strong>Genel Toplam:</strong></td>
                        <td colspan="2" class="text-primary fs-5">
                            <strong><fmt:formatNumber value="${totalSum}" type="currency" currencySymbol="₺"/></strong>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        
        <div class="text-end mt-3">
            <form action="${pageContext.request.contextPath}/order" method="post">
                <button type="submit" class="btn btn-success btn-lg">Siparişi Tamamla</button>
            </form>
        </div>
    </c:otherwise>
</c:choose>

</div>
</body>
</html>