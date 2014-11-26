<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
       <ul>
           <li>
               <s:link beanclass="cz.muni.fi.PA165.actionBean.HuntExpActionBean">
                   <f:message key="index.creatureHunt"/>
               </s:link>
           </li>
       </ul>
    </s:layout-component>
</s:layout-render>
