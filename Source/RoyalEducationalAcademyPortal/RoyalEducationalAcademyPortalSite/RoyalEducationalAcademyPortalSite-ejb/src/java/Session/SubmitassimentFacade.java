/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Submitassiment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class SubmitassimentFacade extends AbstractFacade<Submitassiment> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public SubmitassimentFacade() {
        super(Submitassiment.class);
    }
    public void ADD(Submitassiment newsass){
        try{
            em.persist(newsass);
            em.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
