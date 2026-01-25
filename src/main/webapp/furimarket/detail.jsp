<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../header.jsp"%>

<div class="furima-detail-wrap">

    <!-- Title -->
    <h2 class="furima-detail-title">
        ${dto.title}
    </h2>

    <hr class="furima-divider">

    <!-- Image Area -->
    <div class="furima-detail-image-wrap">

        <c:if test="${not empty dto.imagePath}">
            <img src="${pageContext.request.contextPath}${dto.imagePath}"
                 alt="product image"
                 class="furima-detail-image">
        </c:if>

        <!-- Status Badge -->
        <c:if test="${dto.status == 'R'}">
            <span class="status-badge reserve">予約中</span>
        </c:if>
        <c:if test="${dto.status == 'S'}">
            <span class="status-badge soldout">販売完了</span>
        </c:if>

    </div>

    <hr class="furima-divider">

    <!-- Price -->
    <div class="furima-detail-price">
        ¥ ${dto.price}
    </div>

    <hr class="furima-divider">

    <!-- Description -->
    <div class="furima-detail-desc">
        <h4>商品説明</h4>
        <p>
            ${dto.description}
        </p>
    </div>

    <hr class="furima-divider">

    <!-- Meta -->
    <div class="furima-detail-meta">
        <span>出品日：${dto.createdAt}</span>
        <span>出品者ID：${dto.userId}</span>
    </div>

    <hr class="furima-divider">

    <!-- Actions -->
    <div class="furima-detail-actions">

        <a href="${pageContext.request.contextPath}/furima/list.do"
           class="btn btn-outline-secondary">
            一覧へ戻る
        </a>

        <c:if test="${not empty sessionScope.loginUser
                     && sessionScope.loginUser.userId == dto.userId}">
            <div class="owner-actions">
                <a href="${pageContext.request.contextPath}/furima/edit.do?postId=${dto.postId}"
                   class="btn btn-outline-primary">
                    編集
                </a>
                <a href="${pageContext.request.contextPath}/furima/delete.do?postId=${dto.postId}"
                   class="btn btn-outline-danger"
                   onclick="return confirm('本当に削除しますか？');">
                    削除
                </a>
            </div>
        </c:if>

    </div>

</div>

<%@ include file="../footer.jsp"%>