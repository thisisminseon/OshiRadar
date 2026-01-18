<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../header.jsp"%>

<!-- ================= Main Visual Carousel ================= -->
<div id="mainVisualCarousel"
     class="carousel slide carousel-fade mainvisual"
     data-ride="carousel"
     data-interval="1900"
     data-pause="false">

    <!-- Overlay text inside carousel -->
    <div class="visual-inner">
        <div class="visual-title">
            <h2>近所のグッズショップ</h2>
            <p>まだ知らなかった“推しの聖地”、すぐそばにあるかもしれない！</p>
        </div>
    </div>

    <!-- Carousel images (ONLY ONE active item) -->
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
            <img src="${pageContext.request.contextPath}/images/visual_13.png"
                 class="d-block w-100" alt="visual4">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_14.png"
                 class="d-block w-100" alt="visual5">
        </div>

    </div>
</div>

<!-- ================= Main Content ================= -->
<div class="sub-container">
    <div class="writebox">

        <!-- Category carousel -->
        <div class="carousel-wrap">
            <div class="box-table">

                <div class="box">
                    <a href="${pageContext.request.contextPath}/nearshop/list.do?category=title">
                        <div class="over">
                            <img src="${pageContext.request.contextPath}/images/searchBytitle.png" alt="">
                        </div>
                        <h3 class="porttitle">作品で探す</h3>
                        <p class="txt">作品・ジャンル別に探したい方へ</p>
                    </a>
                </div>

                <div class="box">
                    <a href="${pageContext.request.contextPath}/nearshop/list.do?category=trend">
                        <div class="over">
                            <img src="${pageContext.request.contextPath}/images/searchByWadai.png" alt="">
                        </div>
                        <h3 class="porttitle">最近話題</h3>
                        <p class="txt">SNSや口コミで注目のショップ</p>
                    </a>
                </div>

                <div class="box">
                    <a href="${pageContext.request.contextPath}/nearshop/list.do?category=figure">
                        <div class="over">
                            <img src="${pageContext.request.contextPath}/images/searchByFigure.png" alt="">
                        </div>
                        <h3 class="porttitle">フィギュア</h3>
                        <p class="txt">スケール・可動フィギュアが充実</p>
                    </a>
                </div>

                <div class="box">
                    <a href="${pageContext.request.contextPath}/nearshop/list.do?category=female">
                        <div class="over">
                            <img src="${pageContext.request.contextPath}/images/forFemale.png" alt="">
                        </div>
                        <h3 class="porttitle">女性向け</h3>
                        <p class="txt">アクスタ・可愛い系グッズ中心</p>
                    </a>
                </div>

                <div class="box">
                    <a href="${pageContext.request.contextPath}/nearshop/list.do?category=male">
                        <div class="over">
                            <img src="${pageContext.request.contextPath}/images/forMale.png" alt="">
                        </div>
                        <h3 class="porttitle">男性向け</h3>
                        <p class="txt">王道バトル・ジャンプ系作品</p>
                    </a>
                </div>

                <div class="box">
                    <a href="${pageContext.request.contextPath}/nearshop/list.do?category=etc">
                        <div class="over">
                            <img src="${pageContext.request.contextPath}/images/etc.png" alt="">
                        </div>
                        <h3 class="porttitle">その他</h3>
                        <p class="txt">個性派・隠れ家的ショップ</p>
                    </a>
                </div>

            </div>
        </div>

        <!-- Fixed category menu -->
        <div class="category-menu">
            <a href="${pageContext.request.contextPath}/nearshop/list.do?category=title">作品別</a>
            <a href="${pageContext.request.contextPath}/nearshop/list.do?category=trend">話題</a>
            <a href="${pageContext.request.contextPath}/nearshop/list.do?category=figure">フィギュア</a>
            <a href="${pageContext.request.contextPath}/nearshop/list.do?category=female">女性向け</a>
            <a href="${pageContext.request.contextPath}/nearshop/list.do?category=male">男性向け</a>
            <a href="${pageContext.request.contextPath}/nearshop/list.do?category=etc">その他</a>
        </div>

    </div>
</div>

<script>
/* Pause category conveyor animation on hover */
const conveyor = document.querySelector('.carousel-wrap .box-table');

if (conveyor) {
    conveyor.addEventListener('mouseenter', () => {
        conveyor.style.animationPlayState = 'paused';
    });

    conveyor.addEventListener('mouseleave', () => {
        conveyor.style.animationPlayState = 'running';
    });
}
</script>

<%@ include file="../footer.jsp"%>