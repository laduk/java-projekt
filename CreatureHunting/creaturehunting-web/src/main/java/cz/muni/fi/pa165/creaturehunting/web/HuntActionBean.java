package cz.muni.fi.pa165.creaturehunting.web;

import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.CreatureService;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.HuntingExperienceService;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.WeaponService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HuntActionBean extends BaseActionBean implements ValidationErrorHandler{
    
    @SpringBean
    private HuntingExperienceService huntExpService;
    
    @SpringBean
    private WeaponService weaponService;
    
    @SpringBean
    private CreatureService creatureService;
    
    private List<HuntingExperienceDTO> huntExp;
    private List<WeaponDTO> weapons;
    private List<CreatureDTO> creatures;
    
    private WeaponDTO weaponDTO;
    private CreatureDTO creatureDTO;
    
    private String ids;
    private String ids2;    
    private String ar3;
    private String ar4;
    private Date date;
    private int effeciency;
    private String description;

    @ValidateNestedProperties(
            value = {
                    @Validate(on = {"createHuntExp", "editHuntExp"}, field = "efficiency", required = true, minvalue = 0, maxvalue = 100),
                    @Validate(on = {"createHuntExp", "editHuntExp"}, field = "description", required = true, maxlength = 300),                  
            }
    )
    private HuntingExperienceDTO huntExpDTO;
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"delete", "deleteHuntExp", "edit", "editHuntExp"})
    public void loadHuntExpFromDB() {         
        String ids3 = getContext().getRequest().getParameter("huntExp.id");
        if (ids3 == null) return;
        huntExpDTO = huntExpService.findHuntExp(Long.parseLong(ids3));
    }
    
    @Before(stages = LifecycleStage.EventHandling, on = {"createHuntExp","editHuntExp"})
    public void loadCreatureAndWeaponFromDB()
    {
        ids = getContext().getRequest().getParameter("WeaponID");
        ids2 = getContext().getRequest().getParameter("CreatureID");
        ar3 = getContext().getRequest().getParameter("huntExp.date");
        ar4 = getContext().getRequest().getParameter("huntExp.efficiency");
        description = getContext().getRequest().getParameter("huntExp.description");        

        if ((ids == null)||(ids2 == null)||(ar3 == null)||(ar4==null)) return;
        weaponDTO = weaponService.findWeapon(Long.parseLong(ids));
        creatureDTO = creatureService.findCreature(Long.parseLong(ids2));
        effeciency = Integer.parseInt(ar4);
        date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
        try {
            date = sdf.parse(ar3);
        } catch (ParseException ex) {
            Logger.getLogger(HuntActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<CreatureDTO> getCreatures(){
        return creatures;
    }
    
    public List<HuntingExperienceDTO> getHuntExp() {
        return huntExp;
    }
    
    public List<WeaponDTO> getWeapons(){
        return weapons;
    }
    
    public List<HuntingExperienceDTO> getAllHuntExp(){
        huntExp = huntExpService.findAllHuntExp();
        return huntExp;
    }
    
    public List<CreatureDTO> getAllCreatures(){
        creatures = creatureService.findAllCreatures();
        return creatures;
    }
    
    public List<WeaponDTO> getAllWeapons(){
        weapons = weaponService.findAllWeapons();
        return weapons;
    }
    
    public HuntingExperienceDTO getHuntingExperienceDTO(){
        return huntExpDTO;
    }
    
    public void setHuntingExperienceDTO(HuntingExperienceDTO huntExpDTO){
        this.huntExpDTO=huntExpDTO;
    }
    
    public CreatureDTO getCreatureDTO(){
        return creatureDTO;
    }
    
    public WeaponDTO getWeaponDTO(){
        return weaponDTO;
    }
    
    @DefaultHandler
    public Resolution list() {
        huntExp = huntExpService.findAllHuntExp();
        creatures = creatureService.findAllCreatures();
        weapons = weaponService.findAllWeapons();
        return new ForwardResolution("/HuntingExperience/list.jsp");
    }
    
    public Resolution create(){
        weapons = weaponService.findAllWeapons();
        creatures = creatureService.findAllCreatures();
        return new ForwardResolution("/HuntingExperience/create.jsp");
    }
    
    public Resolution createHuntExp(){
        huntExpDTO = new HuntingExperienceDTO();
        huntExpDTO.setCreature(creatureDTO);        
        huntExpDTO.setWeapon(weaponDTO);
        huntExpDTO.setDateOfExperience(date);
        huntExpDTO.setEfficiency(effeciency);
        huntExpDTO.setDescription(description); 
        
        huntExpService.create(huntExpDTO);
        getContext().getMessages().add(new LocalizableMessage("exp.create.done", escapeHTML(huntExpDTO.getDescription())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution edit(){
        weapons = weaponService.findAllWeapons();
        creatures = creatureService.findAllCreatures();
        return new ForwardResolution("/HuntingExperience/edit.jsp");
    }
    
    public Resolution editHuntExp(){
        huntExpDTO = new HuntingExperienceDTO();
        huntExpDTO.setCreature(creatureDTO);        
        huntExpDTO.setWeapon(weaponDTO);
        huntExpDTO.setDateOfExperience(date);
        huntExpDTO.setEfficiency(effeciency);
        huntExpDTO.setDescription(description); 
        
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
