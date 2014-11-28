<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
    <div class="form-group">
        <s:label for="b1" name="creature.name" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b1" name="creature.name" class="form-control" style="width:300px" />
        </div>
    </div>
    <div class="form-group">
        <s:label for="b2" name="creature.height" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b2" name="creature.height" size="4" class="form-control" style="width:100px"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="b3" name="creature.weight" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b3" name="creature.weight" size="4" class="form-control" style="width:100px"/>
        </div>
    </div>
    <div class="form-group">
        <s:label for="b4" name="creature.agility" class="control-label col-sm-2"/>
        <div class="col-sm-10">
            <s:text id="b4" name="creature.agility" size="4" class="form-control"  style="width:50px"/>
        </div>
    </div>
</table>