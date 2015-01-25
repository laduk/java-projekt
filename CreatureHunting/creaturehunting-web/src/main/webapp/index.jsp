<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
                <form name="f" action="<s:url value='j_spring_security_check'/>" class="form-horizontal">
                    <div class="form-group">
                        <s:label for="b1" name="login.username" class="control-label col-sm-2"/>
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
            
        </div>
    </s:layout-component>
</s:layout-render>