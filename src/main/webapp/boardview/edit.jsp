<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../header.jsp"%>

<c:if test="${empty sessionScope.loginUser}">
    <script>
        alert("ログイン後に利用できます。");
        location.href = "${pageContext.request.contextPath}/users/login.do";
    </script>
</c:if>

<div class="board-wrap">

    <h2 class="board-title">投稿修正</h2>

    <form method="post"
          action="${pageContext.request.contextPath}/board/editOk.do"
          enctype="multipart/form-data"
          class="board-write-form">

        <input type="hidden" name="postId" value="${dto.postId}">

        <div class="form-row">
            <label>タイトル<span class="required">*</span></label>
            <input type="text"
                   name="title"
                   maxlength="200"
                   value="${dto.title}"
                   required>
        </div>

        <div class="form-row">
            <label>内容<span class="required">*</span></label>
            <textarea name="content"
                      rows="10"
                      maxlength="4000"
                      required>${dto.content}</textarea>

            <p class="info">※ 修正後は本文に「修正済み」と表示されます。</p>
        </div>

        <div class="form-row">
            <label>画像変更（任意）</label>

            <c:if test="${not empty dto.imagePath}">
                <div class="current-image">
                    <img src="${pageContext.request.contextPath}${dto.imagePath}"
                         alt="current image"
                         style="max-width: 240px; display: block; margin-bottom: 10px;">
                </div>
            </c:if>

            <input type="file" name="image">
        </div>

        <div class="form-actions">
            <button type="submit">修正する</button>
            <a href="${pageContext.request.contextPath}/board/detail.do?postId=${dto.postId}"
               class="cancel-btn">
                キャンセル
            </a>
        </div>

    </form>

</div>

<%@ include file="../footer.jsp"%>