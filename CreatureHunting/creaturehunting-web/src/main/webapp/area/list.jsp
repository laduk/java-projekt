<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="area.list.title" activeAreaTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="area.list.title"/></h2>

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
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" event="add">
                <span class="glyphicon glyphicon-plus"></span>                
                <f:message key="area.add.title"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>