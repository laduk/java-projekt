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
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
            <s:layout-component name="head"/>
        </head>
        
        <body>
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-6">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    
                </div>                
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <s:link beanclass="cz.muni.fi.PA165.actionBean.HuntExpActionBean">
                                <f:message key="navigation.huntExp"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link href="cz.muni.fi.PA165.actionBean.CreatureActionBean">
                                <f:message key="navigation.creature"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link href="cz.muni.fi.PA165.actionBean.AreaActionBean">
                                <f:message key="navigation.area"/>
                            </s:link>
                        </li>
                        <li>
                            <s:link href="cz.muni.fi.PA165.actionBean.WeaponActionBean">
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