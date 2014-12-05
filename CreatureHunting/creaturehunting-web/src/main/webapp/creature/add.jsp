<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="creature.add.title" activeCreatureTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="creature.add.title"/></h2>
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" class="form-horizontal">
                <%@include file="form.jsp"%>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" name="doAdd" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-ok"></span>
                            <f:message key="all.save"/>
                        </button>
                    </div>
                </div>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>