<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/myStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nearshop.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nearcafe.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/furima.css">


<header class="site-header">

    <!-- Top Area -->
    <div class="site-top">

        <!-- Logo -->
        <div class="site-logo">
            <a href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/images/title.png"
                     alt="logo"
                     class="logo-image">
            </a>
        </div>

        <!-- User Area -->
        <div class="site-user">
            <c:choose>
                <c:when test="${not empty sessionScope.loginUser}">
                    <span class="user-welcome">
                        ${sessionScope.loginUser.userName}様、ようこそ
                    </span>
                    <span class="user-separator">|</span>
                    <a href="${pageContext.request.contextPath}/users/logout.do"
                       class="logout-link">
                        ログアウト
                    </a>
                </c:when>

                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/users/login.do">
                        ログイン
                    </a>
                    <a href="${pageContext.request.contextPath}/users/join.do">
                        新規入会
                    </a>
                </c:otherwise>
            </c:choose>
        </div>

    </div>

    <!-- Search Area -->
    <div class="site-search">
        <form class="form-inline justify-content-center"
              method="get"
              action="${pageContext.request.contextPath}/search.do">
            <div class="input-group search-dark flex-nowrap">
                <input type="search"
                       id="searchPlaceholder"
                       name="keyword"
                       class="form-control search-input rounded-left-pill"
                       placeholder="">
                <div class="input-group-append">
                    <button class="btn btn-dark search-btn rounded-right-pill"
                            type="submit">
                        検索
                    </button>
                </div>
            </div>
        </form>
    </div>

    <!-- Navigation -->
    <nav class="site-nav">
        <ul class="nav-menu">
            <li><a href="${pageContext.request.contextPath}/">ホーム</a></li>
            <li><a href="${pageContext.request.contextPath}/service/nearshop.jsp">近所のグッズショップ</a></li>
            <li><a href="${pageContext.request.contextPath}/service/nearcafe.jsp">近所の開封スポット</a></li>
            <li><a href="#">フリマ</a></li>
            <li><a href="#">雑談掲示板</a></li>
            <li><a href="#">情報共有</a></li>
        </ul>
    </nav>

</header>

<!-- Placeholder Script -->
<script>
(function () {
    var messages = [
        "あなたの推しを探しに行きませんか？",
        "今回は無駄足にならないようにしましょう。",
        "私の推しは今どこにいるんだろう？",
        "友だちとランダム開封するなら、どこがいい？",
        "こんな近くに知らなかったグッズショップが！？"
    ];

    var input = document.getElementById("searchPlaceholder");
    if (input) {
        var randomIndex = Math.floor(Math.random() * messages.length);
        input.placeholder = messages[randomIndex];
    }
})();
</script>