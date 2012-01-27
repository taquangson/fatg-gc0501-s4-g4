/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classincourse;
import Entity.Classmember;
import Entity.Course;
import Entity.Memberpermission;
import Entity.Members;
import Entity.Requestassiment;
import Session.ClassFacade;
import Session.ClassmemberFacade;
import Session.CourseFacade;
import Session.MemberpermissionFacade;
import Session.MembersFacade;
import Session.RequestassimentFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "ShowClassController")
@RequestScoped
public class ShowClassController {

    @EJB
    private CourseFacade courseFacade;
    @EJB
    private RequestassimentFacade requestassimentFacade;
    @EJB
    private MemberpermissionFacade memberpermissionFacade;
    @EJB
    private MembersFacade membersFacade;
    @EJB
    private ClassmemberFacade classmemberFacade;
    @EJB
    private ClassFacade classFacade;
    private List<Members> memberClass;
    private int selectedCID;
    private int selectedCID2;
    private Requestassiment newra;
    private UploadedFile file;

    /** Creates a new instance of ShowClassController */
    public ShowClassController() {
        memberClass = new ArrayList<Members>();
        newra = new Requestassiment();
        selectedCID = 0;
        selectedCID2 = 0;
    }
    
    public void valueChangeMethod(ValueChangeEvent e){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "xxx", "xxx"));
    }
    
    public void HACKCLASSID(){
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        int cid = 0;
        try{
            cid = Integer.parseInt(request.getParameter("classid"));
            session.setAttribute("classid", cid);
        }catch(Exception ex){
        }
        List<Members> rs = new ArrayList<Members>();
        if (cid != 0) {
            Entity.Class mclass = classFacade.find(cid);
            List<Classmember> mrs = classmemberFacade.findAll();
            Memberpermission mpm = memberpermissionFacade.find(3);
            for (int i = 0; i < mrs.size(); i++) {
                if (mrs.get(i).getCid().equals(mclass) && mrs.get(i).getMid().getMpermission().equals(mpm)) {
                    rs.add(mrs.get(i).getMid());
                }
            }
        }
        setMemberClass(rs);
    }

    public List<Course> SHOWCOURSE() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        int cid = Integer.parseInt(session.getAttribute("classid").toString());
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

    public List<Entity.Class> SHOWALL() {
        return classFacade.findAll();
    }

    public void NEWRA(Requestassiment newrax, Members mem) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        int cid = 0;
        try{
            cid = Integer.parseInt(session.getAttribute("classid").toString());
        }catch(Exception ex){
        }
        int cid2 = selectedCID2;
        int error = 0;
        if (newrax.getRaname().equals("")) {
            error++;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some input is wrong", "Assiment name can't be empty"));
        }
        if (newrax.getRainfo().equals("")) {
            error++;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some input is wrong", "Assiment info can't be empty"));
        }
        if (newrax.getRadeadline() == null) {
            error++;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some input is wrong", "Assiment deadline can't be empty"));
        }
        if (cid == 0) {
            error++;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some input is wrong", "Select class first"));
        }
        if (cid2 == 0) {
            error++;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some input is wrong", "Please select course"));
        }
        if (file==null) {
            error++;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some input is wrong", "File is null"));
        }
        if (error == 0) {
            newrax.setRadate(new Date());
            newrax.setRafilename(file.getFileName());
            newrax.setCid(classFacade.find(cid));
            newrax.setCid2(courseFacade.find(cid2));
            newrax.setStuffmid(mem);
            requestassimentFacade.ADD(newrax);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Assiment has been requested", "Name: " + newra.getRaname()+" | CLass: "+ newra.getCid().getCname()+" | Course: "+newra.getCid2().getCname()));
        }
    }

    public int SETUPMEMBERCLASS() {
        int cid = 0;
        List<Members> rs = new ArrayList<Members>();
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try{
            cid = Integer.parseInt(req.getParameter("classid"));
        }catch(Exception ex){
            
        }
        return cid;
    }

    public List<Entity.Class> SHOWALLBYMEMBER(Members mem) {
        List<Entity.Class> rs = new ArrayList<Entity.Class>();
        List<Classmember> clrs = classmemberFacade.findAll();
        for (int i = 0; i < clrs.size(); i++) {
            if (clrs.get(i).getMid().equals(mem)) {
                rs.add(clrs.get(i).getCid());
            }
        }
        return rs;
    }

    public String[] SHOWNAMECLASS() {
        List<Entity.Class> classrs = classFacade.findAll();
        String[] rs = new String[classrs.size()];
        for (int i = 0; i < classrs.size(); i++) {
            rs[i] = classrs.get(i).getCname();
        }
        return rs;
    }

    /**
     * @return the memberClass
     */
    public List<Members> getMemberClass() {
        return memberClass;
    }

    /**
     * @param memberClass the memberClass to set
     */
    public void setMemberClass(List<Members> memberClass) {
        this.memberClass = memberClass;
    }

    /**
     * @return the selectedCID
     */
    public int getSelectedCID() {
        return selectedCID;
    }

    /**
     * @param selectedCID the selectedCID to set
     */
    public void setSelectedCID(int selectedCID) {
        this.selectedCID = selectedCID;
    }

    /**
     * @return the newra
     */
    public Requestassiment getNewra() {
        return newra;
    }

    /**
     * @param newra the newra to set
     */
    public void setNewra(Requestassiment newra) {
        this.newra = newra;
    }

    /**
     * @return the selectedCID2
     */
    public int getSelectedCID2() {
        return selectedCID2;
    }

    /**
     * @param selectedCID2 the selectedCID2 to set
     */
    public void setSelectedCID2(int selectedCID2) {
        this.selectedCID2 = selectedCID2;
    }

    /**
     * @param selectedCID2 the selectedCID2 to set
     */
    public void setSelectedCID2(String selectedCID2) {
        this.selectedCID2 = Integer.parseInt(selectedCID2);
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
