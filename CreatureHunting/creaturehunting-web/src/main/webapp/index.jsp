<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<s:layout-render name="/layout.jsp" titlekey="index.creatureHunt" activeHomeTab="active">
    <s:layout-component name="body">
        <div class="container">
            <h1><f:message key="index.welcome"/></h1>
            <img src="http://i1106.photobucket.com/albums/h378/PIVOISISKI/650px-MonstersScale-small.png"/>
            <br /><br />
            

            <sec:authorize access="isAnonymous()">
                <c:if test="${fn:endsWith(pageContext.request.requestURI, '/badLogin.jsp')}">
                    <div class="container bg-danger" style="margin-top:10px; margin-bottom:10px">
                        <h4><f:message key="all.info.badlogin"/></h4>
                    </div>
                </c:if>
                <c:if test="${fn:endsWith(pageContext.request.requestURI, '/logOut.jsp')}">
                    <div class="container bg-success" style="margin-top:10px; margin-bottom:10px">
                        <h4><f:message key="all.info.logout"/></h4>
                    </div>
                </c:if>
                <c:if test="${fn:endsWith(pageContext.request.requestURI, '/needLogin.jsp')}">
                    <div class="container bg-danger" style="margin-top:10px; margin-bottom:10px">
                        <h4><f:message key="all.info.needlogin"/></h4>
                    </div>
                </c:if>
                <form name="f" action="<s:url value='j_spring_security_check'/>" class="form-horizontal" method="post">
                    <div class="form-group">
                        <s:label for="b1" name="login.name" class="control-label col-sm-2"/>
                        <div class="col-sm-10">
                            <input type="text" id="b1" name="j_username" class="form-control" style="width:300px"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <s:label for="b2" name="login.password" class="control-label col-sm-2"/>
                        <div class="col-sm-10">
                            <input type="password" id="b2" name="j_password" class="form-control" style="width:300px"/>
                        </div>
                    </div>
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" name="doAdd" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-ok"></span>
                            <f:message key="all.login"/>
                        </button>
                    </div>
                </form>
            </sec:authorize>
            
            <sec:authorize url="/all">
                Welcome <b><sec:authentication property="principal.username" /></b>.
            </sec:authorize>
            
        </div>
    </s:layout-component>
</s:layout-render>