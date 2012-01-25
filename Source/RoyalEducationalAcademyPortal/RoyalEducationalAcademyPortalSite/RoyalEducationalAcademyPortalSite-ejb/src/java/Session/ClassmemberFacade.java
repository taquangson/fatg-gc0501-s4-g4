/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Classmember;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class ClassmemberFacade extends AbstractFacade<Classmember> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassmemberFacade() {
        super(Classmember.class);
    }
    public List<Classmember> SHOWALL(){
        return em.createNamedQuery("Classmember.findAll").getResultList();
    }
    public void ADD(Classmember newmem){
        em.persist(newmem);
        em.flush();
    }
    public void DELETE(int id){
        Classmember rmem = em.find(Classmember.class, id);
        em.remove(rmem);
        em.flush();
    }
}
