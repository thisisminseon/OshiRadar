<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../header.jsp"%>

<c:if test="${param.result == 'fail'}">
    <script>
        alert("投稿に失敗しました。もう一度お試しください。");
    </script>
</c:if>

<c:if test="${empty sessionScope.loginUser}">
    <script>
        alert("ログイン後に利用できます。");
        location.href = "${pageContext.request.contextPath}/users/login.do";
    </script>
</c:if>

<div class="board-wrap">

    <h2 class="board-title">投稿作成</h2>

    <form method="post"
          action="${pageContext.request.contextPath}/board/writeOk.do"
          enctype="multipart/form-data"
          class="board-write-form">

        <div class="form-row">
            <label>タイトル<span class="required">*</span></label>
            <input type="text"
                   name="title"
                   maxlength="200"
                   required>
        </div>

        <div class="form-row">
            <label>内容<span class="required">*</span></label>
            <textarea name="content"
                      rows="10"
                      maxlength="4000"
                      required></textarea>
        </div>

        <div class="form-row">
            <label>画像添付（任意）</label>
            <input type="file"
                   name="image">
        </div>

        <div class="form-actions">
            <button type="submit">投稿する</button>
            <button type="reset">再入力</button>
            <a href="${pageContext.request.contextPath}/board/list.do"
               class="cancel-btn">
                キャンセル
            </a>
        </div>

    </form>

</div>

<%@ include file="../footer.jsp"%>