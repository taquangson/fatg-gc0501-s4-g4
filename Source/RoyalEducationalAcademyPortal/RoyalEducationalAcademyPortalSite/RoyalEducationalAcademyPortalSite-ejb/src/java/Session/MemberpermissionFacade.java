/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Memberpermission;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class MemberpermissionFacade extends AbstractFacade<Memberpermission> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MemberpermissionFacade() {
        super(Memberpermission.class);
    }
    public List<Memberpermission> FINDBYMPID(int mpid){
        return em.createNamedQuery("Memberpermission.findByMpid").setParameter("mpid", mpid).getResultList();
    }
    
}
