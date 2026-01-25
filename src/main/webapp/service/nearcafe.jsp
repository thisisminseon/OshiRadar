<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="推しレーダー :: 近所の開封カフェ" />

<%@ include file="../header.jsp"%>

<!-- ================= Main Visual ================= -->
<div class="mainvisual nearcafe-visual">
    <div class="visual-inner">
        <div class="visual-title">
            <h2>自分の推しを探せ！</h2>
            <p>周辺グッズショップ・開封スポット・フリマまで推し活のすべてを一度に</p>
        </div>
    </div>

    <img src="${pageContext.request.contextPath}/images/cafeCarousel.png"
         class="nearcafe-visual-img"
         alt="nearcafe visual">
</div>

<!-- ================= Cafe List ================= -->
<div class="nearcafe-wrapper">

    <div class="nearcafe-count">
        おすすめスポット：10件
    </div>

    <!-- Cafe 1 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe1.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>カフェ・ミルキーオシ</strong></h3>
            <p>住所：東京都新宿区歌舞伎町1-2-3</p>
            <p>営業時間：11:00〜20:00</p>
            <p>説明：アニメ系グッズの開封が自由にできる落ち着いた空間です。</p>
        </div>
    </div>

    <!-- Cafe 2 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe2.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>推し活喫茶 リボン</strong></h3>
            <p>住所：東京都渋谷区神南2-5-8</p>
            <p>営業時間：10:00〜21:00</p>
            <p>説明：女性ファンに人気の推し活専用カフェです。</p>
        </div>
    </div>

    <!-- Cafe 3 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe3.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>オープンBOXカフェ秋葉原</strong></h3>
            <p>住所：東京都千代田区外神田4-7-1</p>
            <p>営業時間：12:00〜22:00</p>
            <p>説明：カプセルトイ・ランダムグッズの開封歓迎店舗です。</p>
        </div>
    </div>

    <!-- Cafe 4 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe4.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>カフェ・アクスタ日和</strong></h3>
            <p>住所：東京都中野区中野5-60-1</p>
            <p>営業時間：11:30〜19:30</p>
            <p>説明：アクリルスタンド撮影に最適な内装が魅力です。</p>
        </div>
    </div>

    <!-- Cafe 5 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe5.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>推し色カフェ Palette</strong></h3>
            <p>住所：東京都豊島区池袋2-10-5</p>
            <p>営業時間：10:00〜20:00</p>
            <p>説明：推しカラー別のドリンクが楽しめます。</p>
        </div>
    </div>

    <!-- Cafe 6 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe6.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>開封OKカフェ もふもふ</strong></h3>
            <p>住所：東京都台東区浅草1-12-7</p>
            <p>営業時間：09:00〜18:00</p>
            <p>説明：ぬい撮りに特化した開放的な空間です。</p>
        </div>
    </div>

    <!-- Cafe 7 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe7.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>オタロード珈琲</strong></h3>
            <p>住所：東京都世田谷区下北沢3-3-9</p>
            <p>営業時間：12:00〜23:00</p>
            <p>説明：深夜まで営業している推し活歓迎カフェです。</p>
        </div>
    </div>

    <!-- Cafe 8 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe8.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>カフェ・推し棚</strong></h3>
            <p>住所：東京都目黒区自由が丘1-8-2</p>
            <p>営業時間：10:30〜19:00</p>
            <p>説明：推しグッズ展示棚を自由に使えます。</p>
        </div>
    </div>

    <!-- Cafe 9 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe9.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>アニメイトカフェ風</strong></h3>
            <p>住所：東京都文京区本郷4-1-6</p>
            <p>営業時間：11:00〜20:30</p>
            <p>説明：期間限定風の内装が楽しめるカフェです。</p>
        </div>
    </div>

    <!-- Cafe 10 -->
    <div class="nearcafe-item">
        <div class="nearcafe-image">
            <img src="${pageContext.request.contextPath}/images/cafe10.png">
        </div>
        <div class="nearcafe-info">
            <h3><strong>カフェ・ランダム運命</strong></h3>
            <p>住所：東京都品川区大井1-4-3</p>
            <p>営業時間：10:00〜21:00</p>
            <p>説明：ランダムグッズ交換会が頻繁に開催されます。</p>
        </div>
    </div>

</div>

<%@ include file="../footer.jsp"%>