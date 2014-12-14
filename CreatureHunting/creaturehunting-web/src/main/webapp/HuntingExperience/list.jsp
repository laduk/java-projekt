<%-- 
    Document   : list
    Created on : 28.11.2014, 15:01:46
    Author     : Fita
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="exp.list.title" activeHuntingTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" var="actionBean"/>

        <div class="container">

            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" class="form-inline text-right">

                <p>
                <div class="form-group">
                    <s:label for="b1" name="exp.creature" class="col-sm-2 control-label"/>
                    <div class="col-sm-10">
                        <s:select class="form-control" id="b1" name="hunting.creature.id" style="width:250px" >
                            <s:option value="null"/>
                            <s:options-collection collection="${actionBean.creatures}" value="id" label="name"/>
                        </s:select>
                    </div>
                </div></p><p>
              
            <div class="form-group">
                <s:label for="b2" name="exp.weapon.efficiency"/>
                <s:text id="b2" name="findWepEff" class="form-control"/> %
            </div></p><p>

            <button type="submit" name="list" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-search"></span>
                <f:message key="all.find"/>
            </button></p>
        </s:form>


    <h2><f:message key="exp.list.title"/></h2>

    <table class="table">
        <tr>
            <th>id</th>
            <th><f:message key="exp.weapon"/></th>
            <th><f:message key="exp.creature"/></th>
            <th><f:message key="exp.dateOfExperienceR"/></th>
            <th><f:message key="exp.efficiency"/></th>
            <th><f:message key="exp.description"/></th>
            <th></th>
        </tr>
        <c:forEach items="${actionBean.huntings}" var="hunting">
            <tr>
                <td>${hunting.id}</td>
                <td><c:out value="${hunting.weapon.name}"/></td>
                <td><c:out value="${hunting.creature.name}"/></td>
                <td><c:out value="${hunting.dateOfExperience}"/></td>
                <td><c:out value="${hunting.efficiency}"/></td>
                <td><c:out value="${hunting.description}"/></td>
                <td>
                    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" event="edit">
                        <s:param name="hunting.id" value="${hunting.id}"/>
                        <span class="glyphicon glyphicon-edit"></span>
                        <f:message key="all.edit" />
                    </s:link>
                    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" event="delete">
                        <s:param name="hunting.id" value="${hunting.id}"/>
                        <span class="glyphicon glyphicon-remove"></span>
                        <f:message key="all.delete"/>
                    </s:link>
                </td>
            </tr>
        </c:forEach>
    </table>
    <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" event="create">
        <span class="glyphicon glyphicon-plus"></span>
        <f:message key="exp.create.title"/>
    </s:link>
</div>
</s:layout-component>
</s:layout-render>