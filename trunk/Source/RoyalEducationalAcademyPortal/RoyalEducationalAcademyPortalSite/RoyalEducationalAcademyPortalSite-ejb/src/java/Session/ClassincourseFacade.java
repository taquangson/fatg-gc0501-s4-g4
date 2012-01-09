/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Classincourse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class ClassincourseFacade extends AbstractFacade<Classincourse> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassincourseFacade() {
        super(Classincourse.class);
    }
    
}
