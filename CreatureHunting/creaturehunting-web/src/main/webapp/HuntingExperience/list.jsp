<%-- 
    Document   : list
    Created on : 28.11.2014, 15:01:46
    Author     : Fita
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="hunting.list.title" activeHuntingTab="active">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" var="actionBean"/>

        <div class="container">

            <s:errors/>
            <s:form beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" class="form-inline text-right">
                <div class="form-group">
                    <s:label for="b1" name="hunting.creature.id"/>
                    <s:select class="form-control" id="b1" name="hunting.creature.id">
                        <s:option value=""/>
                        <s:options-collection collection="${actionBean.creatures}" value="id" label="name"/>
                    </s:select>
                </div>
                <div class="form-group" style="margin-left:20px">
                    <s:label for="b2" name="hunting.weapon.efficiency"/>
                    <s:text id="b2" name="hunting.efficiency" class="form-control" style="width:50px"/> %
                </div>
                <button type="submit" name="doFind" class="btn btn-default btn-sm" style="margin-left:20px">
                    <span class="glyphicon glyphicon-search"></span>
                    <f:message key="all.find"/>
                </button>
            </s:form>

            <h2><f:message key="hunting.list.title"/></h2>
    
            <c:choose>
                <c:when test="${actionBean.huntings != null && actionBean.huntings.size() > 0}">
                    <table class="table">
                        <tr>
                            <th>id</th>
                            <th><f:message key="hunting.weapon.id"/></th>
                            <th><f:message key="hunting.creature.id"/></th>
                            <th><f:message key="hunting.dateOfExperienceR"/></th>
                            <th><f:message key="hunting.efficiency"/></th>
                            <th><f:message key="hunting.description"/></th>
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
                                    <sec:authorize url="/admin">
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
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h6><f:message key="hunting.list.empty"/></h6>
                </c:otherwise>
            </c:choose>
                    
            <p>
                <s:link beanclass="cz.muni.fi.pa165.creaturehunting.web.HuntActionBean" event="create">
                    <span class="glyphicon glyphicon-plus"></span>
                    <f:message key="hunting.create.title"/>
                </s:link>
            </p>
        </div>
    </s:layout-component>
</s:layout-render>