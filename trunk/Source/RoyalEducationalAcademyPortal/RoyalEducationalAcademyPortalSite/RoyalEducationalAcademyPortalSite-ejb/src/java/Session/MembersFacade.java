/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Memberpermission;
import Entity.Members;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SVN - Team
 */
@Stateless
public class MembersFacade extends AbstractFacade<Members> {
    @PersistenceContext(unitName = "RoyalEducationalAcademyPortalSite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MembersFacade() {
        super(Members.class);
    }
    public void ADDTEST(){
        Members rs = new Members();
        em.persist(rs);
    }
    //Code block for insert row (v2)
    public void ADD2(String username, String password, String address, String fullname, String email, byte[] avarta, Date birthday, int permission){
        Members rs = new Members();
//        rs.setMusername(username);
//        rs.setMpassword(password);
//        rs.setMadress(address);
//        rs.setMfullname(fullname);
//        rs.setMemail(email);
//        rs.setMavarta(avarta);
//        rs.setMbirthdate(birthday);
//        Memberpermission mps = em.find(Memberpermission.class, permission);
//        rs.setMpermission(mps);
        em.persist(rs);
    }
    //Code block for insert row (v1)
    public void ADD(Members rs){
        em.persist(rs);
    }
    //Code block for removing row
    public void DELETE(int id){
        Members rs = em.find(Members.class, id);
        em.remove(rs);
    }
    public void UPDATE(Members rs){
        em.merge(rs);
    }
}
