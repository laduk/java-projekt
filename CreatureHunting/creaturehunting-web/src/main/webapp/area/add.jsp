<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="area.add.title" activeAreaTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="area.add.title"/></h2>
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean" class="form-horizontal">
                <fieldset>
                    <%@include file="form.jsp"%>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">                            
                            <button type="submit" name="doAdd" class="btn btn-default">
                                <span class="glyphicon glyphicon-ok"></span>
                                <f:message key="all.save"/>                                
                            </button>
                            <button type="button" name="back" class="btn btn-default" onclick="history.back(-1);">                          
                                <f:message key="all.back"/>
                            </button>
                        </div>
                    </div>
                </fieldset>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>