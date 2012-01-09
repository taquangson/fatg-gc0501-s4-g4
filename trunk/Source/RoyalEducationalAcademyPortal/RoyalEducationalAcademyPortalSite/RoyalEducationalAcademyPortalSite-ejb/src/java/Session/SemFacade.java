/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Sem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class SemFacade extends AbstractFacade<Sem> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public SemFacade() {
        super(Sem.class);
    }
    
}
