<%@ page import="com.knowledgespike.blog.NewsItem" %>
<%@ page import="java.util.List" %>
<div class="p-3 border bg-light" id="newsitems">

    <%
        List<NewsItem> items = (List<NewsItem>) request.getAttribute("items");
    %>
    <% for (NewsItem item: items) {
        pageContext.setAttribute("item", item);
    %>

        <div>
            <h2><a href="/news/${item.title}.do">${item.title}</a></h2>
            <div>
                ${item.entry}
            </div>
        </div>
    <% } %>

</div>