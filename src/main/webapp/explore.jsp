<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>helo</title>
    <link rel="stylesheet" href="https://unpkg.com/tachyons@4.12.0/css/tachyons.min.css"/>
    <style type="text/css">
        html{
            font-family: "Roboto Light", serif;
            font-size: 18px;
        }
        .icon{
            width: 1em;
            height: 1em;
        }
        ul li:not(:first-child){
            margin-top: 8px;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<h3 class="ma0 pa2 normal">${date}</h3>
<h1 class="ma0 pa2">${path}</h1>
<hr/>
<div class="flex flex-column pt2 pb2">
    <div class="inline-flex ml2">
        <i data-feather="arrow-up-circle" class="icon"></i>
        <a class="ml2" href="?path=${path.substring(0, path.lastIndexOf("\\") + (path.lastIndexOf("\\") != path.indexOf("\\") ? 0 : 1))}">Up</a><br>
    </div>
    <span class="b mt2 pa2">Directories</span>
    <ul class="flex-column flex ma0 pa2">
        <c:forEach var="directory" items="${directories}">
            <li class="inline-flex">
                <i data-feather="folder" class="red icon"></i>
                <a href="?path=${directory.getAbsolutePath()}" class="ml2">${directory.getName()}/</a>
            </li>
        </c:forEach>
    </ul>
    <span class="b mt2 pa2">Files</span>
    <ul class="flex-column flex ma0 pa2">
        <c:forEach var="file" items="${files}">
            <li class="inline-flex">
                <i data-feather="file" class="yellow icon"></i>
                <a href="?path=${file.getAbsolutePath()}" class="ml2">${file.getName()}</a><br>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
<script src="https://unpkg.com/feather-icons"></script>
<script>
    feather.replace()
</script>
</html>