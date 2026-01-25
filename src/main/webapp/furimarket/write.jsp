<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Login check --%>
<c:if test="${empty sessionScope.loginUser}">
    <script>
        alert("ログイン後に利用できます。");
        location.href = "${pageContext.request.contextPath}/users/login.do";
    </script>
</c:if>

<%@ include file="/header.jsp" %>

<!-- ================= Write Form ================= -->
<div class="sub-container">
    <div class="writebox">

        <form method="post"
              action="${pageContext.request.contextPath}/furima/writeOk.do"
              enctype="multipart/form-data">

            <div class="form-group">
                <label>商品名</label>
                <input type="text"
                       name="title"
                       class="form-control"
                       required>
            </div>
            
            <div class="form-group">
                <label>商品説明</label>
                <textarea name="description"
                          class="form-control"
                          rows="5"></textarea>
            </div>

            <div class="form-group">
                <label>価格（円）</label>
                <input type="number"
                       name="price"
                       class="form-control"
                       min="1"
                       required>
            </div>

            <div class="form-group">
                <label>販売状態</label>
                <select name="status" class="form-control">
                    <option value="Y" selected>販売中</option>
                    <option value="R">予約中</option>
                    <option value="S">販売完了</option>
                </select>
            </div>

            <div class="form-group">
                <label>商品画像</label>
                <input type="file"
                       name="image"
                       class="form-control-file">
            </div>

            <div class="text-center mt-4">
                <button type="submit" class="btn btn-danger px-5">
                    出品する
                </button>
                <button type="button"
                        class="btn btn-secondary px-4 ml-2"
                        onclick="location.href='${pageContext.request.contextPath}/furima/list.do'">
                    戻る
                </button>
            </div>
        </form>

    </div>
</div>

<%@ include file="/footer.jsp" %>