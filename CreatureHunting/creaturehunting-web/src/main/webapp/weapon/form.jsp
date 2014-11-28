<%-- 
    Document   : form
    Created on : Nov 28, 2014, 7:19:07 PM
    Author     : lada
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
    <div class="form-group">
        <s:label for="b1" name="weapon.name" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b1" name="weapon.name" class="form-control" style="width:300px" />
        </div>
    </div>
    <div class="form-group">
        <s:label for="b2" name="weapon.gunreach" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b2" name="weapon.gunreach" class="form-control" style="width:300px"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="b3" name="weapon.ammunition" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b3" name="weapon.ammunition" class="form-control" style="width:300px"/>
        </div>
    </div>
   
</table>