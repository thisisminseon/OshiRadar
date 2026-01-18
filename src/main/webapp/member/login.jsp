<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../header.jsp"%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン | 推しレーダー</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/myStyle.css">
</head>

<body>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-6">

            <h2 class="text-center mb-5">ログイン</h2>

            <!-- Login error message from Controller -->
            <c:if test="${not empty loginError}">
                <div class="alert alert-danger text-center">
                    ${loginError}
                </div>
            </c:if>

            <!-- LOGIN FORM -->
            <form id="loginForm"
                  method="post"
                  action="${pageContext.request.contextPath}/users/login.do">

                <table class="tableBorderless login-table mx-auto">
                    <tbody>
                        <tr>
                            <th class="login-label">ログインID</th>
                            <td class="login-input">
                                <input type="text"
                                       class="form-control"
                                       id="login_id"
                                       name="login_id"
                                       required>
                            </td>
                        </tr>

                        <tr>
                            <th class="login-label">パスワード</th>
                            <td class="login-input">
                                <input type="password"
                                       class="form-control"
                                       id="password"
                                       name="password"
                                       required>
                            </td>
                        </tr>

                        <tr>
                            <th></th>
                            <td class="login-checkbox">
                                <label>
                                    <input type="checkbox" id="rememberId">
                                    IDを記憶する
                                </label>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div class="text-center mt-5">
                    <button type="submit" class="btn btn-red px-4">
                        ログイン
                    </button>

                    <button type="button"
                            class="btn btn-outline-secondary px-4 ml-3"
                            onclick="location.href='${pageContext.request.contextPath}/users/helpLogin.do'">
                        ログインできない方はこちら
                    </button>
                </div>

            </form>

        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script>
$(function () {

    var savedId = localStorage.getItem("lastLoginId");
    var remember = localStorage.getItem("rememberLoginId");

    if (remember === "true" && savedId) {
        $("#login_id").val(savedId);
        $("#rememberId").prop("checked", true);
    }

    $("#loginForm").on("submit", function () {

        var loginId = $("#login_id").val().trim();

        if ($("#rememberId").is(":checked")) {
            localStorage.setItem("lastLoginId", loginId);
            localStorage.setItem("rememberLoginId", "true");
        } else {
            localStorage.removeItem("lastLoginId");
            localStorage.removeItem("rememberLoginId");
        }

        return true;
    });
});
</script>

<%@ include file="../footer.jsp"%>

</body>
</html>