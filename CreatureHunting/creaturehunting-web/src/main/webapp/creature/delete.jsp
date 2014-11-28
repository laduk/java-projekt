<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="creature.delete.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="creature.delete.title"/></h2>
            <h4>
                <f:message key="creature.delete.ask">
                    <f:param value="${actionBean.creature.name}"/>
                </f:message>
            </h4>
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" class="form-horizontal">
                <s:hidden name="creature.id"/>
                <fieldset>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <s:submit name="doDelete" class="btn btn-default">
                                <f:message key="all.delete"/>
                            </s:submit>
                        </div>
                    </div>
                </fieldset>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>