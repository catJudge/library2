<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../../static/js/jquery-2.2.1.min.js"></script>

    <link rel="stylesheet" href="../../static/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <script type="text/javascript" src="../../static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../../static/dist/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="../../static/dist/css/bootstrap-multiselect.css" type="text/css"/>

    <link rel="stylesheet" href="../../static/css/styles.css"/>

    <title></title>
    <script>
        function validateComment(input) {
            if (input.value.length < 10) {
                input.setCustomValidity("Input more then 10 symbols")
            }
            else if (input.value.length > 300) {
                input.setCustomValidity("Input less then 300 symbols")
            }
            else {
                input.setCustomValidity("");
            }
        }
    </script>
</head>
<body>
<div class="content container">
    <div class="row">
        <div class="col-md-6">
            <div class="post">
                <div class="date">
                    created date: ${postPO.createdDate}
                    by: ${postPO.email}</div>
                <div class="page-header">
                    <h2>${postPO.title}</h2>
                </div>
                <p>${postPO.text}</p>

                <h2>Comments:</h2>
                <c:forEach var="comment" items="${comments}">
                    <div class="well">
                        <div style="color: red">
                            anonymous ${comment.id}, created date: ${comment.createdDate}
                        </div>
                            ${comment.text}
                    </div>
                </c:forEach>
            </div>
            <form action="/post/${id}" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="inputText" placeholder="Comment" required
                           oninput="validateComment(this)">
                </div>
                <div style="text-align: center">
                    <button type="submit" class="btn middle btn-default">Send</button>
                </div>
            </form>
        </div>
        <div class="col-md-4">
        </div>
        <div class="col-md-2">
            <div style="position: fixed">
                <div class="page-header">
                    <h3>Categories</h3>
                </div>
                <div class="form-group">
                    <ul class="list-group">
                        <li class="list-group-item"><a href="<c:url value="/"/>">All</a></li>
                        <c:forEach var="categoryPO" items="${categoryPOs}">
                            <li class="list-group-item"><a
                                    href="<c:url value="/category/${categoryPO.id}"/>">${categoryPO.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
