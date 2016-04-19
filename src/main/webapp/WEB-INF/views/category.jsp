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
        function validateTitle(input) {
            if (input.value.length < 10) {
                input.setCustomValidity("Input more then 10 symbols")
            }
            else if (input.value.length > 30) {
                input.setCustomValidity("Input less then 30 symbols")
            }
            else {
                input.setCustomValidity("");
            }
        }
        function validateText(input) {
            if (input.value.length < 30) {
                input.setCustomValidity("Input more then 30 symbols")
            }
            else if (input.value.length > 600) {
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
            <div class="page-header">
                <h3>Category ${categoryPO.name}</h3>
            </div>
            <c:forEach var="post" items="${posts}">
                <h2><a href="<c:url value="/post/${post.id}"/>">${post.title}</a></h2>

                <p>${post.text}</p>
            </c:forEach>
        </div>
        <div class="col-md-4">

            <div style="position: fixed; width: 25%">
                <div class="page-header">
                    <h3>Add new post</h3>
                </div>
                <form action="<c:url value="/category/${id}"/>" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" id="inputTitle" name="inputTitle" placeholder="Title"
                               required oninput="validateTitle(this)">
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" id="inputText" name="inputText" placeholder="Text" required
                                  oninput="validateText(this)"></textarea>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email">
                    </div>
                    <div style="text-align: center">
                        <input type="submit" id="submitBtn" name="submitBtn" class="btn middle btn-default"
                               value="Send">
                    </div>
                </form>
                <p>
            </div>
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
