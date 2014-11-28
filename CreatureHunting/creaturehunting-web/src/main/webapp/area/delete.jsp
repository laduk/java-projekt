<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="area.delete.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean">
            <s:hidden name="area.id"/>
            <fieldset>
                <legend>
                    <f:message key="area.delete.title"/>
                </legend>
                <f:message key="area.delete.ask">
                    <f:param value="${actionBean.area.name}"/>
                </f:message>
                <br />
                <s:submit name="doDelete">
                    <f:message key="all.delete"/>
                </s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>