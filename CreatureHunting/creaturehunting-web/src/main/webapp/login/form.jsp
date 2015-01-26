<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
    <div class="form-group">
        <s:label for="b1" name="login.name" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b1" name="login.name" class="form-control" style="width:300px" />
        </div>
    </div>
    <div class="form-group">
        <s:label for="b2" name="login.password" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b2" name="login.password" size="4" class="form-control" style="width:300px"/>
        </div>
    </div>
        <div class="form-group">
        <s:label for="b3" name="login.role" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:select class="form-control" id="b3" name="login.role" style="width:100px">
               <s:option value="ROLE_ADMIN" label="Admin"/>
               <s:option value="ROLE_SURVIVOR" label="Survivor"/>
               <s:option value="ROLE_REST" label="Rest"/>
           </s:select>
            
        </div>
    </div>
        

