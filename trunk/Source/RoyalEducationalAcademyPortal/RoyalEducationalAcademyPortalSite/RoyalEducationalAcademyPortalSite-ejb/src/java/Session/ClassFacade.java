/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Class;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class ClassFacade extends AbstractFacade<Class> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassFacade() {
        super(Class.class);
    }    
}
