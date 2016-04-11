<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.CategoryDAO" %>
<%@ page import="DAO.PostDAO" %>
<%@ page import="persistence.CategoryPO" %>
<%@ page import="persistence.PostPO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<html>
<head>
    <script type="text/javascript" src="resources/js/jquery-2.2.1.min.js"></script>

    <link rel="stylesheet" href="resources/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <script type="text/javascript" src="resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="resources/dist/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="resources/dist/css/bootstrap-multiselect.css" type="text/css"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <link rel="stylesheet" href="resources/css/styles.css"/>

    <title></title>
</head>
<body>
<%!
    List<CategoryPO> categoryPOs = CategoryDAO.getAllCategory();
    List<PostPO> allPosts = PostDAO.getAllPosts();
%>
<%
    String inputTitle = request.getParameter("inputTitle");
    if (!Objects.equals(inputTitle, "") && inputTitle != null) {
        PostPO postPO = new PostPO();
        String inputCategory = request.getParameter("inputCategory");
        String inputText = request.getParameter("inputText");
        String inputEmail = request.getParameter("inputEmail");
        Timestamp timestamp = Timestamp.from(Instant.now());
        postPO.setTitle(inputTitle);
        postPO.setText(inputText);
        postPO.setEmail(inputEmail);
//        postPO.setCategoryId(cat);
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
            <div style="position: fixed; width: 25%">
                <div class="page-header">
                    <h3>Add new post</h3>
                </div>
                <form action="/" method="post">
                    <div class="form-group">
                        <select class="form-control" id="example-getting-started" multiple="multiple"
                                name="inputCategory">
                            <%--<option value="4" disabled selected hidden style="color: #999">Categories</option>--%>
                            <% for (CategoryPO categoryPO : categoryPOs) { %>
                            <option value="<%=categoryPO.getId()%>"><%=categoryPO.getName()%>
                            </option>
                            <% } %>
                        </select>
                        <script type="text/javascript">
                            $(document).ready(function () {
                                $('#example-getting-started').multiselect({
                                    enableFiltering: true
                                });
                            });
                        </script>
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
