/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classmember;
import Entity.Course;
import Entity.Markassiment;
import Entity.Memberpermission;
import Entity.Members;
import Entity.Requestassiment;
import Entity.Submitassiment;
import Session.ClassFacade;
import Session.ClassmemberFacade;
import Session.CourseFacade;
import Session.MarkassimentFacade;
import Session.MembersFacade;
import Session.RequestassimentFacade;
import Session.SubmitassimentFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "AssimentController")
@RequestScoped
public class AssimentController {
    //Insert some EJB for using
    
    @EJB
    private MarkassimentFacade markassimentFacade;
    @EJB
    private MembersFacade membersFacade;
    @EJB
    private SubmitassimentFacade submitassimentFacade;
    @EJB
    private ClassmemberFacade classmemberFacade;
    @EJB
    private RequestassimentFacade requestassimentFacade;
    @EJB
    private CourseFacade courseFacade;
    @EJB
    private ClassFacade classFacade;
    private UploadedFile file;
    private ScheduleModel eventModel; 
    int selectedCourse;
    int seletedClass;
    private int temp;
    private Markassiment newmass;
    /** Creates a new instance of AssimentController */
    public AssimentController() {
        selectedCourse = 0;
        seletedClass = 0;
        currentra = new Requestassiment(1);
        temp = 0;
        newmass = new Markassiment();
    }
    //Get Assiment Mark for Student
    public List<Markassiment> GETASSIMENTMARKLIST(Members mem){
        List<Markassiment> lma = new ArrayList<Markassiment>();
        List<Markassiment> lm = new ArrayList<Markassiment>();
        lma = markassimentFacade.findAll();
        for(int i = 0; i < lma.size(); i++){
            if(lma.get(i).getSaid().getMid().equals(mem)){
                lm.add(lma.get(i));
            }
        }
        return lm;
    }
    //Get Assiment Mark
    public double GETASSIMENTMARK(Submitassiment sas){
        double mark = 999;
        List<Markassiment> lma = new ArrayList<Markassiment>();
        lma = markassimentFacade.findAll();
        for(int i = 0; i < lma.size(); i++){
            if(lma.get(i).getSaid().equals(sas)){
                mark = lma.get(i).getMamark();
            }
        }
        return mark;
    }
    //Hack AssimentID to Session, for fix some bug
    public void HackMarkAssimentID(){
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        try{
            int sasid = Integer.parseInt(request.getParameter("submitassimentid"));
            session.setAttribute("submitassimentid", sasid);
        }catch(Exception ex){
            
        }
    }
    //Get current submission assiment by URL
    public Submitassiment GETSUBMITASSIMENTBYID(){
        Submitassiment sas = new Submitassiment();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        try{
            int sasid = Integer.parseInt(request.getParameter("submitassimentid"));
            sas = submitassimentFacade.find(sasid);
        }catch(Exception ex){
            int sasid = Integer.parseInt(session.getAttribute("submitassimentid").toString());
            sas = submitassimentFacade.find(sasid);
        }
        return sas;
    }
    //Creating new MarkAssiment
    public void ADDNEWMARKASSIMENT(Members mem, Submitassiment sas){
        newmass.setSaid(sas);
        newmass.setMid(mem);
        try{
            
            List<Markassiment> lma = new ArrayList<Markassiment>();
            lma = markassimentFacade.findAll();
            for(int i = 0; i < lma.size(); i++){
                if(lma.get(i).getSaid().equals(sas)){
                    markassimentFacade.DELETE(lma.get(i).getMaid());
                }
            }
            markassimentFacade.ADD(newmass);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.sendRedirect("http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces//ViewAssimentStatus.xhtml?assimentid="+sas.getSaid());
        }catch(Exception ex){
        }
    }
    //Delete request assiment by id
    public void DELETEREQUESTASSIMENT(){
        int raid;
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        raid = Integer.parseInt(session.getAttribute("assimentid").toString());
        
        try {
            requestassimentFacade.REMOVE(raid);
        } catch (Exception ex) {
            Logger.getLogger(AssimentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.sendRedirect("http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/ViewAssimentStuff.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(AssimentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Clear all Stuff and Admin from a ClassmembersList
    public List<Classmember> CLEARSTUFF(List<Classmember> lmem){
        List<Classmember> newlmem = new ArrayList<Classmember>();
        if(lmem!=null){
        for(int i = 0; i < lmem.size(); i++){
            if(lmem.get(i).getMid().getMpermission().equals(new Memberpermission(3))){
                newlmem.add(lmem.get(i));
            }
        }
        }
        return newlmem;
    }
    //Get SubmiAssiment by Memberid
    public List<Submitassiment> GETSUBMITASSIMENTBYMEMBER(Members mem){
        List<Submitassiment> lsat = new ArrayList<Submitassiment>();
        List<Submitassiment> lsa = new ArrayList<Submitassiment>();
        lsa = submitassimentFacade.findAll();
        for(int i = 0; i < lsa.size(); i++){
            if(lsa.get(i).getMid().equals(mem)){
                lsat.add(lsa.get(i));
            }
        }
        return lsat;
    }
    //Count Number of Submission by RequestAssimentID
    public int GETCOUNTMEMBERBYRA(int raid){
        int count = 0;
        Requestassiment cra = new Requestassiment();
        cra = requestassimentFacade.find(raid);
        List<Submitassiment> sas = new ArrayList<Submitassiment>();
        sas = submitassimentFacade.findAll();
        for(int i = 0; i < sas.size(); i++){
            if(sas.get(i).getRaid().equals(cra)){
                count++;
            }
        }
        return count;
    }
    //Get all Assiment for Stuff
    public List<Requestassiment> GETREQUESTASSIMENTSTUFF(Members stuff){
        List<Requestassiment> rs = new ArrayList<Requestassiment>();
        List<Requestassiment> ras = new ArrayList<Requestassiment>();
        rs = requestassimentFacade.findAll();
        for(int i = 0; i < rs.size(); i++){
            if(rs.get(i).getStuffmid().equals(stuff)){
                ras.add(rs.get(i));
            }
        }
        return ras;
    }
    //Get assiment PieChart for Report
    public PieChartModel GETMEMBERNUMBERBYCLASSCHART(){
        PieChartModel pieAss;
        pieAss = new PieChartModel();
        List<Entity.Class> classlist = classFacade.findAll();
        for(int i = 0; i < classlist.size(); i++){
            pieAss.set(classlist.get(i).getCname(), classlist.get(i).getClassmemberList().size());
        }
        return pieAss;
    }
    //Get assiment PieChart for Report
    public PieChartModel GETASSIMENTNUMBERBYCLASSCHART(){
        PieChartModel pieAss;
        pieAss = new PieChartModel();
        List<Entity.Class> classlist = classFacade.findAll();
        for(int i = 0; i < classlist.size(); i++){
            pieAss.set(classlist.get(i).getCname(), classlist.get(i).getRequestassimentList().size());
        }
        return pieAss;
    }
    //Get assiment to schedule for homepage
    public void SETUPSCHEDULE(){
        eventModel = new DefaultScheduleModel();
        Date today = new Date();
        DefaultScheduleEvent sv = new DefaultScheduleEvent("Testing", today, today);
        eventModel.addEvent(sv);
    }
    //Some code block about file and FileUpload isn't work, just for testing
    private String filePath;
    //demo fileupload from http://java.dzone.com/articles/how-upload-primefaces-under
    private static final int BUFFER_SIZE = 6124;
    //Get all information of current account: Copy from MemberController :D
    public Members GETACCOUNT() {
        Members account = new Members();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            account.setMid(Integer.parseInt(session.getAttribute("userid").toString()));
            account = membersFacade.find(account.getMid());
        } else {
            account = null;
        }
        return account;
    }
    Requestassiment currentra;
    public void handleFileUpload(FileUploadEvent event) {
        //SAID, SAFILENAME, MID, RAID
        String filename = event.getFile().getFileName();
        Submitassiment newsass = new Submitassiment();
        newsass.setMid(GETACCOUNT());//Set Members
        newsass.setSafilename(filename);//Set filename
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            HttpSession session = request.getSession(true);
            newsass.setRaid(requestassimentFacade.find(Integer.parseInt(session.getAttribute("assimentid").toString())));//Set RequestAssiment
        } catch (Exception ex) {
        }
        
        submitassimentFacade.ADD(newsass);
        UploadFile(event.getFile(),"//assiment//");
        FacesMessage msg = new FacesMessage("Succesful", filename + " is uploaded from " + GETACCOUNT().getMfullname() + " for " + newsass.getRaid().getRaname());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    //Upload file to a destination folder
    public void UploadFile(UploadedFile uFile, String filepath){
        
    }
    public void HackRAID(){
        Requestassiment ra = new Requestassiment();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            HttpSession session = request.getSession(true);
            session.setAttribute("assimentid", Integer.parseInt(request.getParameter("assimentid")));
        } catch (Exception ex) {
            try {
                response.sendRedirect("http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/ViewAssimentStuff.xhtml");
            } catch (Exception e) {
            }
        }
    }
    //Get current assiment from id in URL
    public Requestassiment GETASSIMENTBYID() {
        Requestassiment ra = new Requestassiment();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            ra = requestassimentFacade.find(Integer.parseInt(request.getParameter("assimentid")));
        } catch (Exception ex) {
        }
        currentra = ra;
        return ra;
    }
    //Equal a date with today Date for validate "it's after deadline or not"

    public boolean VALIDDEADLINE(Date deadline) {
        try {
            Date today = new Date();
            if (deadline.after(today)) {
                return true;
            } else if (deadline.equals(today)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    //Get all assiment for current logged account

    public List<Requestassiment> GETASSIMENTBYACCOUNT() {
        Entity.Class classes = new Entity.Class();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        classes = classFacade.find(Integer.parseInt(request.getParameter("classid")));
        List<Requestassiment> rs = new ArrayList<Requestassiment>();
        rs = classes.getRequestassimentList();
        //rs = requestassimentFacade.findAll();
        return rs;
    }
    //Get all assiment for selected class from URL
    public List<Requestassiment> GETALLASSIMENTBYCLASS() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Entity.Class classes = new Entity.Class();

        List<Requestassiment> rsx = requestassimentFacade.findAll();
        List<Requestassiment> rs = new ArrayList<Requestassiment>();
        try {
            seletedClass = Integer.parseInt(request.getParameter("classid"));
            classes = classFacade.find(seletedClass);
            for (int i = 0; i < rsx.size(); i++) {
                if (rsx.get(i).getCid().equals(classes)) {
                    rs.add(rsx.get(i));
                }
            }
        } catch (Exception ex) {
            rs = rsx;
        }
        return rs;
    }
    //Get all assiment for selected course from URL
    public List<Requestassiment> GETALLASSIMENTBYCOURSE() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Entity.Course course = new Entity.Course();

        List<Requestassiment> rsx = requestassimentFacade.findAll();
        List<Requestassiment> rs = new ArrayList<Requestassiment>();
        try {
            selectedCourse = Integer.parseInt(request.getParameter("courseid"));
            course = courseFacade.find(selectedCourse);
            for (int i = 0; i < rsx.size(); i++) {
                if (rsx.get(i).getCid2().equals(course)) {
                    rs.add(rsx.get(i));
                }
            }
        } catch (Exception ex) {
            rs = rsx;
        }
        return rs;
    }
    //Setup a assiment view by URL

    public void SETUPASSIMENTVIEW() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        HttpSession session = request.getSession(true);
//        session.setAttribute("login", 1);
        try {
            seletedClass = Integer.parseInt(request.getParameter("classid"));
            selectedCourse = Integer.parseInt(request.getParameter("courseid"));
        } catch (Exception exception) {
        }
    }

    public Entity.Class GETSELECTEDCLASS() {
        Entity.Class classes = new Entity.Class();
        classes = classFacade.find(seletedClass);
        return classes;
    }

    public Course GETSELECTEDCOURSE() {
        Course cou = new Course();
        cou = courseFacade.find(selectedCourse);
        return cou;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * @return the temp
     */
    public int getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(int temp) {
        this.temp = temp;
    }

    /**
     * @return the newmass
     */
    public Markassiment getNewmass() {
        return newmass;
    }

    /**
     * @param newmass the newmass to set
     */
    public void setNewmass(Markassiment newmass) {
        this.newmass = newmass;
    }
}
