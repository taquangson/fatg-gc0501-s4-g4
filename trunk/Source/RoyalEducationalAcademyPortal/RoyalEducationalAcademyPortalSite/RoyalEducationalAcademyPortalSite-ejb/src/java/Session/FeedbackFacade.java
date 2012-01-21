/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Feedback;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class FeedbackFacade extends AbstractFacade<Feedback> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackFacade() {
        super(Feedback.class);
    }
    
    public void ADDNEWFEEDBACK(Feedback fb){
        em.persist(fb);
    }
    public void DELETE(int id){
        Feedback rs = em.find(Feedback.class, id);
        em.remove(rs);
    }
    
}
