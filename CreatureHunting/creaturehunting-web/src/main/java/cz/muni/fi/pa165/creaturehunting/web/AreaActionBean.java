package cz.muni.fi.pa165.creaturehunting.web;

import cz.muni.fi.pa165.creaturehunting.api.dto.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.AreaService;
import static cz.muni.fi.pa165.creaturehunting.web.BaseActionBean.escapeHTML;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * Stripes ActionBean for handling area operations.
 * 
 * @author Matej Čižik
 */
@UrlBinding("/areas/{$event}/{area.id}")
public class AreaActionBean extends BaseActionBean implements ValidationErrorHandler {
    
    @SpringBean
    protected AreaService areaService;

    //--- part for showing a list of areas ---
    
    private List<AreaDTO> areas;

    @DefaultHandler
    public Resolution list() {        
        String name = getContext().getRequest().getParameter("find");
        if (name == null || name.isEmpty()) {
            areas = areaService.findAllAreas();
        } else {
            areas = areaService.findAllByName(name);
        }
        return new ForwardResolution("/area/list.jsp");
    }

    public List<AreaDTO> getAreas() {
        return areas;
    }

    //--- part for adding a areas ----

    @ValidateNestedProperties(value = {
        @Validate(on = {"doAdd", "doEdit"}, field = "name", required = true),
        @Validate(on = {"doAdd", "doEdit"}, field = "description"),
        @Validate(on = {"doAdd", "doEdit"}, field = "acreage", minvalue = 0),
    })
    private AreaDTO area;
    
    public Resolution add() {
        return new ForwardResolution("/area/add.jsp");
    }

    public Resolution doAdd() {
        areaService.create(area);
        getContext().getMessages().add(new LocalizableMessage("area.add.done", escapeHTML(area.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        areas = areaService.findAllAreas();
        //return null to let the event handling continue
        return null;
    }

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }

    //--- part for deleting a area ----

    public Resolution delete() {
        return new ForwardResolution("/area/delete.jsp");
    }
    
    public Resolution doDelete() {
        areaService.delete(area);
        getContext().getMessages().add(new LocalizableMessage("area.delete.done", escapeHTML(area.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a area ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete", "doDelete", "edit", "doEdit"})
    public void loadAreaFromDatabase() {
        String ids = getContext().getRequest().getParameter("area.id");
        if (ids == null) return;
        area = areaService.findArea(Long.parseLong(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/area/edit.jsp");
    }

    public Resolution doEdit() {
        areaService.update(area);
        getContext().getMessages().add(new LocalizableMessage("area.edit.done", escapeHTML(area.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
}
