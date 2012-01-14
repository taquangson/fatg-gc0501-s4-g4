/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import entities.Memberpermission;
import entities.Members;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thao
 */
@Stateless
@LocalBean
public class MemberService {

    @PersistenceContext
    EntityManager em;
    
    public void insertMember(String userName, String password,String fullName,String address,String email, Date birthday,byte[] mavarta,int permission)
    {        
        Members m = new Members();
        Memberpermission mp = em.find(Memberpermission.class, permission);
        m.setMusername(userName);
        m.setMpassword(password);
        m.setMfullname(fullName);
        m.setMadress(address);
        m.setMemail(email);
        m.setMbirthdate(birthday);
        m.setMavarta(mavarta);
        m.setMpermission(mp);
        
        em.persist(m);
    }
    
//    public List getMemberById(int id)
//    {
//        return em.createQuery("SELECT m FROM Members m WHERE m.mid = " +id).getResultList();
//    }
    
    public Members getMemberByUserAndPwd(String user, String pwd)
    {
        return (Members)em.createQuery("SELECT m FROM Members m WHERE m.musername = '" +user + "' AND m.mpassword = '" + pwd + "'").getSingleResult();
    }
    
    public List getAllMember()
    {
        return em.createQuery("SELECT m FROM Members m").getResultList();
    }
    
    public Members editMemInfo(Members m)
    {
        em.merge(m);
        return m;
    }
    
    public Members getMemberById(int id){
        return em.find(Members.class, id);
    }
    
}
