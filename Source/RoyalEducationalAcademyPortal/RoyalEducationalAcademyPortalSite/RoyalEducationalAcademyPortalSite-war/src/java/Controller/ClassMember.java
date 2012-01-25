/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classincourse;
import Entity.Classmember;
import Entity.Course;
import Entity.Members;
import Session.ClassFacade;
import Session.ClassmemberFacade;
import Session.MembersFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name="ClassMemberController")
@RequestScoped
public class ClassMember {
    @EJB
    private ClassFacade classFacade;
    @EJB
    private MembersFacade membersFacade;
    @EJB
    private ClassmemberFacade classmemberFacade;
    
    private DualListModel<Members> listmode;  
    private List<Members> sourceMember;
    private List<Members> pickMember;
    private int SelectedCID;
    
    /** Creates a new instance of ClassMember */
    public ClassMember() {
        sourceMember = new ArrayList<Members>();
        SelectedCID = 0;
        //sourceMember = membersFacade.findAll();
        pickMember = new ArrayList<Members>();
        listmode = new DualListModel<Members>(sourceMember, pickMember);
    }
    //Show a course by cid
    public List<Course> SHOWCOURSE(int cid) {
        List<Course> rs = new ArrayList<Course>();
        if (cid != 0) {
            Entity.Class mclass = classFacade.find(cid);
            List<Classincourse> cicl = mclass.getClassincourseList();
            for (int y = 0; y < cicl.size(); y++) {
                rs.add(cicl.get(y).getCourse());
            }
        }
        return rs;
    }
    //Show a class depend on account
    public List<Entity.Class> SHOWCLASSBYACCOUNT(Members account){
        List<Entity.Class> rs = new ArrayList<Entity.Class>();
        List<Classmember> clrs = classmemberFacade.findAll();
        for(int i = 0; i< clrs.size(); i++){
            if(clrs.get(i).getMid().equals(account)){
                rs.add(clrs.get(i).getCid());            }
        }
        return rs;
    }
    //Get a class id from account
    public String SHOWCIDBYACCOUNT(Members account){
        List<Entity.Class> rs = new ArrayList<Entity.Class>();
        List<Classmember> clrs = classmemberFacade.findAll();
        for(int i = 0; i< clrs.size(); i++){
            if(clrs.get(i).getMid().equals(account)){
                rs.add(clrs.get(i).getCid());            }
        }
        return rs.get(0).getCid().toString();
    }
    //Show all class by List
    public List<Entity.Class> SHOWALLCLASS(){
        return classFacade.findAll();
    }
    //Just for Testing OneMenuSelect, not use
    public String[] SHOWCLASSSELECT(){
        List<Entity.Classmember> classrs = classmemberFacade.SHOWALL();
        String[] rs = new String[classrs.size()];
        for(int i = 0; i < classrs.size(); i++){
            rs[i] = classrs.get(i).getCid().getCname();
        }
        return rs;
    }

    /**
     * @return the sourceMember
     */
    public List<Members> getSourceMember() {
        return sourceMember;
    }

    /**
     * @param sourceMember the sourceMember to set
     */
    public void setSourceMember(List<Members> sourceMember) {
        this.sourceMember = sourceMember;
    }

    /**
     * @return the pickMember
     */
    public List<Members> getPickMember() {
        return pickMember;
    }

    /**
     * @param pickMember the pickMember to set
     */
    public void setPickMember(List<Members> pickMember) {
        this.pickMember = pickMember;
    }

    /**
     * @return the listmode
     */
    public DualListModel<Members> getListmode() {
        return listmode;
    }

    /**
     * @param listmode the listmode to set
     */
    public void setListmode(DualListModel<Members> listmode) {
        this.listmode = listmode;
    }

    /**
     * @return the SelectedCID
     */
    public int getSelectedCID() {
        return SelectedCID;
    }

    /**
     * @param SelectedCID the SelectedCID to set
     */
    public void setSelectedCID(int SelectedCID) {
        this.SelectedCID = SelectedCID;
    }
}
