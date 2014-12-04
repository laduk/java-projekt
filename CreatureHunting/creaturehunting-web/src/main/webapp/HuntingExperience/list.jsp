<%-- 
    Document   : list
    Created on : 28.11.2014, 15:01:46
    Author     : Fita
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="exp.list.title" activeHuntingTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="exp.list.title"/></h2>

            <table class="table">
                <tr>
                    <th>id</th>
                    <th><f:message key="exp.weapon"/></th>
                    <th><f:message key="exp.creature"/></th>
                    <th><f:message key="exp.dateOfExperience"/></th>
                    <th><f:message key="exp.efficiency"/></th>
                    <th><f:message key="exp.description"/></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.huntExp}" var="hunt">
                    <tr>
                        <td>${hunt.id}</td>
                        <td><c:out value="${hunt.weapon.name}"/></td>
                        <td><c:out value="${hunt.creature.name}"/></td>
                        <td><c:out value="${hunt.dateOfExperience}"/></td>
                        <td><c:out value="${hunt.efficiency}"/></td>
                        <td><c:out value="${hunt.description}"/></td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" event="edit">
                                <s:param name="huntExp.id" value="${hunt.id}"/>
                                <span class="glyphicon glyphicon-edit"></span>
                                <f:message key="all.edit" />
                            </s:link>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" event="delete">
                                <s:param name="huntExp.id" value="${hunt.id}"/>
                                <span class="glyphicon glyphicon-remove"></span>
                                <f:message key="all.delete"/>
                            </s:link>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" event="create">
                <span class="glyphicon glyphicon-plus"></span>
                <f:message key="exp.create.title"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>