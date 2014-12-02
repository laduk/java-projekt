package cz.muni.fi.pa165.creaturehunting.web;

import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.CreatureService;
import static cz.muni.fi.pa165.creaturehunting.web.BaseActionBean.escapeHTML;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

import java.util.List;

/**
 * Stripes ActionBean for handling creature operations.
 *
 * @author Radoslav Zajonc
 */
@UrlBinding("/creatures/{$event}/{creature.id}")
public class CreatureActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected CreatureService creatureService;
    
    private List<CreatureDTO> creatures;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"doAdd", "doEdit"}, field = "name", required = true),
        @Validate(on = {"doAdd", "doEdit"}, field = "height", minvalue = 0),
        @Validate(on = {"doAdd", "doEdit"}, field = "weight", minvalue = 0),
        @Validate(on = {"doAdd", "doEdit"}, field = "agility", minvalue = 0, maxvalue = 100)
    })
    private CreatureDTO creature;

    public List<CreatureDTO> getCreatures() {
        return creatures;
    }
    
    public CreatureDTO getCreature() {
        return creature;
    }

    public void setCreature(CreatureDTO creature) {
        this.creature = creature;
    }
    
    @DefaultHandler
    public Resolution list() {
        String ids = getContext().getRequest().getParameter("creature.id");
        if (ids == null) {
            creatures = creatureService.findAllCreatures();
        } else {
            creatures = creatureService.findAllCreaturesByName(ids);
        }
        return new ForwardResolution("/creature/list.jsp");
    }
    
    public Resolution add() {
        return new ForwardResolution("/creature/add.jsp");
    }

    public Resolution doAdd() {
        creatureService.create(creature);
        getContext().getMessages().add(new LocalizableMessage("creature.add.done", escapeHTML(creature.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        creatures = creatureService.findAllCreatures();
        //return null to let the event handling continue
        return null;
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete", "doDelete", "edit", "doEdit"})
    public void loadCreatureFromDatabase() {
        String ids = getContext().getRequest().getParameter("creature.id");
        if (ids == null) return;
        creature = creatureService.findCreature(Long.parseLong(ids));
    }

    public Resolution delete() {
        return new ForwardResolution("/creature/delete.jsp");
    }
    
    public Resolution doDelete() {
        creatureService.delete(creature);
        getContext().getMessages().add(new LocalizableMessage("creature.delete.done", escapeHTML(creature.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution edit() {
        return new ForwardResolution("/creature/edit.jsp");
    }

    public Resolution doEdit() {
        creatureService.update(creature);
        getContext().getMessages().add(new LocalizableMessage("creature.edit.done", escapeHTML(creature.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution show() {
        String ids = getContext().getRequest().getParameter("creature.id");
        creature = creatureService.findCreature(Long.parseLong(ids));
        return new ForwardResolution("/creature/show.jsp");
    }
}
