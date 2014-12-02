<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="creature.show.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" var="actionBean"/>

        <div class="container">
            <h2><f:message key="creature.show.title"/></h2>

            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.CreatureActionBean" class="form-inline">
                <fieldset>
                    <div class="form-group">
                        <s:label for="b1" name="creature.name" class="control-label col-sm-2"/>
                        <div class="col-sm-10">
                            <s:text id="b1" name="creature.name" class="form-control"/>
                        </div>
                    </div>
                    <button type="submit" name="doAdd" class="btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-search"></span>
                        <f:message key="all.find"/>
                    </button>
                </fieldset>
            </s:form>
            
            <table class="table">
                <tr>
                    <th><f:message key="creature.name"/></th>
                    <td><c:out value="${actionBean.creature.name}"/></td>
                </tr>
                <tr>
                    <th><f:message key="creature.height"/></th>
                    <td><c:out value="${actionBean.creature.height}"/></td>
                </tr>
                <tr>
                    <th><f:message key="creature.weight"/></th>
                    <td><c:out value="${actionBean.creature.weight}"/></td>
                </tr>
                <tr>
                    <th><f:message key="creature.agility"/></th>
                    <td><c:out value="${actionBean.creature.agility}"/></td>
                </tr>
            </table>
        </div>
    </s:layout-component>
</s:layout-render>