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
<div id="modalDlg" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">MESSAGE</h4>
            </div>
            <div id="modalContent" class="modal-body">
                CLOSED SUCCESSED!
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">CLOSE</button>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row" style="margin-top: 60px;">
        <div class="col-sm-4">
            <div class="list-group">
                <a href="#" class="list-group-item active">
                    USER LIST:
                </a>
                <div id="userList">
                    <a href="#" class="list-group-item">${email}</a>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="row">
                <div class="col-sm-12">
                    <textarea id="content" class="form-control" rows="18" disabled></textarea>
                </div>
            </div>
            <div class="row" style="margin-top: 20px;">
                <div class="col-sm-8">
                    <input type="text" id="message" name="message" class="form-control" placeholder="MESSAGE">
                </div>
                <div class="col-sm-2">
                    <button id="doSend" class="btn btn-warning btn-block" type="button" onclick="doSend();">SEND</button>
                </div>
                <div class="col-sm-2">
                    <button id="doLogOut" class="btn btn-success btn-block" type="button" onclick="doLogOut();">LOGOUT</button>
                </div>
            </div>
        </div>
    </div>
</div> <!-- /container -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/public/vender/bootstrap/js/bootstrap.min.js"></script>
<!--websocket-->
<script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
<script>
    // key binding
    $(function() {
        $("#message").bind("keypress", function(event) {
            if (event.keyCode == "13") {
                doSend();
            }
        });
        $("#doSend").bind("keypress", function(event) {
            if (event.keyCode == "13") {
                doSend();
            }
        });
    });

    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/spring4/socket/chat");
    }
    else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://localhost:8080/spring4/socket/chat");
    }
    else {
        websocket = new SockJS("ws://localhost:8080/spring4/socket/chat");
    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;

    function onOpen(openEvt) {
        console.log(openEvt.data);
    }

    function onMessage(evt) {
        var result = JSON.parse(evt.data);
        console.log(result);
        var userListDom =  $("#userList");
        if (result.on_line_users != null) {
            userListDom.empty();
            $.each(result.on_line_users, function(i, email) {
                var user = $('<a href="#" class="list-group-item"></a>');
                user.html(email);
                userListDom.append(user);
            });
        }

        $("#content").append(result.msg + "\r\n");
    }
    function onError() {
        console.log("session on error!!!");
    }
    function onClose() {
        console.log("session closed!!!");
    }

    function doSend() {
        if (websocket.readyState == websocket.OPEN) {
            var msg = $("#message").val();
            if (msg != "") {
                websocket.send(msg);//调用后台handleTextMessage方法
                $("#message").val("");
            }
        } else {
            alert("连接失败!");
        }
    }

    function doLogOut() {
        if (websocket.readyState == websocket.OPEN) {
            websocket.close();
            messageDlg("DISCONNECT SUCCESSED!");
            $('#modalDlg').on('hidden.bs.modal', function (e) {
                window.location.href = "/spring4";
            })
        }
    }

    function messageDlg (content) {
        $("#modalContent").html(content);
        $("#modalDlg").modal("show");
    }
</script>
</body>
</html>
