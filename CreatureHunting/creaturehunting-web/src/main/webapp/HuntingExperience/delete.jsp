<%-- 
    Document   : delete
    Created on : 28.11.2014, 15:17:23
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
            <h2><f:message key="exp.delete.title"/></h2>
            <h4>
                <f:message key="exp.delete.ask">
                    <f:param value="${actionBean.huntExp.id}"/>
                </f:message>
            </h4>
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntExpActionBean" class="form-horizontal">
                <s:hidden name="huntExp.id"/>
                <fieldset>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <s:submit name="deleteHuntExp" class="btn btn-default">
                                <f:message key="all.delete"/>
                            </s:submit>
                        </div>
                    </div>
                </fieldset>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>