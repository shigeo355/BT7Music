
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>PF02 Download</title>
    <link rel="stylesheet" href="<c:url value='/styles/main.css'/>" type="text/css"/>
  </head>
  <body>
    <h1>Download</h1>
    <h2>JACKJ97 Vuc Tham Binh Yen</h2>

    <%-- Tạo URL an toàn (có context path) cho các file MP3 --%>
    <c:url var="mp3_1" value="/musicStore/sound/pf02/JACKJ97VucThamBinhYen.mp3"/>
    <c:url var="mp3_2" value="/musicStore/sound/pf02/JACKJ97VucThamBinhYen.mp3"/>

    <table>
      <tr>
        <th><b>Song title</b></th>
        <th><b>Audio</b></th>
      </tr>
      <tr>
        <td>JACKJ97 Vuc Tham Binh Yen 1</td>
        <td>
          <a href="${mp3_1}">MP3 (Play)</a>
          &nbsp;|&nbsp;
          <a href="${mp3_1}" download>Download</a>
        </td>
      </tr>
      <tr>
        <td>JACKJ97 Vuc Tham Binh Yen 2</td>
        <td>
          <a href="${mp3_2}">MP3 (Play)</a>
          &nbsp;|&nbsp;
          <a href="${mp3_2}" download>Download</a>
        </td>
      </tr>
    </table>

    <p style="margin-top:1rem">
      <audio id="player" controls style="width:100%;">
        <source src="${mp3_1}" type="audio/mpeg"/>
        Trình duyệt của bạn không hỗ trợ audio.
      </audio>
    </p>

    <p><a href="<c:url value='/download'><c:param name='action' value='viewAlbums'/></c:url>">View list of Albums</a></p>
    <p><a href="<c:url value='/download'><c:param name='action' value='viewCookies'/></c:url>">View all cookies</a></p>
  </body>
</html>

