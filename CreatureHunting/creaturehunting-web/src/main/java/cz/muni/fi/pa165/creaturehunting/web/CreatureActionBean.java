package cz.muni.fi.pa165.creaturehunting.web;

import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.CreatureService;
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

    //--- part for showing a list of creatures ---
    
    private List<CreatureDTO> creatures;

    @DefaultHandler
    public Resolution list() {
        creatures = creatureService.findAllCreatures();
        return new ForwardResolution("/creature/list.jsp");
    }

    public List<CreatureDTO> getCreatures() {
        return creatures;
    }

    //--- part for adding a creatures ----

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "height", minvalue = 0),
        @Validate(on = {"add", "save"}, field = "weight", minvalue = 0),
        @Validate(on = {"add", "save"}, field = "agility", minvalue = 0, maxvalue = 100)
    })
    private CreatureDTO creature;

    public Resolution add() {
        creatureService.create(creature);
        getContext().getMessages().add(new LocalizableMessage("book.add.message", escapeHTML(creature.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        creatures = creatureService.findAllCreatures();
        //return null to let the event handling continue
        return null;
    }

    public CreatureDTO getCreature() {
        return creature;
    }

    public void setCreature(CreatureDTO creature) {
        this.creature = creature;
    }

    //--- part for deleting a book ----

    public Resolution delete() {
        //only id is filled by the form
        creature = creatureService.findCreature(creature.getId());
        creatureService.delete(creature);
        getContext().getMessages().add(new LocalizableMessage("book.delete.message", escapeHTML(creature.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a book ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadCreatureFromDatabase() {
        String ids = getContext().getRequest().getParameter("creature.id");
        if (ids == null) return;
        creature = creatureService.findCreature(Long.parseLong(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/creature/edit.jsp");
    }

    public Resolution save() {
        creatureService.update(creature);
        return new RedirectResolution(this.getClass(), "list");
    }
}
