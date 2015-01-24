<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="login.list.title" activeLogInTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.LogInActionBean" var="actionBean"/>

        <div class="container">
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.LogInActionBean" class="form-inline text-right">
                <div class="form-group">
                    <s:label for="b1" name="login.name.find"/>
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
                       title="<f:message key="find.login.title"/>" 
                       data-content="<f:message key="find.text.reg"/>">
                    <span class="glyphicon glyphicon-info-sign">                            
                    </span>
                </a>
            </s:form>
            
            <h2><f:message key="login.list.title"/></h2>

            <!--<c:choose>
                <c:when test="${actionBean.logins != null && actionBean.logins.size() > 0}">
                    <table class="table">
                        <tr>
                            <th>id</th>
                            <th><f:message key="login.name"/></th>
                            <th><f:message key="login.isRoot"/></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${actionBean.logins}" var="login">
                            <tr>
                                <td>${login.id}</td>
                                <td><c:out value="${login.name}"/></td>
                                <td><c:out value="${login.isRoot}"/></td>                                
                                <td>
                                    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.LogInActionBean" event="edit">
                                        <s:param name="login.id" value="${login.id}"/>
                                        <span class="glyphicon glyphicon-edit"></span>
                                        <f:message key="all.edit" />
                                    </s:link>
                                    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.LogInActionBean" event="delete">
                                        <s:param name="login.id" value="${login.id}"/>
                                        <span class="glyphicon glyphicon-remove"></span>
                                        <f:message key="all.delete"/>
                                    </s:link>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h6><f:message key="login.list.empty"/></h6>
                </c:otherwise>
            </c:choose>-->

            <p>
                <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.LogInActionBean" event="add">
                    <span class="glyphicon glyphicon-plus"></span>                
                    <f:message key="login.add.title"/>
                </s:link>
            </p>
        </div>
    </s:layout-component>
</s:layout-render>