/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Markassiment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class MarkassimentFacade extends AbstractFacade<Markassiment> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MarkassimentFacade() {
        super(Markassiment.class);
    }
    public void ADD(Markassiment newmass){
        em.persist(newmass);
        em.flush();
    }
    public void DELETE(int massid){
        Markassiment delmass = em.find(Markassiment.class, massid);
        em.remove(delmass);
        em.flush();
    }
    
}
