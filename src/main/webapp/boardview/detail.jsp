<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../header.jsp"%>

<div class="board-detail-wrap">

    <!-- Post Header -->
    <div class="board-detail-header">
        <h2 class="board-detail-title">${dto.title}</h2>

        <div class="board-detail-meta">
            <span>作成者：${dto.nickname}</span>
            <span>閲覧数：${dto.viewCount}</span>
            <span>作成日：${dto.createdAt}</span>
        </div>
    </div>

    <!-- Post Body -->
    <div class="board-detail-body">

        <c:if test="${not empty dto.imagePath}">
            <div class="board-detail-image">
                <img src="${pageContext.request.contextPath}${dto.imagePath}"
                     alt="post image">
            </div>
        </c:if>

        <div class="board-detail-content">
            ${dto.content}
        </div>
    </div>

    <!-- Action Buttons -->
    <div class="board-detail-actions">
        <a href="${pageContext.request.contextPath}/board/list.do">
            一覧へ
        </a>

        <c:if test="${not empty sessionScope.loginUser}">
            <c:if test="${sessionScope.loginUser.userId == dto.userId}">
                <a href="${pageContext.request.contextPath}/board/edit.do?postId=${dto.postId}">
                    修正
                </a>
                <a href="${pageContext.request.contextPath}/board/delete.do?postId=${dto.postId}"
                   onclick="return confirm('本当に削除しますか？');">
                    削除
                </a>
            </c:if>
        </c:if>
    </div>

    <!-- Comment Section -->
    <div class="board-comment-wrap">

        <h3 class="comment-title">コメント</h3>

        <c:choose>
            <c:when test="${empty commentList}">
                <p class="no-comment">コメントがありません。</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="cmt" items="${commentList}">
                    <div class="comment-item">

                        <div class="comment-meta">
                            <span class="comment-writer">${cmt.nickname}</span>
                            <span class="comment-date">${cmt.createdAt}</span>
                        </div>

                        <div class="comment-body">
                            <c:if test="${not empty cmt.imagePath}">
                                <img src="${pageContext.request.contextPath}${cmt.imagePath}"
                                     alt="comment image">
                            </c:if>
                            <p>${cmt.content}</p>
                        </div>

                        <c:if test="${not empty sessionScope.loginUser}">
                            <c:if test="${sessionScope.loginUser.userId == cmt.userId}">
                                <div class="comment-actions">
                                    <a href="${pageContext.request.contextPath}/board/commentEdit.do?commentId=${cmt.commentId}&postId=${dto.postId}">
                                        修正
                                    </a>
                                    <a href="${pageContext.request.contextPath}/board/commentDelete.do?commentId=${cmt.commentId}&postId=${dto.postId}"
                                       onclick="return confirm('コメントを削除しますか？');">
                                        削除
                                    </a>
                                </div>
                            </c:if>
                        </c:if>

                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty sessionScope.loginUser}">
            <form method="post"
                  action="${pageContext.request.contextPath}/board/commentWrite.do"
                  enctype="multipart/form-data"
                  class="comment-form">

                <input type="hidden" name="postId" value="${dto.postId}">

                <textarea name="content"
                          rows="3"
                          placeholder="コメントを入力してください"
                          required></textarea>

                <input type="file" name="image">

                <button type="submit">コメント投稿</button>
            </form>
        </c:if>

    </div>

</div>

<%@ include file="../footer.jsp"%>