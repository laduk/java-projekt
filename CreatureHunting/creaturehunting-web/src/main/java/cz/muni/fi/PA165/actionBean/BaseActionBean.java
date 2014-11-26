package cz.muni.fi.PA165.actionBean;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.taglibs.standard.functions.Functions;

/**
 *
 * @author Fita
 */
public class BaseActionBean implements ActionBean{
    
    private ActionBeanContext context;
    

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    /**
     *
     * @param s
     * @return
     */
    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }
}
