package cz.muni.fi.pa165.creaturehunting.web;

import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.CreatureService;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.HuntingExperienceService;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.WeaponService;
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
public class HuntActionBean extends BaseActionBean implements ValidationErrorHandler{
    
    @SpringBean
    private HuntingExperienceService huntingService;
    
    @SpringBean
    private WeaponService weaponService;
    
    @SpringBean
    private CreatureService creatureService;
    
    private List<HuntingExperienceDTO> huntings;
    private List<WeaponDTO> weapons;
    private List<CreatureDTO> creatures;
    
    private WeaponDTO weapon;
    private CreatureDTO creature;

    @ValidateNestedProperties(value = {
        @Validate(on = {"doCreate", "doEdit"}, field = "efficiency", required = true, minvalue = 0, maxvalue = 100),
        @Validate(on = {"doCreate", "doEdit"}, field = "description", required = true, maxlength = 300),                  
    })
    private HuntingExperienceDTO hunting;
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete", "doDelete", "edit", "doEdit"})
    public void loadHuntExpFromDB() {         
        String ids = getContext().getRequest().getParameter("hunting.id");
        if (ids == null) return;
        hunting = huntingService.findHuntExp(Long.parseLong(ids));
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"create", "doCreate", "edit", "doEdit"})
    public void loadWeaponsAndCreaturesFromDB() {         
        creatures = creatureService.findAllCreatures();
        weapons = weaponService.findAllWeapons();
    }

    public List<HuntingExperienceDTO> getHuntings() {
        return huntings;
    }

    public void setHuntings(List<HuntingExperienceDTO> huntings) {
        this.huntings = huntings;
    }

    public List<WeaponDTO> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponDTO> weapons) {
        this.weapons = weapons;
    }

    public List<CreatureDTO> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<CreatureDTO> creatures) {
        this.creatures = creatures;
    }

    public WeaponDTO getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponDTO weapon) {
        this.weapon = weapon;
    }

    public CreatureDTO getCreature() {
        return creature;
    }

    public void setCreature(CreatureDTO creature) {
        this.creature = creature;
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
    
    public Resolution create() {
        return new ForwardResolution("/HuntingExperience/create.jsp");
    }
    
    public Resolution doCreate() {
        huntingService.create(hunting);
        getContext().getMessages().add(new LocalizableMessage("exp.create.done", escapeHTML(hunting.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution edit() {
        return new ForwardResolution("/HuntingExperience/edit.jsp");
    }
    
    public Resolution doEdit() {
        huntingService.update(hunting);
        getContext().getMessages().add(new LocalizableMessage("exp.edit.done", escapeHTML(hunting.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        return new ForwardResolution("/HuntingExperience/delete.jsp");
    }            
    
    public Resolution doDelete() {
        huntingService.delete(hunting);
        getContext().getMessages().add(new LocalizableMessage("exp.delete.done", escapeHTML(hunting.getDescription())));
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
