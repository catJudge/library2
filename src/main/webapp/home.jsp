<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.CategoryDAO" %>
<%@ page import="DAO.PostDAO" %>
<%@ page import="persistence.CategoryPO" %>
<%@ page import="persistence.PostPO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="resources/css/styles.css">
    <title></title>
</head>
<body>
<%!
    List<CategoryPO> categoryPOs = CategoryDAO.getAllCategory();
    List<PostPO> allPosts = PostDAO.getAllPosts();
%>
<%
    String inputTitle = request.getParameter("inputTitle");
    if (inputTitle != "" && inputTitle != null) {
        PostPO postPO = new PostPO();
        Long cat = Long.valueOf(request.getParameter("inputCategory"));
        String inputText = request.getParameter("inputText");
        String inputEmail = request.getParameter("inputEmail");
        Timestamp timestamp = Timestamp.from(Instant.now());
        postPO.setTitle(inputTitle);
        postPO.setText(inputText);
        postPO.setEmail(inputEmail);
        postPO.setCategoryId(cat);
        postPO.setCreatedDate(timestamp);
        PostDAO.insertPost(postPO);
    }
    allPosts = PostDAO.getAllPosts();
%>
<div class="content container">
    <div class="row">
        <div class="col-md-6">
            <div class="page-header">
                <h3>All posts</h3>
            </div>
            <% for (PostPO post : allPosts) { %>
            <%--<div class="post">--%>
            <h2><a href="/post?id=<%= post.getId()%>"><%= post.getTitle()%>
            </a></h2>

            <p><%= post.getText() %>
            </p>
            <%--</div>--%>

            <% } %>
        </div>
        <div class="col-md-4">
            <div style="position: fixed; width: 30%">
                <div class="page-header">
                    <h3>Add new post</h3>
                </div>
                <form action="/" method="post">
                    <div class="form-group">
                        <select class="form-control" name="inputCategory">
                            <option value="4" disabled selected hidden style="color: #999">Categories</option>
                            <% for (CategoryPO categoryPO : categoryPOs) { %>
                            <option value="<%=categoryPO.getId()%>"><%=categoryPO.getName()%>
                            </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="inputTitle" placeholder="Title">
                    </div>
                    <div class="form-group">
                        <textarea type="text" class="form-control" name="inputText" placeholder="Text"></textarea>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" name="inputEmail" placeholder="Email">
                    </div>
                    <center>
                        <button type="submit" class="btn middle btn-default">Send</button>
                    </center>
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
                        <% for (CategoryPO categoryPO : categoryPOs) { %>
                        <li class="list-group-item"><a
                                href="/category?id=<%= categoryPO.getId() %>"><%= categoryPO.getName() %>
                        </a></li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
