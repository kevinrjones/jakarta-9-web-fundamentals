<%@ page import="com.knowledgespike.blog.NewsItem" %>
<%@ page import="java.util.List" %>
<div class="p-3 border bg-light">

    <%
        List<NewsItem> items = (List<NewsItem>) request.getAttribute("items");
    %>
    <% for (NewsItem item: items) { %>

        <div>
            <h2><a href="/news/<%= item.getTitle()%>.do"><%= item.getTitle() %></a></h2>
            <div>
                    <%= item.getEntry() %>
            </div>
        </div>
    <% } %>

</div>