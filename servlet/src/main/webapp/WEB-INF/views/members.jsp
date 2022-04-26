<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
 <meta charset="UTF-8">
</head>
<body>
    <a href="/index.html">메인</a>
        <table>
             <thead>
                 <th>id</th>
                 <th>userName</th>
                 <th>age</th>
             </thead>
             <tbody>
                <c:forEach var="item" items="${memberList}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.userName}</td>
                        <td>${item.age}</td>
                    </tr>
                </c:forEach>
             </tbody>
        </table>
</body>
</html>