<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="creature.list.title" activeCreatureTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" var="actionBean"/>

        <div class="container">
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" class="form-inline text-right">
                <div class="form-group">
                    <s:label for="b1" name="creature.name"/>
                    <s:text id="b1" name="find" class="form-control"/>
                </div>
                <button type="submit" name="list" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-search"></span>
                    <f:message key="all.find"/>
                </button>
            </s:form>
            
            <h2><f:message key="creature.list.title"/></h2>
            
            <c:choose>
                <c:when test="${actionBean.creatures != null && actionBean.creatures.size() > 0}">
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
                                        <span class="glyphicon glyphicon-edit"></span>
                                        <f:message key="all.edit"/>
                                    </s:link>
                                    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" event="delete">
                                        <s:param name="creature.id" value="${creature.id}"/>
                                        <span class="glyphicon glyphicon-remove"></span>
                                        <f:message key="all.delete"/>
                                    </s:link>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h6><f:message key="creature.list.empty"/></h6>
                </c:otherwise>
            </c:choose>

            <p>
                <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" event="add">
                    <span class="glyphicon glyphicon-plus"></span>
                    <f:message key="creature.add.title"/>
                </s:link>
            </p>
        </div>
    </s:layout-component>
</s:layout-render>