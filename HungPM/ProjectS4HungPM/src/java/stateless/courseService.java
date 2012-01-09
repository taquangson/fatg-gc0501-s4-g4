/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stateless;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kai
 */
@Stateless
@LocalBean
public class courseService {    
    @PersistenceContext
    EntityManager em;
    
    public List allBaths(){
        return em.createQuery("SELECT b FROM Bath b").getResultList();
    }
    
    public List getSemsByBath(int id){
        return em.createQuery("SELECT s FROM Sem s JOIN s.bid b WHERE b.bid = "+id).getResultList();
    }
    
    public List getCoursesBySem(int id){
        return em.createQuery("SELECT c FROM Course c JOIN c.sid s WHERE s.sid = "+id).getResultList();
    }
    
    public List getClassesByCourse(int id){
        return em.createQuery("SELECT cc FROM Classincourse cc JOIN cc.course c  WHERE c.cid = "+id+" JOIN cc.class1 cl WHERE cl.cid = cc.ccid").getResultList();
    }
}
