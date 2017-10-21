<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
        <%@include file="/WEB-INF/jsp/common/header.jsp"%>
    </head>
    <body>
        <div class="login">

            <!-- Register -->
            <div class="login__block toggled" id="l-register">
                <div class="login__block__header palette-Blue bg">
                    <i class="zmdi zmdi-account-circle"></i>
                    Create an account

                    <div class="actions login__block__actions">
                        <div class="dropdown">
                            <a href="" data-toggle="dropdown"><i class="zmdi zmdi-more-vert"></i></a>

                            <ul class="dropdown-menu pull-right">
                                <li><a href="/login">Already have an account?</a></li>
                                <li><a href="/forgotPassword">Forgot password?</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <form id="personRegForm" method="post" onsubmit="return false;">
                    <div class="login__block__body">
                        <div class="form-group form-group--float form-group--centered">
                            <input type="text" class="form-control" id="regName" name="username" autoComplete="off">
                            <label>Name</label>
                            <i class="form-group__bar"></i>
                        </div>

                        <div class="form-group form-group--float form-group--centered">
                            <input type="text" class="form-control" id="regPhone" name="phone" autoComplete="off">
                            <label>Phone</label>
                            <i class="form-group__bar"></i>
                        </div>

                        <div class="form-group form-group--float form-group--centered">
                            <input type="text" class="form-control" id="regEmail" name="email" autoComplete="off">
                            <label>Email Address</label>
                            <i class="form-group__bar"></i>
                        </div>

                        <div class="form-group form-group--float form-group--centered">
                            <input type="password" class="form-control" id="pwd" name="password" autoComplete="off">
                            <label>Password</label>
                            <i class="form-group__bar"></i>
                        </div>

                        <div class="form-group form-group--float form-group--centered">
                            <input type="password" class="form-control" id="pwdRepeat" name="pwdRepeat" autoComplete="off">
                            <label>Password Repeat</label>
                            <i class="form-group__bar"></i>
                            <label id="pwdRepeat_succeed" class="blank"></label>
                            <label id="pwdRepeat_error"></label>
                        </div>

                        <div class="form-group form-group--float form-group--centered">
                            <input type="text" class="form-control" id="regQuestion" name="question" autoComplete="off">
                            <label>Question</label>
                            <i class="form-group__bar"></i>
                        </div>
                        <div class="form-group form-group--float form-group--centered">
                            <input type="text" class="form-control" id="regAnswer" name="answer" autoComplete="off">
                            <label>Answer</label>
                            <i class="form-group__bar"></i>
                        </div>

                        <div class="input-centered">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" checked="checked" id="readme" onclick="agreeonProtocol();">
                                    <i class="input-helper"></i>
                                    Accept the license agreement
                                </label>
                            </div>
                        </div>
                        <!--注册按钮-->
                        <button class="btn btn--light btn--icon m-t-15" id="registsubmit" onclick="REGISTER.reg();"><i class="zmdi zmdi-plus"></i></button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Older IE Warning -->
        <!--[if lt IE 9]>
            <div class="ie-warning">
                <h1>Warning!!</h1>
                <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers to access this website.</p>
                <div class="ie-warning__container">
                    <ul class="ie-warning__download">
                        <li>
                            <a href="http://www.google.com/chrome/">
                                <img src="img/browsers/chrome.png" alt="">
                                <div>Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.mozilla.org/en-US/firefox/new/">
                                <img src="img/browsers/firefox.png" alt="">
                                <div>Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.opera.com">
                                <img src="img/browsers/opera.png" alt="">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="https://www.apple.com/safari/">
                                <img src="img/browsers/safari.png" alt="">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                                <img src="img/browsers/ie.png" alt="">
                                <div>IE (New)</div>
                            </a>
                        </li>
                    </ul>
                </div>
                <p>Sorry for the inconvenience!</p>
            </div>
        <![endif]-->

        <%@include file="/WEB-INF/jsp/common/footer.jsp"%>

        <!-- Javascript Libraries -->

        <!-- jQuery -->
        <script src="${ctx}js/jquery.min.js"></script>

        <!-- Bootstrap -->
        <script src="${ctx}js/bootstrap.min.js"></script>

        <!-- Placeholder for IE9 -->
        <!--[if IE 9 ]>
            <script src="${ctx}js/jquery.placeholder.min.js"></script>
        <![endif]-->

        <!-- Site Functions & Actions -->
        <script src="${ctx}js/app.min.js"></script>

        <script type="text/javascript">
            var REGISTER={
                param:{
                    //单点登录系统的url
                    surl:"http://localhost:8084/"
                },
                inputcheck:function(){
                    //不能为空检查
                    if ($("#regName").val() == "") {
                        alert("用户名不能为空");
                        $("#regName").focus();
                        return false;
                    }if ($("#regPhone").val() == "") {
                        alert("手机号不能为空");
                        $("#regPhone").focus();
                        return false;
                    }if ($("#regEmail").val() == "") {
                        alert("邮箱不能为空");
                        $("#regEmail").focus();
                        return false;
                    }if ($("#pwd").val() == "") {
                        alert("密码不能为空");
                        $("#pwd").focus();
                        return false;
                    }if ($("#pwd").val() != $("#pwdRepeat").val()) {
                        alert("确认密码和密码不一致，请重新输入！");
                        $("#pwdRepeat").select();
                        $("#pwdRepeat").focus();
                        return false;
                    }if ($("#regQuestion").val() == "") {
                        alert("密保问题不能为空");
                        $("#regQuestion").focus();
                        return false;
                    }if ($("#regAnswer").val() == "") {
                        alert("密保答案不能为空");
                        $("#regAnswer").focus();
                        return false;
                    }
                    return true;
                },
                doSubmit:function() {
                    $.post(REGISTER.param.surl + "/user/register",$("#personRegForm").serialize(), function(data){
                        if(data.status == 1){
                            alert('用户注册成功，请登录！');
                            REGISTER.login();
                        } else {
                            alert(data.msg);
                        }
                    });
                },
                login:function() {
                    location.href = "/login";
                    return false;
                },
                reg:function() {
                    if (this.inputcheck()) {
                        //this.beforeSubmit();
                        this.doSubmit();
                    }
                }
            };
        </script>
    </body>
</html>