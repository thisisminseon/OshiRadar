<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    List<Map<String, Object>> list = new ArrayList<>();

    String[][] data = {
        {"1","SPY×FAMILY ラバーストラップ","完全新品です。神経質な方はご購入をご遠慮ください","1500","fake1.jpg"},
        {"2","呪術廻戦 ガチャセット","被ったため出品します","1200","fake2.jpg"},
        {"3","SAKAMOTO DAYS 南雲 アクリルスタンド","未使用・袋あり","800","fake3.jpg"},
        {"4","推しの子 アクリルスタンド","在庫整理のため出品しますー！新品未開封品です","2000","fake4.jpg"},
        {"5","東京リベンジャーズ フィギュア","箱なし・現状品","3000","fake5.jpg"},
        {"6","SAKAMOTO DAYS 朝倉シン フィギュア","未開封","900","fake6.jpg"},
        {"7","チェンソーマン マキマ フィギュア","未使用・暗所保管","2000","fake7.jpg"},
        {"8","らぶしっく ゆそん アクリルスタンド","目立つ傷なし","1100","fake8.jpg"},
        {"9","韓国限定 サカモトデイズ SAKAMOTODAYS 21巻","苦労して韓国でゲットしました","12000","fake9.jpg"},
        {"10","銀魂 銀時 フィギュア","開封しました","1300","fake10.jpg"},
        {"11","逃げ上手の若君 北条時行 フィギュア","未使用・箱あり","2500","fake11.jpg"},
        {"12","着せ恋 アクリルスタンド","被りのため出品","500","fake12.jpg"}
    };

    for (String[] d : data) {
        Map<String, Object> m = new HashMap<>();
        m.put("fno", d[0]);
        m.put("title", d[1]);
        m.put("content", d[2]);
        m.put("price", d[3]);
        m.put("imgfile", d[4]);
        list.add(m);
    }

    request.setAttribute("list", list);
    request.setAttribute("totalCount", list.size());
%>

<%@ include file="../header.jsp"%>

<div id="furimaVisualCarousel"
     class="carousel slide carousel-fade furima-visual"
     data-ride="carousel"
     data-interval="2200"
     data-pause="false">

    <div class="carousel-inner">

        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/images/visual_15.png"
                 class="d-block w-100" alt="furima-visual1">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_12.png"
                 class="d-block w-100" alt="furima-visual2">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_10.png"
                 class="d-block w-100" alt="furima-visual3">
        </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/images/visual_11.png"
                 class="d-block w-100" alt="furima-visual4">
        </div>

    </div>

    <!-- Fixed Text Overlay -->
    <div class="furima-visual-inner">
        <div class="furima-visual-title">
            <h2>フリマ</h2>
            <p>
                推しの卒業も、次の出会いもここから始まる
            </p>
        </div>
    </div>

</div>

<div class="furima-container">

    <div class="furima-search-group">
        <div class="furima-count">
            <span>総商品数：${totalCount}</span>
        </div>
    </div>

    <c:forEach var="item" items="${list}">
        <div class="furima-item">

            <div class="furima-image">
                <img src="${pageContext.request.contextPath}/images/${item.imgfile}"
                     alt="商品画像">
            </div>

            <div class="furima-text">
                <div class="furima-meta">
                    <span>No.${item.fno}</span> |
                    <span>${item.price}円</span>
                </div>

                <h3 class="furima-title">${item.title}</h3>

                <div class="furima-desc">
                    <div style="height:72px; overflow:hidden;">
                        ${item.content}
                    </div>
                </div>
            </div>

        </div>
    </c:forEach>

</div>

<div class="furima-page">
    <div class="furima-writer">
        <a href="#">商品を出品する</a>
    </div>
</div>

<%@ include file="../footer.jsp"%>