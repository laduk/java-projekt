package cz.muni.fi.pa165.creaturehunting.web;

import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.CreatureService;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.HuntingExperienceService;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.WeaponService;
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
 *
 * @author Fita
 */
@UrlBinding("/huntexperince/{$event}/{hunting.id}")
public class HuntActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    private HuntingExperienceService huntingService;
    @SpringBean
    private WeaponService weaponService;
    @SpringBean
    private CreatureService creatureService;
    private List<HuntingExperienceDTO> huntings;
    @ValidateNestedProperties(value = {
        @Validate(on = {"doCreate", "doEdit"}, field = "weapon.id", required = true),
        @Validate(on = {"doCreate", "doEdit", "doFind"}, field = "creature.id", required = true),
        @Validate(on = {"doCreate", "doEdit", "doFind"}, field = "efficiency", required = true, minvalue = 0, maxvalue = 100),
        @Validate(on = {"doCreate", "doEdit"}, field = "description", required = true, maxlength = 300)})
    private HuntingExperienceDTO hunting;

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete", "doDelete", "edit", "doEdit"})
    public void loadHuntExpFromDB() {
        String ids = getContext().getRequest().getParameter("hunting.id");
        if (ids == null) {
            return;
        }
        hunting = huntingService.findHuntExp(Long.parseLong(ids));
    }
    
    public List<HuntingExperienceDTO> getHuntings() {
        return huntings;
    }

    public void setHuntings(List<HuntingExperienceDTO> huntings) {
        this.huntings = huntings;
    }

    public List<WeaponDTO> getWeapons() {
        return weaponService.findAllWeapons();
    }

    public List<CreatureDTO> getCreatures() {
        return creatureService.findAllCreatures();
    }

    public HuntingExperienceDTO getHunting() {
        return hunting;
    }

    public void setHunting(HuntingExperienceDTO hunting) {
        this.hunting = hunting;
    }

    @DefaultHandler
    public Resolution list() {
        huntings = huntingService.findAllHuntExp();
        return new ForwardResolution("/HuntingExperience/list.jsp");
    }
    
    public Resolution doFind() {
        huntings = huntingService.findEfficientWeaponExperiences(hunting.getCreature(), hunting.getEfficiency());
        return new ForwardResolution("/HuntingExperience/list.jsp");
    }

    public Resolution create() {
        return new ForwardResolution("/HuntingExperience/create.jsp");
    }

    public Resolution doCreate() {
        huntingService.create(hunting);
        getContext().getMessages().add(new LocalizableMessage("hunting.create.done", escapeHTML(hunting.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution edit() {
        return new ForwardResolution("/HuntingExperience/edit.jsp");
    }

    public Resolution doEdit() {
        huntingService.update(hunting);
        getContext().getMessages().add(new LocalizableMessage("hunting.edit.done", escapeHTML(hunting.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        return new ForwardResolution("/HuntingExperience/delete.jsp");
    }

    public Resolution doDelete() {
        huntingService.delete(hunting);
        getContext().getMessages().add(new LocalizableMessage("hunting.delete.done", escapeHTML(hunting.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        huntings = huntingService.findAllHuntExp();
        //return null to let the event handling continue
        return null;
    }
}
