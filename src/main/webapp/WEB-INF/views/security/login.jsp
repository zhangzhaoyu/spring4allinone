<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>ANICLOUD CHATING ROOM</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/public/vender/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/vender/bootstrap/css/bootstrap-theme.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 30px; margin-bottom: 40px;">
        <div class="col-sm-offset-3 col-sm-6">
            <h1>ANICLOUD CHATING ROOM</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-3 col-sm-6">
            <c:url value="/login" var="loginUrl"/>
            <form action="${loginUrl}" method="post">
                <c:if test="${param.error != null}">
                    <p>
                        Invalid username and password.
                    </p>
                </c:if>
                <c:if test="${param.logout != null}">
                    <p>
                        You have been logged out.
                    </p>
                </c:if>
                <p>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username"/>
                </p>
                <p>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password"/>
                </p>
                <input type="hidden" name="${_csrf.parameterName}"
                value="${_csrf.token}"/>
                <button type="submit" class="btn">Log in</button>
            </form>
        </div>
    </div>
</div> <!-- /container -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/public/vender/bootstrap/js/bootstrap.min.js"></script>
<!--websocket-->
<script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
</body>
</html>
