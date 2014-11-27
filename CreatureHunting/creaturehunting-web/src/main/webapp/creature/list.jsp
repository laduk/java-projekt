<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="navigation.creature">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" var="actionBean"/>

        <p><f:message key="creature.listcreatures"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="creature.name"/></th>
                <th><f:message key="creature.height"/></th>
                <th><f:message key="creature.weight"/></th>
                <th><f:message key="creature.agility"/></th>
                <th></th>
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
                        <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" event="edit">
                            <s:param name="creature.id" value="${creature.id}"/>
                            edit
                        </s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean">
                            <s:hidden name="creature.id" value="${creature.id}"/>
                            <s:submit name="delete"><f:message key="creature.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean">
            <fieldset>
                <legend><f:message key="creature.newcreature"/></legend>
                <!--%@include file="form.jsp"%-->
                <s:submit name="add">Vytvořit novou knihu</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>