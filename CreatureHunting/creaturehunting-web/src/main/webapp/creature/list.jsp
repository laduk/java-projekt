<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="creature.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="creature.list.title"/></h2>

            <table class="table">
                <tr>
                    <th>id</th>
                    <th><f:message key="creature.name"/></th>
                    <th><f:message key="creature.height"/></th>
                    <th><f:message key="creature.weight"/></th>
                    <th><f:message key="creature.agility"/></th>
                    <th><f:message key="creature.listOfAreas"/></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.creatures}" var="creature">
                    <tr>
                        <td>${creature.id}</td>
                        <td><c:out value="${creature.name}"/></td>
                        <td><c:out value="${creature.height}"/></td>
                        <td><c:out value="${creature.weight}"/></td>
                        <td><c:out value="${creature.agility}"/></td>
                        <td>
                            <c:forEach items="${creature.listOfAreas}" var="area">
                                <c:out value="${area.name}"/>
                            </c:forEach>
                        </td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" event="edit">
                                <s:param name="creature.id" value="${creature.id}"/>
                                <f:message key="all.edit" />
                            </s:link>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" event="delete">
                                <s:param name="creature.id" value="${creature.id}"/>
                                <f:message key="all.delete"/>
                            </s:link>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <p>
                <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" event="add">
                    <f:message key="creature.add.title"/>
                </s:link>
            </p>
        </div>
    </s:layout-component>
</s:layout-render>