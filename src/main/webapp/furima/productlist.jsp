<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>フリマ一覧</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/myStyle.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/furima.css">
</head>
<body>

<%@ include file="../header.jsp"%>

<div class="container my-5">

    <!-- Top info -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <strong>
                ${fn:length(furimaList)} 件の商品があります
            </strong>
        </div>
        <div>
            <select class="form-control form-control-sm">
                <option>新着順</option>
                <option>価格が安い順</option>
                <option>価格が高い順</option>
            </select>
        </div>
    </div>

    <!-- Product Grid -->
    <div class="row">

        <c:forEach var="item" items="${furimaList}">
            <div class="col-md-3 mb-4">

                <div class="card h-100 furima-card">

                    <!-- Image -->
                    <a href="${pageContext.request.contextPath}/furima/detail.do?postId=${item.postId}">
                        <img src="${empty item.imagePath 
                                   ? pageContext.request.contextPath.concat('/images/no-image.png') 
                                   : item.imagePath}"
                             class="card-img-top"
                             alt="商品画像">
                    </a>

                    <!-- Body -->
                    <div class="card-body">

                        <div class="furima-title mb-1">
                            ${item.title}
                        </div>

                        <div class="furima-price mb-1">
                            ￥${item.price}
                        </div>

                        <div class="furima-time text-muted">
                            ${item.createdAt}
                        </div>

                    </div>

                </div>

            </div>
        </c:forEach>

        <!-- Empty case -->
        <c:if test="${empty furimaList}">
            <div class="col-12 text-center text-muted py-5">
                出品された商品はありません
            </div>
        </c:if>

    </div>

</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<%@ include file="../footer.jsp"%>