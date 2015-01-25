<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:layout-render name="/layout.jsp" titlekey="login.title">
    <s:layout-component name="body">
        <f:message key="login.login" var="login"/>
        <fieldset>
            <legend><f:message key="login.loginfo"/></legend>
            <form name="f" action="<s:url value='j_spring_security_check'/>" method="POST">
                <table>
                    <tr>
                        <td><f:message key="login.username"/></td>
                        <td><input type='text' name='j_username' /></td>
                    </tr>
                    <tr>
                        <td><f:message key="login.password"/></td>
                        <td><input type='password' name='j_password'></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan='2'><input class="button" value="${login}" name="submit" type="submit"></td>
                    </tr>
                </table>
            </form>
        </fieldset>
    </s:layout-component>
</s:layout-render>
