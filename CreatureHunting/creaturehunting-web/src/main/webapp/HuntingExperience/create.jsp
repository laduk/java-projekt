<%-- 
    Document   : create
    Created on : 25.11.2014, 12:52:22
    Author     : Fita
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="exp.create.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntExpActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="area.add.title"/></h2>
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntExpActionBean" class="form-horizontal">
                <fieldset>
                    <%@include file="form.jsp"%>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <s:submit name="createHuntExp" class="btn btn-default"><f:message key="all.save"/></s:submit>
                        </div>
                    </div>
                </fieldset>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>