<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- ================= Main Visual ================= -->
<div id="mainVisualCarousel"
     class="carousel slide carousel-fade mainvisual"
     data-ride="carousel"
     data-interval="1900"
     data-pause="false">

    <div class="visual-inner">
        <div class="visual-title">
            <h2>新規会員登録</h2>
            <p>推し活をもっと楽しく、もっと便利に</p>
        </div>
    </div>

    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/images/login_2.jpg"
                 class="d-block w-100" alt="join">
        </div>
    </div>
</div>

<!-- ================= Join Form ================= -->
<div class="sub-container">
    <div class="writebox">

        <form id="myform"
              data-context="${pageContext.request.contextPath}"
              method="post"
              action="${pageContext.request.contextPath}/users/joinOk.do">

            <p class="text-danger mb-4">* は必須項目です。</p>

			<!-- Login ID -->
			<div class="form-group">
				<label>*ログインID</label>

				<div class="form-row">
					<div class="col-md-4 p-0">
						<div class="input-group">
							<input type="text"
							class="form-control"
							id="login_id"
							name="login_id">
							<div class="input-group-append">
								<button type="button" id="btn-id-check"
									class="btn btn-outline-secondary">重複確認</button>
							</div>
						</div>
					</div>
				</div>

				<div class="login-id-msg"></div>
			</div>

			<!-- Password -->
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>*パスワード</label>
                    <input type="password"
                           class="form-control"
                           id="password"
                           name="password">
                    <div class="password-msg"></div>
                </div>
                <div class="form-group col-md-6">
                    <label>*パスワード確認</label>
                    <input type="password"
                           class="form-control"
                           id="password_check"
                           name="password_check">
                    <div class="password-check-msg"></div>
                </div>
            </div>

            <!-- Name / Nickname -->
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>*氏名</label>
                    <input type="text"
                           class="form-control"
                           id="user_name"
                           name="user_name">
                    <div class="user-name-msg"></div>
                </div>
                <div class="form-group col-md-6">
                    <label>*ニックネーム</label>
                    <input type="text"
                           class="form-control"
                           id="nickname"
                           name="nickname">
                    <div class="nickname-msg"></div>
                </div>
            </div>

            <!-- Birth / Gender -->
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label>*生年月日</label>
                    <input type="date"
                           class="form-control"
                           name="birth_date">
                </div>
                <div class="form-group col-md-6">
                    <label>*性別</label>
                    <div class="form-control border-0 p-0">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="radio"
                                   name="gender"
                                   value="M">
                            <label class="form-check-label">男性</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input"
                                   type="radio"
                                   name="gender"
                                   value="F">
                            <label class="form-check-label">女性</label>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Phone -->
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>*電話番号</label>
                    <input type="text"
                           class="form-control"
                           id="phone_1"
                           name="phone_1"
                           placeholder="09012345678">
                    <div class="phone-msg"></div>
                </div>
                <div class="form-group col-md-6">
                    <label>電話番号2</label>
                    <input type="text"
                           class="form-control"
                           name="phone_2">
                </div>
            </div>

<!-- Address -->
<div class="form-row">
    <div class="form-group col-md-3">
        <label>*都道府県</label>
        <select class="form-control" id="prefecture" name="prefecture">
            <option value="">選択してください</option>
            <option>北海道</option>
    <option>青森県</option>
    <option>岩手県</option>
    <option>宮城県</option>
    <option>秋田県</option>
    <option>山形県</option>
    <option>福島県</option>

    <option>茨城県</option>
    <option>栃木県</option>
    <option>群馬県</option>
    <option>埼玉県</option>
    <option>千葉県</option>
    <option>東京都</option>
    <option>神奈川県</option>

    <option>新潟県</option>
    <option>富山県</option>
    <option>石川県</option>
    <option>福井県</option>
    <option>山梨県</option>
    <option>長野県</option>

    <option>岐阜県</option>
    <option>静岡県</option>
    <option>愛知県</option>
    <option>三重県</option>

    <option>滋賀県</option>
    <option>京都府</option>
    <option>大阪府</option>
    <option>兵庫県</option>
    <option>奈良県</option>
    <option>和歌山県</option>

    <option>鳥取県</option>
    <option>島根県</option>
    <option>岡山県</option>
    <option>広島県</option>
    <option>山口県</option>

    <option>徳島県</option>
    <option>香川県</option>
    <option>愛媛県</option>
    <option>高知県</option>

    <option>福岡県</option>
    <option>佐賀県</option>
    <option>長崎県</option>
    <option>熊本県</option>
    <option>大分県</option>
    <option>宮崎県</option>
    <option>鹿児島県</option>
    <option>沖縄県</option>
        </select>
    </div>

    <div class="form-group col-md-8">
        <label>*住所</label>
        <input type="text"
               class="form-control"
               id="address"
               name="address"
               placeholder="例：渋谷区渋谷1-1-1">
        <div class="address-msg"></div>
    </div>
</div>

<div class="form-row">
    <div class="form-group col-md-8">
        <label>住所２（建物名・部屋番号など）</label>
        <input type="text"
               class="form-control"
               id="address2"
               name="address2"
               placeholder="例：○○マンション101号室">
    </div>
</div>

			<!-- Buttons -->
            <div class="text-center mt-5">
                <button type="submit" class="btn btn-red px-5">登録する</button>
                <button type="reset" class="btn btn-secondary px-4 ml-2">リセット</button>
                <button type="button"
                        class="btn btn-dark px-4 ml-2"
                        onclick="location.href='${pageContext.request.contextPath}/'">
                    戻る
                </button>
            </div>

        </form>
    </div>
</div>

<%@ include file="../footer.jsp"%>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.6.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/myProjectJoin.js"></script>