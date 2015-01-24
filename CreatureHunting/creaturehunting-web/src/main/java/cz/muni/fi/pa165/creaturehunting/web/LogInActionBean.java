package cz.muni.fi.pa165.creaturehunting.web;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.LogInService;
import java.util.ArrayList;
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
 *
 * @author Fita
 */
@UrlBinding("/loggingin/{$event}/{login.id}")
public class LogInActionBean extends BaseActionBean implements ValidationErrorHandler{
    
    @SpringBean
    protected LogInService service;

    private List<LogInDTO> logins;
    
    
    @DefaultHandler
    public Resolution list() {        
        String name = getContext().getRequest().getParameter("find");
        if (name == null || name.isEmpty()) {
            logins = service.findAllLogIns();
        } else {
            logins = new ArrayList<LogInDTO>();
            logins.add(service.findByName(name));
        }
        return new ForwardResolution("/login/list.jsp");
    }
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"doAdd", "doEdit"}, field = "name", required = true, maxlength = 50),
        @Validate(on = {"doAdd", "doEdit"}, field = "password", required = true, minlength = 8, maxlength = 256)
    })
    private LogInDTO login;
        
    public Resolution add() {
        return new ForwardResolution("/login/add.jsp");
    }
        
    public Resolution doAdd() {
        if (service.findByName(login.getName())==null) {
            service.create(login);
            getContext().getMessages().add(new LocalizableMessage("login.add.done",
                    escapeHTML(login.getName())));
            return new RedirectResolution(this.getClass(), "list");
        }else{
            getContext().getMessages().add(new LocalizableMessage("login.add.fail",
                    escapeHTML(login.getName())));
            return new RedirectResolution(this.getClass(), "list");
        }       
    }
    
    public LogInDTO getLogin() {
        return login;
    }
    
    public void setLogin(LogInDTO login) {
        this.login = login;
    }
    
    public List<LogInDTO> getLogins(){
        return service.findAllLogIns();
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete", "doDelete", "edit", "doEdit"})
    public void loadLoginFromDatabase() {
        String ids = getContext().getRequest().getParameter("login.id");
        if (ids == null) return;
        login = service.findLogIn(Long.parseLong(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/area/edit.jsp");
    }

    public Resolution doEdit() {
        service.update(login);
        getContext().getMessages().add(new LocalizableMessage("login.edit.done", escapeHTML(login.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        return new ForwardResolution("/login/delete.jsp");
    }
    
    public Resolution doDelete() {
        service.delete(login);
        getContext().getMessages().add(new LocalizableMessage("login.delete.done", escapeHTML(login.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
        
    @Override
    public Resolution handleValidationErrors(ValidationErrors validationErrors) throws Exception {
        //fill up the data for the table if validation errors occured
        logins = service.findAllLogIns();
        //return null to let the event handling continue
        return null;
    }
    
}
