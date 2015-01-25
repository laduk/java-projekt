<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="area.list.title" activeAreaTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" var="actionBean"/>

        <div class="container">
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" class="form-inline text-right">
                <div class="form-group">
                    <s:label for="b1" name="area.name.find"/>
                    <s:text id="b1" name="find" class="form-control"/>
                </div>
                <button type="submit" name="list" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-search"></span>
                    <f:message key="all.find"/>
                </button>
                            <script type="text/javascript">
                    $(document).ready(function(){
                        $('[data-toggle="popover"]').popover({
                        placement : 'bottom'
                        });
                    });
                </script>

                <a href="#" class="btn btn-primary" data-toggle="popover" 
                       tabindex="0" data-trigger="focus" 
                       title="<f:message key="find.area.title"/>" 
                       data-content="<f:message key="find.text.con"/>">
                    <span class="glyphicon glyphicon-info-sign">                            
                    </span>
                </a>
            </s:form>
            
            <h2><f:message key="area.list.title"/></h2>

            <c:choose>
                <c:when test="${actionBean.areas != null && actionBean.areas.size() > 0}">
                    <table class="table">
                        <tr>
                            <th>id</th>
                            <th><f:message key="area.name"/></th>
                            <th><f:message key="area.description"/></th>
                            <th><f:message key="area.acreage"/></th>
                            <th><f:message key="area.listOfCreatures"/></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${actionBean.areas}" var="area">
                            <tr>
                                <td>${area.id}</td>
                                <td><c:out value="${area.name}"/></td>
                                <td><c:out value="${area.description}"/></td>
                                <td><c:out value="${area.acreage}"/></td>
                                <td>
                                    <c:forEach items="${area.listOfCreatures}" var="creature" varStatus="status">
                                        <c:out value="${creature.name}${not status.last ? ',' : ''}"/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <sec:authorize url="/admin">
                                    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" event="edit">
                                        <s:param name="area.id" value="${area.id}"/>
                                        <span class="glyphicon glyphicon-edit"></span>
                                        <f:message key="all.edit" />
                                    </s:link>
                                    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" event="delete">
                                        <s:param name="area.id" value="${area.id}"/>
                                        <span class="glyphicon glyphicon-remove"></span>
                                        <f:message key="all.delete"/>
                                    </s:link>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h6><f:message key="area.list.empty"/></h6>
                </c:otherwise>
            </c:choose>

            <p>
                <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" event="add">
                    <span class="glyphicon glyphicon-plus"></span>                
                    <f:message key="area.add.title"/>
                </s:link>
            </p>
        </div>
    </s:layout-component>
</s:layout-render>