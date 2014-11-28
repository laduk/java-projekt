/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.web;


import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.WeaponService;
import java.util.List;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;


/**
 *
 * @author lada
 */
@UrlBinding("/weapon/{$event}/{weapon.id}")
public class WeaponActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    private WeaponService weaponService;
    private List<WeaponDTO> weapons;
    private WeaponDTO weapon;

    @ValidateNestedProperties(
            value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "gunReach", minvalue = 0, required = true),
        @Validate(on = {"add", "save"}, field = "ammunition", required = true)
    })
    public List<WeaponDTO> getWeapons() {
        return weapons;
    }

    public WeaponDTO getWeapon() {
        return weapon;
    }

    public void setWeapons(List<WeaponDTO> weapons) {
        this.weapons = weapons;
    }

    @DefaultHandler
    public Resolution list() {
        weapons = weaponService.findAllWeapons();
        return new ForwardResolution("/weapon/list.jsp");
    }

    public Resolution add() {
        weaponService.create(weapon);
        getContext().getMessages().add(new LocalizableMessage("Cosi", escapeHTML(weapon.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {

        weapon = weaponService.findWeapon(weapon.getId());
        weaponService.delete(weapon);
        getContext().getMessages().add(new LocalizableMessage("Cosi",escapeHTML(weapon.getName())));
        return new RedirectResolution(this.getClass(), "list");

    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        weapons = weaponService.findAllWeapons();
        return null;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadCreatureFromDatabase() {
        String identification = getContext().getRequest().getParameter("weapon.id");
        if (identification == null) {
            return;
        }
        weapon = weaponService.findWeapon(Long.parseLong(identification));
    }

    public Resolution edit() {
        return new ForwardResolution("/weapon/edit.jsp");
    }

    public Resolution save() {
        weaponService.update(weapon);
        return new RedirectResolution(this.getClass(), "list");
    }
}
