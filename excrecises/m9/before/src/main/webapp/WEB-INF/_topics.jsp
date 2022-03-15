<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
    <h3>Topics</h3>
    <ul id="topics">
        <li><a href='<c:url value="topic/all.do"/>'>All</a></li>

        <c:forEach items="${app.topicList.topics}" var="topic">
            <li><a href='<c:url value="/topic${topic.url}"/>'>${topic.title}  </a></li>
        </c:forEach>

    </ul>
</div>