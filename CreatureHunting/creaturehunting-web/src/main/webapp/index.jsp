<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<s:layout-render name="/layout.jsp" titlekey="index.creatureHunt" activeHomeTab="active">
    <s:layout-component name="body">
        <div class="container">
            <h1><f:message key="index.welcome"/></h1>
            <img src="http://i1106.photobucket.com/albums/h378/PIVOISISKI/650px-MonstersScale-small.png"/>
            <sec:authorize access="isAnonymous()">
                If you want to login as administrator use this credentials: <br><br>
                <b>Username: admin</b><br>
                <b>Password: admin</b><br><br>
                If you want to login as a regular system user use this login<br><br>
                <b>Username: survivor</b><br>
                <b>Password: survivor</b><br><br>
            </sec:authorize>
        </div>
    </s:layout-component>
</s:layout-render>