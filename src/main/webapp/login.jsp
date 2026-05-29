<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp" />

<div class="row justify-content-center">
    <div class="col-md-5">
        <div class="card mt-5">
            <div class="card-header bg-primary text-white text-center">
                <h4>Giriş Yap</h4>
            </div>
            <div class="card-body">
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
                <c:if test="${param.message == 'registered'}">
                    <div class="alert alert-success">Kayıt başarılı! Şimdi giriş yapabilirsiniz.</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="mb-3">
                        <label class="form-label">E-Posta Adresi</label>
                        <input type="email" name="email" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Şifre</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Giriş Yap</button>
                </form>
                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/register">Hesabın yok mu? Kayıt ol.</a>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>