<%@ page import="hu.alkfejl.model.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="hu.alkfejl.model.User" %>
<%@ page import="hu.alkfejl.model.WebappMSG" %>
<%@ page import="hu.alkfejl.dao.MessengeDAOImpl" %>
<%@ page import="hu.alkfejl.dao.MessengDAO" %>
<%@ page import="hu.alkfejl.dao.RoomDAOImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hu.alkfejl.dao.UserDAOImpl" %>
<%@ page import="java.util.Base64" %>
<%--
  Created by IntelliJ IDEA.
  User: berberus
  Date: 2021. 04. 29.
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<!DOCTYPE html>
<html lang="hu">
<head>
    <%
        if (session.getAttribute("status") == null) {
            String redirectURL = "/Chat_webapp_war/login";
            response.sendRedirect(redirectURL);
        }
    %>
    <meta charset="UTF-8">
    <title>ChitChat!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous"/>
    <link rel="stylesheet" href="css/master.css"/>
    <link rel="stylesheet" href="css/formStyle.css"/>
    <link rel="stylesheet" href="css/sidenav.css"/>
    <link rel="stylesheet" href="css/home.css"/>
    <link rel="stylesheet" href="css/msgBoard.css"/>
    <link rel="stylesheet" href="css/search.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<%--sidenav--%>
<div class="sidenav">
    <form action="act" method="post">
        <input type="submit" name="childRoom" id="childRoom-btn" class="btn btn-form btn-choose" value="Csoportok">
        <input type="submit" name="childUser" id="childUser-btn" class="btn btn-form btn-choose" value="Felhasználók">
    </form>
    <div id="logOF-uname">
        <%
            out.println("<h1 id='username'>"+session.getAttribute("username")+"</h1>");
        %>
        <form action="login" method="post">
            <button name="logOF" id="logof-btn" type="submit" class="btn btn-primary btn-form" value="logOF">Kijelentkezés</button>
        </form>
    </div>
</div>
<div id="elements">

    <%
        try {
            if (session.getAttribute("child").equals("rooms") && session.getAttribute("child") != null) {
                out.println(
                                "<form action=\"search\" method=\"post\">" +
                                        "<select name='kategori' id='kategori'>" +
                                "        <option value=\"\" selected>Mind</option>\n" +
                                "        <option value=\"other\">Egyéb</option>\n" +
                                "        <option value=\"gameing\">Játék</option>\n" +
                                "        <option value=\"diy\">DIY</option>\n" +
                                "        <option value=\"movies\">Filmek</option>\n" +
                                "        <option value=\"books\">Könyvek</option>" +
                                "</select>" +
                                        "<input name=\"condition\" placeholder=\"Név...\" style='width: 100%;'>" +
                                        "<button name='search-btn' id='search-btn' type='submit' value='kys' class='fa fa-search'>\n" +
                                        "</button>" +
                                        "</form>" +
                                        "<hr>"
                );
                List<Room> rooms = new ArrayList<>();
                switch (Integer.parseInt(String.valueOf(session.getAttribute("query")))){
                    case 1:
                        rooms = new RoomDAOImpl().findAll();
                        break;
                    case 2:
                        rooms = new RoomDAOImpl().findAllByKategori(String.valueOf(session.getAttribute("condition")));
                        break;
                    case 3:
                        rooms = new RoomDAOImpl().findRoomByName(String.valueOf(session.getAttribute("condition")));
                        break;
                    case 4:
                        rooms = new RoomDAOImpl().findRommByNameAndKategori(String.valueOf(session.getAttribute("condition1")),String.valueOf(session.getAttribute("condition2")));
                        break;
                }
                for (Room room : rooms) {
                    out.println("<form action='msg' method='post'>");
                    out.println("<button name=\"csop\" id=\"regiszt-btn\" type=\"submit\" class=\"btn btn-form btn-choose\" value=\"" + room.getID() + "\">" + room.getName() + "</button>");
                    out.println("<form>");
                }
            } else if (session.getAttribute("child").equals("users") && session.getAttribute("child") != null) {
                out.println(
                        "<form action=\"search\" method=\"post\">" +
                                "<select name='interest' id='kategori'>" +
                                "        <option value=\"\" selected>Mind</option>\n" +
                                "        <option value=\"other\">Egyéb</option>\n" +
                                "        <option value=\"gameing\">Játék</option>\n" +
                                "        <option value=\"diy\">DIY</option>\n" +
                                "        <option value=\"movies\">Filmek</option>\n" +
                                "        <option value=\"books\">Könyvek</option>" +
                                "</select>" +
                                "<select name=\"gender\" id=\"gender\" class=\"form-select\" aria-label=\"Default select example\">\n" +
                                "        <option value=\"\" selected>Bármi</option>\n" +
                                "        <option value=\"male\">Férfi</option>\n" +
                                "        <option value=\"femail\">Nő</option>\n" +
                                "        <option value=\"other\">Apache helikopter</option>\n" +
                                "</select>" +
                                "<input name='age' type='number' id='ageSelector' min=0 style='width: 100%;'>" +
                                "<input name=\"condition\" placeholder=\"Név...\" style='width: 100%;'>" +
                                "<button name='user-search-btn' id='search-btn' type='submit' value='kys' class='fa fa-search'>\n" +
                                "</button>" +
                                "</form>" +
                                "<hr>"
                );
                List<User> users = new ArrayList<>();
                switch (Integer.parseInt(String.valueOf(session.getAttribute("query")))) {
                    case 1:
                        users = new UserDAOImpl().findAll();
                        break;
                    case 2:
                        users = new UserDAOImpl().findAllByInterest(String.valueOf(session.getAttribute("condition1")));
                        break;
                    case 3:
                        users = new UserDAOImpl().findAllByAge(Integer.parseInt(String.valueOf(session.getAttribute("condition1"))));
                        break;
                    case 4:
                        users = new UserDAOImpl().findAllByGender(String.valueOf(session.getAttribute("condition1")));
                        break;
                    case 5:
                        users = new UserDAOImpl().findAllByInterestAndAge(String.valueOf(session.getAttribute("condition1")),Integer.parseInt(String.valueOf(session.getAttribute("condition2"))));
                        break;
                    case 6:
                        users = new UserDAOImpl().findAllByInterestAndGender(String.valueOf(session.getAttribute("condition1")),String.valueOf(session.getAttribute("condition2")));
                        break;
                    case 7:
                        users = new UserDAOImpl().findAllByGenderAndAge(String.valueOf(session.getAttribute("condition1")),Integer.parseInt(String.valueOf(session.getAttribute("condition2"))));
                        break;
                    case 8:
                        users = new UserDAOImpl().findAllByInterestAndGenderAndAge(String.valueOf(session.getAttribute("condition1")),String.valueOf(session.getAttribute("condition2")),Integer.parseInt(String.valueOf(session.getAttribute("condition3"))));
                        break;
                    case 9:
                        users = new UserDAOImpl().findUserByName(String.valueOf(session.getAttribute("condition1")));
                        break;
                }
                for (User user : users) {
                    if (!user.getUsername().equals(String.valueOf(session.getAttribute("username")))) {
                        out.println("<form action='msg' method='post'>");
                        out.println("<button name=\"felh\" id=\"regiszt-btn\" type=\"submit\" class=\"btn btn-form btn-choose\" value=\"" + user.getID() + "\">" + user.getUsername() + "</button>");
                        out.println("<form>");
                    }
                }
            } else {
                System.err.println("Hibatörtént");
            }
        } catch (Exception e) {
            return;
        }
    %>
    </form>
</div>
<%
    if (session.getAttribute("msBID") != null) {
        MessengDAO dao = new MessengeDAOImpl();
        String id = (String) session.getAttribute("msBID");
        System.out.println((session.getAttribute("child").equals("rooms") ? 0 : 1));
        List<WebappMSG> msgs;
        if (session.getAttribute("child").equals("rooms")) {
            msgs = dao.findAllMSGByReceiverAndToWhat(id,1);
        } else {
            msgs = dao.findAllMessengBySenderAndReceiver(Integer.parseInt(id),Integer.parseInt(String.valueOf(session.getAttribute("uID"))),0);
        }
        out.println("<div class=\"msgBoardContainer\">");
        for (WebappMSG msg : msgs) {
            if (msg.getSender().equals(session.getAttribute("username"))){
                if (msg.getMesseng().getType().equals("text")){
                    out.println(
                            "    <div class=\"msgBoard\">" +
                                    "<p> &lt; <label class='mine'>\n" + msg.getSender() + "</label> &gt; : " + msg.getMesseng().getMesseng() +
                                    "</p>" +
                                    "<hr>" +
                                    "</div>\n"
                    );
                } else {
                    String str = Base64.getEncoder().encodeToString(msg.getMesseng().getImg());
                    String url = "data:image/png;base64," + str;
                    out.println(
                            "<div class=\"msgBoard\">" +
                                    "<p> &lt; <label class='mine'>\n" + msg.getSender() + "</label> &gt; : <br> " +
                                        "<img src=\""+url+"\" alt=\"img\" width=\"300\" height=\"300\">" +
                                    "</p>" +
                                    "<hr>" +
                            "</div>\n"
                    );
                }
            } else {
                if (msg.getMesseng().getType().equals("text")){
                    out.println(
                            "<div class=\"msgBoard\">" +
                                    "<p> &lt; <label class='notmine'>\n" + msg.getSender() + "</label> &gt; : " + msg.getMesseng().getMesseng() + "</p>" +
                                    "<hr>" +
                                    "</div>\n"
                    );
                } else {
                    String str = Base64.getEncoder().encodeToString(msg.getMesseng().getImg());
                    String url = "data:image/png;base64," + str;
                    out.println(
                            "<div class=\"msgBoard\">" +
                                    "<p> &lt; <label class='notmine'>\n" + msg.getSender() + "</label> &gt; : <br>" +
                                        "<img src=\""+url+"\" alt=\"img\" width=\"300\" height=\"300\">" +
                                    "</p>" +
                                    "<hr>" +
                            "</div>\n"
                    );
                }
            }
        }
        out.println("</div>");
        out.println(
                "<div class='msgField'>\n" +
                "<form class='msg-sender' action='msg' method='post' enctype=\"multipart/form-data\">" +
                        "<input name='img' type='file' id='file_field' accept=\"image/png, image/jpeg\">"+
                        "<input name='msg' type='text' id='msg-textfield' placeholder=' . . . ' autocomplete=\"off\">" +
                        "<button name='send' id=\"send-btn\" type=\"submit\" class=\"button\" value=\""+(String)session.getAttribute("msBID")+"\">&#10148;</button>" +
                        "</form>" +
                        "</div>"
        );

    }
%>
</body>
<script>
    window.addEventListener( "pageshow", function ( event ) {
        var historyTraversal = event.persisted ||
            ( typeof window.performance != "undefined" &&
                window.performance.navigation.type === 2 );
        if ( historyTraversal ) {
            // Handle page restore.
            window.location.reload();
        }
    });
</script>
</html>
