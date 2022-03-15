<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <h3>Topics</h3>
    <ul id="topics">
        <li><a href="topic/all.do">All</a></li>

        <c:forEach items="${app.topicList.topics}" var="topic">
            <li><a href="/topic${topic.url} ">${topic.title}  </a></li>
        </c:forEach>

    </ul>
</div>