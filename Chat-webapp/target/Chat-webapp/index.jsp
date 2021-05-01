<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ChitChat!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous"/>
    <link rel="stylesheet" href="css/master.css"/>
    <link rel="stylesheet" href="css/formStyle.css"/>
</head>
<body>
<h1 id="chit-chat-title">ChitChat!</h1>
<%
    String redirectURL = "/Chat_webapp_war/login";
    response.sendRedirect(redirectURL);
%>
</div>
</body>
</html>
