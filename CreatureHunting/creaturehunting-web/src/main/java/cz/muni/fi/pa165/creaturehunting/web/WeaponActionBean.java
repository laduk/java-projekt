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
@UrlBinding("/weapons/{$event}/{weapon.id}")
public class WeaponActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    private WeaponService weaponService;
    private List<WeaponDTO> weapons;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"doAdd", "doSave"}, field = "name", required = true),
        @Validate(on = {"doAdd", "doSave"}, field = "gunReach", minvalue = 0, required = true),
        @Validate(on = {"doAdd", "doSave"}, field = "ammunition", required = true)
    })
    private WeaponDTO weapon;

    public List<WeaponDTO> getWeapons() {
        return weapons;
    }

    public WeaponDTO getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponDTO weapon) {
        this.weapon = weapon;
    }

    public void setWeapons(List<WeaponDTO> weapons) {
        this.weapons = weapons;
    }

    @DefaultHandler
    public Resolution list() {
        String name = getContext().getRequest().getParameter("find");
        if (name == null || name.isEmpty()) {
            weapons = weaponService.findAllWeapons();
        } else {
            weapons = weaponService.findAllByName(name);
        }
        return new ForwardResolution("/weapon/list.jsp");
    }

    public Resolution add() {
        return new ForwardResolution("/weapon/add.jsp");
    }

    public Resolution doAdd() {
        weaponService.create(weapon);
        getContext().getMessages().add(new LocalizableMessage("weapon.add.done", escapeHTML(weapon.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        weapons = weaponService.findAllWeapons();
        return null;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "doEdit", "delete", "doDelete"})
    public void loadWeaponFromDatabase() {
        String identification = getContext().getRequest().getParameter("weapon.id");
        if (identification == null) {
            return;
        }
        weapon = weaponService.findWeapon(Long.parseLong(identification));
    }

    public Resolution delete() {
        return new ForwardResolution("/weapon/delete.jsp");
    }

    public Resolution doDelete() {

        weapon = weaponService.findWeapon(weapon.getId());
        weaponService.delete(weapon);
        getContext().getMessages().add(new LocalizableMessage("weapon.delete.done", escapeHTML(weapon.getName())));
        return new RedirectResolution(this.getClass(), "list");

    }

    public Resolution edit() {
        return new ForwardResolution("/weapon/edit.jsp");
    }

    public Resolution doEdit() {
        weaponService.update(weapon);
        getContext().getMessages().add(new LocalizableMessage("weapon.edit.done", escapeHTML(weapon.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
}

