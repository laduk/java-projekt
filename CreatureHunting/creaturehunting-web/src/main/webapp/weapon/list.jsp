<%-- 
    Document   : listWeapons
    Created on : Nov 28, 2014, 3:14:22 PM
    Author     : lada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="navigation.weapon">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" var="actionBean"/>

        <p><f:message key="weapon.listofweapons"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="weapon.name"/></th>
                <th><f:message key="weapon.gunreach"/></th>
                <th><f:message key="weapon.ammunition"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.weapons}" var="creature">
                <tr>
                    <td>${weapon.id}</td>
                    <td><c:out value="${weapon.name}"/></td>
                    <td><c:out value="${weapon.gunreach}"/></td>
                    <td><c:out value="${weapon.ammunotion}"/></td>
                    <td>
                        <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" event="edit">
                            <s:param name="weapon.id" value="${weapon.id}"/>
                            edit
                        </s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean">
                            <s:hidden name="weapon.id" value="${weapon.id}"/>
                            <s:submit name="delete"><f:message key="weapon.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean">
            <fieldset>
                <legend><f:message key="weapon.newweapon"/></legend>
                <!--%@include file="form.jsp"%-->
                <s:submit name="add">Zalozit zaznam pro novou zbran</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>