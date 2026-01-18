<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>

<div class="subvisual">
    <div class="sub-inner">
        <div class="visual-title">
            <h2>近所の開封カフェ</h2>
            <p>
                今いる場所の周辺でランダムグッズ開封にちょうどいいカフェ
            </p>
        </div>
    </div>
</div>

<div class="sub-container">

    <!-- Display total number of recommended spots -->
    <div class="search-group">
        <div class="count">
            <span>おすすめスポット：${totalCount}件</span>
        </div>
    </div>

    <!-- Location-based cafe list -->
    <c:forEach var="cafe" items="${list}">
        <div class="search-content">

            <!-- Cafe image -->
            <div class="search-image">
                <img src="${pageContext.request.contextPath}/images/${cafe.image}"
                     alt="Cafe image">
            </div>

            <!-- Cafe information -->
            <div class="search-text">

                <div>
                    <span>${cafe.name}</span> |
                    <span>${cafe.distance} km</span>
                </div>

                <div>
                    <h3>${cafe.name}</h3>
                </div>

                <div class="txt">
                    <div style="height:72px; overflow:hidden;">
                        ${cafe.description}
                    </div>

                    <p>
                        <a href="${pageContext.request.contextPath}/nearcafe/view.do?id=${cafe.id}"
                           class="more">
                            詳細を見る
                        </a>
                    </p>
                </div>

            </div>
        </div>
    </c:forEach>

    <!-- Message shown when no results are available -->
    <c:if test="${empty list}">
        <div style="text-align:center; padding:60px 0;">
            <p>周辺におすすめできる開封スポットが見つかりませんでした。</p>
        </div>
    </c:if>

</div>

<%@ include file="../footer.jsp"%>