<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Paneli</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/dashboard">Yönetim Paneli</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="adminNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/dashboard">Özet</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/categories">Kategori Yönetimi</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/products">Ürün Yönetimi</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/orders">Sipariş Yönetimi</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link text-white" href="${pageContext.request.contextPath}/home">Siteye Dön</a>
        </li>
        <li class="nav-item">
            <a class="nav-link fw-bold text-dark" href="${pageContext.request.contextPath}/logout">Çıkış Yap</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container mt-4">