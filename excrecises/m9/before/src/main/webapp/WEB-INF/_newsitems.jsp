<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="p-3 border bg-light" id="newsitems">

    <c:forEach items="${items}" var="item">
        <div>
            <h2><a href='<c:url value="/news/${item.title}.do"/>'>${item.title}</a></h2>
            <div>
                    ${item.entry}
            </div>
        </div>

    </c:forEach>


</div>