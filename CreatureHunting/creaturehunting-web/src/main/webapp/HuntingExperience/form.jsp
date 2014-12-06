<%-- 
    Document   : form
    Created on : 28.11.2014, 15:17:37
    Author     : Fita
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:errors/>

    <div class="form-group">
        <s:label for="b1" name="exp.weapon" class="col-sm-2 control-label"/>
        <div class="col-sm-4">
            <s:select class="form-control" id="b1" name="hunting.weapon.id">
                <s:options-collection collection="${actionBean.weapons}" value="id" label="name"/>
            </s:select>
        </div>
    </div>

    <div class="form-group">
        <s:label for="b2" name="exp.creature" class="col-sm-2 control-label"/>
        <div class="col-sm-4">
            <s:select class="form-control" id="b2" name="hunting.creature.id">
                <s:options-collection collection="${actionBean.creatures}" value="id" label="name"/>
            </s:select>
        </div>
    </div>

    <div class="form-group">
        <s:label for="datepicker" name="exp.dateOfExperience" class="col-sm-2 control-label"/>
        <div class="col-sm-4">
            <s:text class="form-control" id="datepicker" name="hunting.date"/>
        </div>
    </div>

    <div class="form-group">
        <s:label for="b4" name="exp.efficiency" class="col-sm-2 control-label">
            <f:message key="exp.efficiency"/>
        </s:label>
        <div class="col-sm-4">
            <s:text class="form-control" id="b4" name="hunting.efficiency"/>
        </div>
    </div>
        
    <div class="form-group">
        <s:label for="b5" name="exp.description" class="col-sm-2 control-label"/>
        <div class="col-sm-4">
            <s:text id="b5" name="hunting.description" class="form-control" style="width:360px" />
        </div>
    </div>
