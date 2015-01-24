<%-- 
    Document   : layout
    Created on : 25.11.2014, 12:54:31
    Author     : Fita
--%>

<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <meta charset="utf-8"/>
            <s:layout-component name="head"/>
        
            <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"/>
            <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css"/>
            <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/smoothness/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery.min.js"></script>
            <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
            <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
            <script>
                $(function() {
                    $( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy" });
                });
            </script>
        </head>

        <body>
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <ul class="nav nav-tabs">
                        <li class="${activeHomeTab}">
                            <s:link href="/pa165/index.jsp">
                                <f:message key="navigation.home"/>
                            </s:link>
                        </li>
                        <li class="${activeHuntingTab}">
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean">
                                <f:message key="navigation.huntExp"/>
                            </s:link>
                        </li>
                        <li class="${activeCreatureTab}">
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean">
                                <f:message key="navigation.creature"/>
                            </s:link>
                        </li>
                        <li class="${activeAreaTab}">
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean">
                                <f:message key="navigation.area"/>
                            </s:link>
                        </li>
                        <li class="${activeWeaponTab}">
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean">
                                <f:message key="navigation.weapon"/>
                            </s:link>
                        </li>
                        <li class="${activeLogInTab}">
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.LogInActionBean">
                                <f:message key="navigation.login"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link href="http://localhost:8084/creaturehunting-restClient/">
                                Rest Client
                            </s:link>
                        </li>
                    </ul>
                </div>
            </nav>

            <s:layout-component name="header"/>

            <s:messages/>

            <s:layout-component name="body"/>
        </body>
    </html>
</s:layout-definition>
