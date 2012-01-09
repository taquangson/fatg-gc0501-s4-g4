/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Faq;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class FaqFacade extends AbstractFacade<Faq> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FaqFacade() {
        super(Faq.class);
    }
    
    public void DELETE(int id){
        Faq deleteFAQs = new Faq();
        deleteFAQs = em.find(Faq.class, id);
        em.remove(deleteFAQs);
    }
    
    public void newFAQs(String question, String answer){
        Faq newFAQs = new Faq();
        newFAQs.setFquestion(question);
        newFAQs.setFanswer(answer);
        em.persist(newFAQs);
    }
    
}
