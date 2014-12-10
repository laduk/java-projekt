<%-- 
    Document   : list
    Created on : Nov 28, 2014, 3:14:22 PM
    Author     : lada
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="weapon.list.title" activeWeaponTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" var="actionBean"/>

        <div class="container">
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" class="form-inline text-right">
                <div class="form-group">
                    <s:label for="b1" name="weapon.name"/>
                    <s:text id="b1" name="find" class="form-control"/>
                </div>
                <button type="submit" name="list" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-search"></span>
                    <f:message key="all.find"/>
                </button>                
                <script type="text/javascript">
                    $(document).ready(function(){
                        $('[data-toggle="popover"]').popover({
                        placement : 'bottom'
                        });
                    });
                </script>

                <a href="#" class="btn btn-primary" data-toggle="popover" 
                       tabindex="0" data-trigger="focus" 
                       title="<f:message key="find.weapon.title"/>" 
                       data-content="<f:message key="find.text.reg"/>">
                    <span class="glyphicon glyphicon-info-sign">                            
                    </span>
                </a>   
            </s:form>
            
            <h2><f:message key="weapon.list.title"/></h2>
            
            <table class="table">
                <tr>
                    <th>id</th>
                    <th><f:message key="weapon.name"/></th>
                    <th><f:message key="weapon.gunreach"/></th>
                    <th><f:message key="weapon.ammunition"/></th>
                    <th></th>
                </tr>
                <c:forEach items="${actionBean.weapons}" var="weapon">
                    <tr>
                        <td>${weapon.id}</td>
                        <td><c:out value="${weapon.name}"/></td>
                        <td><c:out value="${weapon.gunReach}"/></td>
                        <td><c:out value="${weapon.ammunition}"/></td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" event="edit">
                                <s:param name="weapon.id" value="${weapon.id}"/>
                                <span class="glyphicon glyphicon-edit"></span>
                                <f:message key="all.edit" />
                            </s:link>
                            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" event="delete">
                                <s:param name="weapon.id" value="${weapon.id}"/>
                                <span class="glyphicon glyphicon-remove"></span>
                                <f:message key="all.delete"/>
                            </s:link>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.WeaponActionBean" event="add">
                <span class="glyphicon glyphicon-plus"></span> 
                <f:message key="weapon.add.title"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>