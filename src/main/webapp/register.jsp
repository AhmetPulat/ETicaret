<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp" />

<div class="row justify-content-center">
    <div class="col-md-6">
        <div class="card mt-4 mb-5">
            <div class="card-header bg-success text-white text-center">
                <h4>Yeni Hesap Oluştur</h4>
            </div>
            <div class="card-body">
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/register" method="post">
                    <div class="mb-3">
                        <label class="form-label">Ad Soyad</label>
                        <input type="text" name="fullName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">E-Posta Adresi</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Şifre</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Telefon</label>
                        <input type="text" name="phone" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Açık Adres</label>
                        <textarea name="address" class="form-control" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Kayıt Ol</button>
                </form>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>