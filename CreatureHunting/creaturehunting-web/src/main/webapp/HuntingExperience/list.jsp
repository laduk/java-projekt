<%-- 
    Document   : list
    Created on : 28.11.2014, 15:01:46
    Author     : Fita
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="exp.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntExpActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="exp.list.title"/></h2>

            <table class="table">
                <tr>
                    <th>id</th>
                    <th><f:message key="exp.weapon"/></th>
                    <th><f:message key="exp.creature"/></th>
                    <th><f:message key="exp.dateOfExperience"/></th>
                    <th><f:message key="exp.dateOfExperience"/></th>
                    <th><f:message key="exp.efficiency"/></th>
                    <th><f:message key="exp.description"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.huntEx}" var="exp">
                    <tr>
                        <td>${exp.id}</td>
                        <td><c:out value="${exp.weapon}"/></td>
                        <td><c:out value="${exp.creature}"/></td>
                        <td><c:out value="${exp.dateOfExperience}"/></td>
                        <td><c:out value="${exp.efficiency}"/></td>
                        <td><c:out value="${exp.description}"/></td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntExpActionBean" event="edit">
                                <s:param name="exp.id" value="${exp.id}"/>
                                <f:message key="all.edit" />
                            </s:link>
                        </td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntExpActionBean" event="delete">
                                <s:param name="area.id" value="${area.id}"/>
                                <f:message key="all.delete"/>
                            </s:link>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntExpActionBean" event="create">
                <f:message key="exp.add.title"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>