<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="creature.add.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean">
            <fieldset>
                <legend><f:message key="creature.edit.title"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="doAdd"><f:message key="all.save"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>