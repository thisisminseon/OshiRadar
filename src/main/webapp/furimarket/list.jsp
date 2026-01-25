<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../header.jsp"%>

<c:if test="${param.msg == 'deleted'}">
    <script>
        alert("正常に削除されました。");
    </script>
</c:if>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/furima.css">

<script>
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

    <!-- Header -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <a href="${pageContext.request.contextPath}/furima/write.do"
           class="btn btn-danger"
           onclick="return checkLogin(${not empty sessionScope.loginUser});">
            出品する
        </a>

        <form method="get"
              action="${pageContext.request.contextPath}/furima/list.do"
              class="form-inline">
            <input type="text"
                   name="keyword"
                   class="form-control mr-2"
                   placeholder="商品名で検索"
                   value="${param.keyword}">
            <button type="submit" class="btn btn-outline-danger">検索</button>
        </form>
    </div>

    <!-- Product List -->
    <c:forEach var="dto" items="${list}">
        <div class="furima-item row mb-3">

            <!-- Thumbnail -->
				<div class="col-md-2 text-center">
					<img src="${pageContext.request.contextPath}${dto.imagePath}"
						class="img-fluid furima-thumb" alt="product">
				</div>

				<!-- Content -->
            <div class="col-md-7">
                <h5 class="furima-item-title">
                    <a href="${pageContext.request.contextPath}/furima/detail.do?postId=${dto.postId}">
                        ${dto.title}
                    </a>
                </h5>
                <p class="furima-item-desc">
                    ${dto.description}
                </p>
            </div>

            <!-- Price / Action -->
            <div class="col-md-3 text-right">
                <div class="furima-price">
                    ¥ ${dto.price}
                </div>
                <a href="${pageContext.request.contextPath}/furima/detail.do?postId=${dto.postId}"
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
                       href="${pageContext.request.contextPath}/furima/list.do?page=${p}&keyword=${param.keyword}">
                        ${p}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>

</div>

</body>
</html>

<%@ include file="../footer.jsp"%>