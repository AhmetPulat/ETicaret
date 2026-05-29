<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="admin-header.jsp" />

<div class="row justify-content-center">
    <div class="col-md-8">
        <div class="card mb-5 mt-3">
            <div class="card-header bg-success text-white">
                <h4 class="m-0">Yeni Ürün Ekle</h4>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin/products" method="post">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Ürün Adı <span class="text-danger">*</span></label>
                            <input type="text" name="name" class="form-control" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Kategori <span class="text-danger">*</span></label>
                            <select name="categoryId" class="form-select" required>
                                <option value="">Kategori Seçin...</option>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Açıklama</label>
                        <textarea name="description" class="form-control" rows="3"></textarea>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Fiyat (₺) <span class="text-danger">*</span></label>
                            <input type="number" step="0.01" name="price" class="form-control" min="0.01" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Stok Miktarı <span class="text-danger">*</span></label>
                            <input type="number" name="stock" class="form-control" min="0" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Görsel URL (İsteğe Bağlı)</label>
                        <input type="url" name="imageUrl" class="form-control" placeholder="https://ornek.com/resim.jpg">
                    </div>
                    
                    <div class="d-flex justify-content-between mt-4">
                        <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-secondary">İptal</a>
                        <button type="submit" class="btn btn-success px-5">Ürünü Kaydet</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>