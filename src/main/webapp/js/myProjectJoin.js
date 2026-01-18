console.log("myProjectJoin.js loaded");

$(document).ready(function () {

    var $form = $("#myform");
    if ($form.length === 0) {
        console.error("myform not found");
        return;
    }

    var contextPath = $form.data("context");

    function showMsg(selector, msg) {
        $(selector)
            .removeClass("text-primary")
            .addClass("text-danger")
            .text(msg);
    }

    function showSuccess(selector, msg) {
        $(selector)
            .removeClass("text-danger")
            .addClass("text-primary")
            .text(msg);
    }

    function clearMsg(selector) {
        $(selector).text("").removeClass("text-danger text-primary");
    }

    /* =========================
       LOGIN ID
    ========================= */
    $("#login_id").on("blur", function () {
        clearMsg(".login-id-msg");

        if (!$(this).val().trim()) {
            showMsg(".login-id-msg", "ログインIDを入力してください。");
        }
    });

    $("#btn-id-check").on("click", function () {

        var loginId = $("#login_id").val().trim();
        clearMsg(".login-id-msg");

        if (!loginId) {
            showMsg(".login-id-msg", "ログインIDを入力してください。");
            return;
        }

        $.ajax({
            url: contextPath + "/users/idCheck.do",
            type: "post",
            data: { login_id: loginId },
            success: function (res) {
                if ($.trim(res) === "EXISTS") {
                    showMsg(".login-id-msg", "既に存在するIDです。");
                } else {
                    showSuccess(".login-id-msg", "使用可能なIDです。");
                }
            },
            error: function () {
                showMsg(".login-id-msg", "ID確認中にエラーが発生しました。");
            }
        });
    });

    /* =========================
       PASSWORD
    ========================= */
    $("#password").on("blur", function () {
        clearMsg(".password-msg");

        if ($(this).val().length < 6) {
            showMsg(".password-msg", "パスワードは6文字以上必要です。");
        }
    });

    $("#password_check").on("blur", function () {
        clearMsg(".password-check-msg");

        if ($("#password").val() !== $(this).val()) {
            showMsg(".password-check-msg", "パスワードが一致しません。");
        }
    });

    /* =========================
       USER NAME
    ========================= */
    $("#user_name").on("blur", function () {
        clearMsg(".user-name-msg");

        if (!$(this).val().trim()) {
            showMsg(".user-name-msg", "氏名を入力してください。");
        }
    });

    /* =========================
       NICKNAME
    ========================= */
    $("#nickname").on("blur", function () {
        clearMsg(".nickname-msg");

        if (!$(this).val().trim()) {
            showMsg(".nickname-msg", "ニックネームを入力してください。");
        }
    });

    /* =========================
       PHONE
    ========================= */
    $("#phone_1").on("blur", function () {
        clearMsg(".phone-msg");

        var val = $(this).val().trim();
        if (!val) {
            showMsg(".phone-msg", "電話番号を入力してください。");
            return;
        }

        if (!/^[0-9]+$/.test(val)) {
            showMsg(".phone-msg", "電話番号は数字のみ入力してください。");
        }
    });

    /* =========================
       ADDRESS
    ========================= */
    $("#address").on("blur", function () {
        clearMsg(".address-msg");

        if (!$(this).val().trim()) {
            showMsg(".address-msg", "住所を入力してください。");
        }
    });

    /* =========================
       FINAL SUBMIT
    ========================= */
    $form.on("submit", function () {

        var isValid = true;

        clearMsg(".login-id-msg");
        clearMsg(".password-msg");
        clearMsg(".password-check-msg");
        clearMsg(".user-name-msg");
        clearMsg(".nickname-msg");
        clearMsg(".phone-msg");
        clearMsg(".address-msg");

        if (!$("#login_id").val().trim()) {
            showMsg(".login-id-msg", "ログインIDを入力してください。");
            isValid = false;
        }

        var pw = $("#password").val();
        var pw2 = $("#password_check").val();

        if (!pw || pw.length < 6) {
            showMsg(".password-msg", "パスワードは6文字以上必要です。");
            isValid = false;
        }

        if (!pw2 || pw !== pw2) {
            showMsg(".password-check-msg", "パスワードが一致しません。");
            isValid = false;
        }

        if (!$("#user_name").val().trim()) {
            showMsg(".user-name-msg", "氏名を入力してください。");
            isValid = false;
        }

        if (!$("#nickname").val().trim()) {
            showMsg(".nickname-msg", "ニックネームを入力してください。");
            isValid = false;
        }

        var phone = $("#phone_1").val().trim();
        if (!phone || !/^[0-9]+$/.test(phone)) {
            showMsg(".phone-msg", "電話番号は数字のみ入力してください。");
            isValid = false;
        }

        if (!$("#address").val().trim()) {
            showMsg(".address-msg", "住所を入力してください。");
            isValid = false;
        }

        return isValid;
    });

});