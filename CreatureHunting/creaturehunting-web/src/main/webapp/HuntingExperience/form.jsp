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
        <s:label for="b1" name="HuntExpWeapon" class="col-sm-2 control-label">
            <f:message key="exp.weapon"/>
        </s:label>
        <div class="col-sm-4">
            <s:select class="form-control" id="b1" name="WeaponID">
                <s:options-collection collection="${actionBean.weapons}" value="id" label="name"/>
            </s:select>
        </div>
    </div>

    <div class="form-group">
        <s:label for="b2" name="HuntExpCreature" class="col-sm-2 control-label">
            <f:message key="exp.creature"/>
        </s:label>
        <div class="col-sm-4">
            <s:select class="form-control" id="b2" name="WeaponID">
                <s:options-collection collection="${actionBean.creatures}" value="id" label="name"/>
            </s:select>
        </div>
    </div>

    <div class="form-group">
        <s:label for="b3" name="HuntExpDate" class="col-sm-2 control-label">
            <f:message key="exp.dateOfExperience"/>
        </s:label>
        <div class="col-sm-4">
            <s:text class="form-control" id="datepicker" name="huntExp.date"/>
        </div>
    </div>

    <div class="form-group">
        <s:label for="b4" name="HuntExpDate" class="col-sm-2 control-label">
            <f:message key="exp.efficiency"/>
        </s:label>
        <div class="col-sm-4">
            <s:text class="form-control" id="b4" name="huntExp.efficiency"/>
        </div>
    </div>
        
    <div class="form-group">
        <s:label for="b5" name="HuntExpDate" class="col-sm-2 control-label">
            <f:message key="exp.description"/>
        </s:label>
        <div class="col-sm-4">
            <s:text class="form-control" id="b5" name="huntExp.description"/>
        </div>
    </div>
