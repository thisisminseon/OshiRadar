<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../header.jsp" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>FURIMARKET | LIST</title>

    <!-- Bootstrap 4.6 -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Furima CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/furima.css">

    <script>
        // Guest write button alert
        function checkLogin(isLogin) {
            if (!isLogin) {
                alert("ログイン後に利用できます。");
                return false;
            }
            return true;
        }
    </script>
</head>

<body>

<div class="container furima-list-wrap">

    <!-- List Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3 class="furima-title">商品一覧</h3>

        <a href="${pageContext.request.contextPath}/furimarket/write.do"
           class="btn btn-danger"
           onclick="return checkLogin(${not empty sessionScope.loginUser});">
            出品する
        </a>
    </div>

    <!-- Product List -->
    <c:forEach var="dto" items="${list}">
        <div class="furima-item row">

            <!-- Thumbnail -->
            <div class="col-md-2 text-center">
                <img src="${dto.imagePath}"
                     class="img-fluid furima-thumb"
                     alt="product">
            </div>

            <!-- Content -->
            <div class="col-md-7">
                <h5 class="furima-item-title">
                    <a href="${pageContext.request.contextPath}/furimarket/detail.do?id=${dto.id}">
                        ${dto.title}
                    </a>
                </h5>
                <p class="furima-item-desc">
                    ${dto.content}
                </p>
            </div>

            <!-- Price / Action -->
            <div class="col-md-3 text-right">
                <div class="furima-price">
                    ¥ ${dto.price}
                </div>
                <a href="${pageContext.request.contextPath}/furimarket/detail.do?id=${dto.id}"
                   class="btn btn-outline-danger btn-sm mt-2">
                    もっと見る
                </a>
            </div>

        </div>
    </c:forEach>

    <!-- Pagination -->
    <nav class="furima-pagination">
        <ul class="pagination justify-content-center">
            <c:forEach begin="1" end="${totalPage}" var="p">
                <li class="page-item ${p == page ? 'active' : ''}">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/furimarket/list.do?page=${p}">
                        ${p}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>

</div>

</body>
</html>

<%@ include file="../footer.jsp" %>