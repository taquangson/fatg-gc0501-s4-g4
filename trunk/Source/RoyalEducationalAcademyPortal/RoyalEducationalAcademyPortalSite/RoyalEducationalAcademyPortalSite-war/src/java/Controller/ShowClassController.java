/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Session.ClassFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name="ShowClassController")
@RequestScoped
public class ShowClassController {
    @EJB
    private ClassFacade classFacade;

    /** Creates a new instance of ShowClassController */
    public ShowClassController() {
    }
    public List<Entity.Class> SHOWALL(){
        return classFacade.findAll();
    }
}
