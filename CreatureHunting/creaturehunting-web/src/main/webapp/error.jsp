<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<s:layout-render name="/layout.jsp" titlekey="index.creatureHunt" activeHomeTab="active">
    <s:layout-component name="body">
        <div class="container">
            <h1><f:message key="index.error"/></h1>
            <img src="http://www.spore.com/static/war/images/global/500_Error_sm.png"/>
        </div>
    </s:layout-component>
</s:layout-render>