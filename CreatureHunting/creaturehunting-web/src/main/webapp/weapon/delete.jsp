<%-- 
    Document   : delete
    Created on : Nov 28, 2014, 6:56:32 PM
    Author     : lada
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="weapon.delete.title" activeWeaponTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="weapon.delete.title"/></h2>
            <h4>
                <f:message key="weapon.delete.ask">
                    <f:param value="${actionBean.weapon.name}"/>
                </f:message>
            </h4>
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" class="form-horizontal">
                <s:hidden name="weapon.id"/>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" name="doDelete" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-remove"></span>
                            <f:message key="all.delete"/>
                        </button>
                    </div>
                </div>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>
