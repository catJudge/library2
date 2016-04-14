<%@ page import="DAO.CategoryDAO" %>
<%@ page import="DAO.CommentDAO" %>
<%@ page import="DAO.PostDAO" %>
<%@ page import="persistence.CategoryPO" %>
<%@ page import="persistence.CommentPO" %>
<%@ page import="persistence.PostPO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../../static/js/jquery-2.2.1.min.js"></script>

    <link rel="stylesheet" href="../../static/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <script type="text/javascript" src="../../static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../../static/dist/js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="../../static/dist/css/bootstrap-multiselect.css" type="text/css"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
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
<%!
    List<CategoryPO> categoryPOs = CategoryDAO.getAllCategory();
    List<CommentPO> commentsByPostId;
    PostPO postPO;
%>
<%
    Long id = Long.valueOf(request.getParameter("id"));
    postPO = PostDAO.getPostById(id);
    String inputText = request.getParameter("inputText");
    if (!Objects.equals(inputText, "") && inputText != null) {
        CommentPO commentPO = new CommentPO();
        Timestamp timestamp = Timestamp.from(Instant.now());
        commentPO.setText(inputText);
        commentPO.setCreatedDate(timestamp);
        commentPO.setPost(postPO);
        CommentDAO.insertComment(commentPO);
    }

    commentsByPostId = CommentDAO.getCommentsByPostId(id);
%>
<div class="content container">
    <div class="row">
        <div class="col-md-6">
            <div class="post">
                <div class="date">
                    created date: <%=postPO.getCreatedDate().toString()%>
                    by: <%=postPO.getEmail()%>
                </div>
                <div class="page-header">
                    <h1><%=postPO.getTitle()%>
                    </h1>
                </div>
                <p><%=postPO.getText()%>
                </p>

                <h2>Comments:</h2>
                <% for (CommentPO commentPO : commentsByPostId) { %>
                <div class="well">
                    <div style="color: red">
                        anonymous <%=commentPO.getId()%>, created date: <%=commentPO.getCreatedDate()%>
                    </div>
                    <%=commentPO.getText()%>
                </div>
                <% } %>
            </div>
            <form action="/post?id=<%=id%>" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="inputText" placeholder="Comment" required
                           oninput="validateComment(this)">
                </div>
                <center>
                    <button type="submit" class="btn middle btn-default">Send</button>
                </center>
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
                        <li class="list-group-item"><a href="/">All</a></li>
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
