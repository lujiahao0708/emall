<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
        <%@include file="/WEB-INF/jsp/common/header.jsp"%>
    </head>
    <body>
        <div class="login">

            <!-- Login -->
            <div class="login__block toggled" id="l-login">
                <div class="login__block__header">
                    <i class="zmdi zmdi-account-circle"></i>
                    Hi there! Please Sign in

                    <div class="actions login__block__actions">
                        <div class="dropdown">
                            <a href="" data-toggle="dropdown"><i class="zmdi zmdi-more-vert"></i></a>

                            <ul class="dropdown-menu pull-right">
                                <li><a href="/register">Create an account</a></li>
                                <li><a href="/forgotPassword">Forgot password?</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <form id="formlogin" method="post" onsubmit="return false;">
                    <div class="login__block__body">
                        <div class="form-group form-group--float form-group--centered form-group--centered">
                            <input type="text" class="form-control" id="loginname" name="username" autoComplete="off">
                            <label>Email Address</label>
                            <i class="form-group__bar"></i>
                        </div>

                        <div class="form-group form-group--float form-group--centered form-group--centered">
                            <input type="password" class="form-control" id="nloginpwd" name="password" autoComplete="off" >
                            <label>Password</label>
                            <i class="form-group__bar"></i>
                        </div>

                        <button class="btn btn--light btn--icon m-t-15" id="loginsubmit" >
                            <i class="zmdi zmdi-long-arrow-right"></i>
                        </button>
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
            var redirectUrl = "${redirect}";
            var LOGIN = {
                param:{
                    //单点登录系统的url
                    surl:"http://localhost:8084/"
                },
                checkInput:function() {
                    if ($("#loginname").val() == "") {
                        alert("用户名不能为空");
                        $("#loginname").focus();
                        return false;
                    }
                    if ($("#nloginpwd").val() == "") {
                        alert("密码不能为空");
                        $("#nloginpwd").focus();
                        return false;
                    }
                    return true;
                },
                doLogin:function() {
                    $.post(LOGIN.param.surl + "/user/login", $("#formlogin").serialize(),function(data){
                        if (data.status == 200) {
                            alert("登录成功！");
                            if (redirectUrl == "") {
                                location.href = "http://localhost:8082";
                            } else {
                                location.href = redirectUrl;
                            }
                        } else {
                            alert("登录失败，原因是：" + data.msg);
                            $("#loginname").select();
                        }
                    });
                },
                login:function() {
                    if (this.checkInput()) {
                        this.doLogin();
                    }
                }
            };
            $(function(){
                $("#loginname").select();
                $("#loginsubmit").click(function(){
                    LOGIN.login();
                });
            });
        </script>
    </body>
</html>