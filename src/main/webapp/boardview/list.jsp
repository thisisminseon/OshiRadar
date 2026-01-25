<!-- Board list page -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="推しレーダー :: みんなとする雑談掲示板" />

<%@ include file="../header.jsp"%>

<c:if test="${param.result == 'success'}">
    <script>
        alert("投稿が正常に完了しました！");
    </script>
</c:if>

<!-- Main Visual  -->
<div class="mainvisual nearcafe-visual">
    <div class="visual-inner">
        <div class="visual-title">
            <h2>みんな！こっちで遊ぼう</h2>
            <p>溢れる『推しへの愛』を思う存分語り合って、レアな推し活情報も気兼ねなくシェアできる！</p>
        </div>
    </div>

    <img src="${pageContext.request.contextPath}/images/board_carousel2.png"
         class="nearcafe-visual-img" alt="nearcafe visual">
</div>

<div class="board-wrap">

    <div class="board-header">
        <h2 class="board-title"> </h2>

        <a href="${pageContext.request.contextPath}/board/write.do"
           onclick="return checkLogin(${not empty sessionScope.loginUser});">
            投稿する
        </a>
    </div>

    <table class="board-table">
        <thead>
            <tr>
                <th>番号</th>
                <th>タイトル</th>
                <th>作成者</th>
                <th>閲覧数</th>
                <th>作成日</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty list}">
                    <tr>
                        <td colspan="5">投稿が存在しません。</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="dto" items="${list}">
                        <tr>
                            <td>${dto.postId}</td>
                            <td class="title">
                                <a href="${pageContext.request.contextPath}/board/detail.do?postId=${dto.postId}"
                                   onclick="return checkLogin(${not empty sessionScope.loginUser});">
                                    ${dto.title}
                                </a>
                            </td>
                            <td>${dto.nickname}</td>
                            <td>${dto.viewCount}</td>
                            <td>${dto.createdAt}</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

    <div class="pagination">
        <c:forEach begin="1" end="${totalPage}" var="p">
            <a href="${pageContext.request.contextPath}/board/list.do?page=${p}&type=${param.type}&keyword=${param.keyword}"
               class="${p == page ? 'active' : ''}">
                ${p}
            </a>
        </c:forEach>
    </div>

    <form class="board-search"
          method="get"
          action="${pageContext.request.contextPath}/board/list.do">

        <select name="type">
            <option value="title" ${param.type == 'title' ? 'selected' : ''}>タイトル</option>
            <option value="content" ${param.type == 'content' ? 'selected' : ''}>内容</option>
        </select>

        <input type="text"
               name="keyword"
               placeholder="検索ワードを入力"
               value="${param.keyword}">

        <button type="submit">検索</button>
    </form>

</div>

<script>
function checkLogin(isLogin) {
    if (!isLogin) {
        alert("ログイン後に利用できます。");
        return false;
    }
    return true;
}
</script>

<%@ include file="../footer.jsp"%>