<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>E-Ticaret Portalı</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">E-Ticaret</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/home">Ana Sayfa</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <c:if test="${empty sessionScope.loggedUser}">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Giriş Yap</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Kayıt Ol</a></li>
        </c:if>
        
        <c:if test="${not empty sessionScope.loggedUser}">
            <li class="nav-item"><span class="nav-link text-white">Hoşgeldin, ${sessionScope.loggedUser.fullName}</span></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/order">Siparişlerim</a></li>
            <c:if test="${sessionScope.loggedUser.role eq 'admin'}">
                <li class="nav-item"><a class="nav-link text-warning" href="${pageContext.request.contextPath}/admin/dashboard">Admin Paneli</a></li>
            </c:if>
            <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Çıkış</a></li>
        </c:if>
        
        <li class="nav-item">
            <a class="nav-link text-info" href="${pageContext.request.contextPath}/cart">🛒 Sepet</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container mt-4">