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
@UrlBinding("/huntexperince/{$event}/{huntExp.id}")
public class HuntExpActionBean extends BaseActionBean implements ValidationErrorHandler{
    
    @SpringBean
    private HuntingExperienceService huntExpService;
    
    @SpringBean
    private WeaponService weaponService;
    
    @SpringBean
    private CreatureService creatureService;
    
    private List<HuntingExperienceDTO> huntExp;
    
    private Long weaponId;
    private Long creatureId;

    @ValidateNestedProperties(
            value = {
                    @Validate(on = {"createHuntExp", "editHuntExp"}, field = "efficiency", required = true, minvalue = 0, maxvalue = 100),
                    @Validate(on = {"createHuntExp", "editHuntExp"}, field = "description", required = true, maxlength = 300),                  
            }
    )
    private HuntingExperienceDTO huntExpDTO;
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete", "deleteHuntExp", "edit", "editHuntExp"})
    public void loadHuntExpFromDB() {
        String ids = getContext().getRequest().getParameter("huntExp.id");
        if (ids == null) return;
        huntExpDTO = huntExpService.findHuntExp(Long.parseLong(ids));
    }
    
    public void setWeapon(Long weaponId) {
        this.weaponId = weaponId;
    }

    public void setCreature(Long creatureId) {
        this.creatureId = creatureId;
    }    
    
    public List<CreatureDTO> getAllCreatures(){
        return creatureService.findAllCreatures();
    }
    
    public List<HuntingExperienceDTO> getHuntingExp() {
        return huntExp;
    }
    
    public List<WeaponDTO> getAllWeapons(){
        return weaponService.findAllWeapons();
    }
    
    public List<HuntingExperienceDTO> getAllHuntExp(){
        huntExp = huntExpService.findAllHuntExp();
        return huntExp;
    }
    
    public HuntingExperienceDTO getHuntingExperienceDTO(){
        return huntExpDTO;
    }
    
    public void setHuntingExperienceDTO(HuntingExperienceDTO huntExpDTO){
        this.huntExpDTO=huntExpDTO;
    }

    @DefaultHandler
    public Resolution list() {
        huntExp = huntExpService.findAllHuntExp();
        return new ForwardResolution("/HuntingExperience/list.jsp");
    }
    
    public Resolution create(){
        return new ForwardResolution("/HuntingExperience/create.jsp");
    }
    
    public Resolution createHuntExp(){
        CreatureDTO creatureDTO = creatureService.findCreature(creatureId);
        huntExpDTO.setCreature(creatureDTO);
        
        WeaponDTO weaponDTO = weaponService.findWeapon(weaponId);
        huntExpDTO.setWeapon(weaponDTO);
        
        huntExpService.create(huntExpDTO);
        getContext().getMessages().add(new LocalizableMessage("exp.create.done", escapeHTML(huntExpDTO.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution edit(){
        return new ForwardResolution("/HuntingExperience/edit.jsp");
    }
    
    public Resolution editHuntExp(){
        CreatureDTO creatureDTO = creatureService.findCreature(creatureId);
        huntExpDTO.setCreature(creatureDTO);
        
        WeaponDTO weaponDTO = weaponService.findWeapon(weaponId);
        huntExpDTO.setWeapon(weaponDTO);
        
        huntExpService.update(huntExpDTO);
        getContext().getMessages().add(new LocalizableMessage("exp.edit.done", escapeHTML(huntExpDTO.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete(){
        return new ForwardResolution("/HuntingExperience/delete.jsp");
    }            
    
    public Resolution deleteHuntExp(){
        huntExpService.delete(huntExpDTO);
        getContext().getMessages().add(new LocalizableMessage("exp.delete.done", escapeHTML(huntExpDTO.getDescription())));
        return new RedirectResolution(this.getClass(), "list");        
    }
    
    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        huntExp = huntExpService.findAllHuntExp();
        //return null to let the event handling continue
        return null;
    }
    
}
