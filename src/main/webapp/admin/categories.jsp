<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="admin-header.jsp" />

<h2 class="mb-4">Kategori Yönetimi</h2>
<c:if test="${param.success != null}">
    <div class="alert alert-success">${param.success}</div>
</c:if>

<div class="row">
    <div class="col-md-4 mb-4">
        <div class="card">
            <div class="card-header bg-success text-white">Yeni Kategori Ekle</div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/categories" method="post">
                    <div class="mb-3">
                        <label class="form-label">Kategori Adı</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Açıklama</label>
                        <textarea name="description" class="form-control" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Ekle</button>
                </form>
            </div>
        </div>
    </div>
    
    <div class="col-md-8">
        <table class="table table-striped table-bordered align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Kategori Adı</th>
                    <th>Açıklama</th>
                    <th>Durum</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}">
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>${category.description}</td>
                        <td>
                            <span class="badge ${category.active ? 'bg-primary' : 'bg-danger'}">
                                ${category.active ? 'Aktif' : 'Pasif'}
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>