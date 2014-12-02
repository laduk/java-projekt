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
            <s:layout-component name="head"/>
        
            <link rel="stylesheet"
            href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
            <link rel="stylesheet"
            href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css">
            <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/smoothness/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery.min.js">                
            </script>
             <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
            <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js">                
            </script>
            <script>
              $(function() {
                $( "#datepicker" ).datepicker({ dateFormat: "dd-mm-yy" });
              });
            </script>
        </head>
    <style>.bootstrap-demo{margin:30px;}</style>
        
        <body>
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-6">
                            <span class="sr-only"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.BaseActionBean">
                                <f:message key="navigation.home"/>
                            </s:link>
                        </button>
                    </div>
                </div>
                
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6" style="min-width: 500px">
                    <ul class="nav navbar-nav" style="min-width: 500px">
                        <li class="active">
                            <s:link href="/pa165/index.jsp">
                                <f:message key="navigation.home"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean">
                                <f:message key="navigation.huntExp"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean">
                                <f:message key="navigation.creature"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.AreaActionBean">
                                <f:message key="navigation.area"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean">
                                <f:message key="navigation.weapon"/>
                            </s:link>
                        </li>
                    </ul>
                </div>
                
                <s:layout-component name="header"/>
                
                <div class="content">
                    <s:messages/>
                    <s:layout-component name="body"/>
                </div>
            </nav>
                
        </body>
    </html>
</s:layout-definition>