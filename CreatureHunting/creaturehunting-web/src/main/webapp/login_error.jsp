<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <fieldset>
            <legend><f:message key="login"/></legend>
            <div class="error_container">
            <div>
            <img class="error_img" src="${pageContext.request.contextPath}/img/error_img.png" alt="Error">               
            </div>
            Invalid user name or password please try again.
            </div>
            <br>
            <br>
            <form name="f" action="<s:url value='j_spring_security_check'/>" method="POST">
                <table>
                    <tr>
                        <td><f:message key="username"/></td>
                        <td><input type='text' name='j_username' /></td>
                    </tr>
                    <tr>
                        <td><f:message key="password"/></td>
                        <td><input type='password' name='j_password'></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit">&nbsp;<input name="reset" type="reset"></td>
                    </tr>
                </table>
            </form>
        </fieldset>
    </s:layout-component>
</s:layout-render>
