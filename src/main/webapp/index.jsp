<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>


<!-- MAIN VISUAL -->
<div id="mainVisualCarousel"
     class="carousel slide carousel-fade mainvisual"
     data-ride="carousel"
     data-interval="1900"
     data-pause="false">

    <div class="carousel-inner">

        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/images/visual_15.png"
                 class="d-block w-100" alt="visual1">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_12.png"
                 class="d-block w-100" alt="visual2">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_10.png"
                 class="d-block w-100" alt="visual3">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_11.png"
                 class="d-block w-100" alt="visual4">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_13.png"
                 class="d-block w-100" alt="visual5">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_14.png"
                 class="d-block w-100" alt="visual6">
        </div>

    </div>

    <!-- Fixed Text Overlay -->
    <div class="visual-inner">
        <div class="visual-title">
            <h2>自分の推しを探せ！</h2>
            <p>
                周辺グッズショップ・開封スポット・フリマまで推し活のすべてを一度に
            </p>
        </div>
    </div>

</div>

<!-- ================= MAIN CONTENT ================= -->
<div class="box-table">

    <div class="box">
        <a href="${pageContext.request.contextPath}/service/nearshop.jsp"
           style="text-decoration: none; color: inherit; display: block;">

            <div class="over">
                <img src="${pageContext.request.contextPath}/images/wotaku.png"
                     alt="goods-shop">
            </div>
            <h3 class="porttitle">近所のグッズショップ</h3>
            <p class="txt">穴場のグッズスポット探しに行こう！</p>

        </a>
    </div>

   <div class="box">
    <a href="${pageContext.request.contextPath}/service/nearcafe.jsp"
       style="text-decoration: none; color: inherit; display: block;">

        <div class="over">
            <img src="${pageContext.request.contextPath}/images/unboxing.png"
                 alt="unboxing-zone">
        </div>
        <h3 class="porttitle">近所の開封スポット</h3>
        <p class="txt">
            開けるなら一緒が最高！<br>
            ランダムグッズ開封
        </p>

    </a>
</div>

<div class="box">
    <a href="${pageContext.request.contextPath}/furima/list.jsp"
       style="text-decoration: none; color: inherit; display: block;">

        <div class="over">
            <img src="${pageContext.request.contextPath}/images/adopt.png"
                 alt="goods-trade">
        </div>
        <h3 class="porttitle">フリマ</h3>
        <p class="txt">推しの卒業は、新たなご縁の始まり</p>

    </a>
</div>

    <div class="box"> 
        <div class="over">
            <img src="${pageContext.request.contextPath}/images/information.png"
                 alt="information-board">
        </div>
        <h3 class="porttitle">情報共有</h3>
        <p class="txt">推し活は情報戦！</p>
    </div>

</div>

<%@ include file="footer.jsp" %>