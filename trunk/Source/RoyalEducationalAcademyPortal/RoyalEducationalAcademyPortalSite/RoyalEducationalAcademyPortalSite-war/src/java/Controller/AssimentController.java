/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Course;
import Entity.Members;
import Entity.Requestassiment;
import Entity.Submitassiment;
import Session.ClassFacade;
import Session.ClassmemberFacade;
import Session.CourseFacade;
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

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "AssimentController")
@RequestScoped
public class AssimentController {
    //Insert some EJB for using
    
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

    /** Creates a new instance of AssimentController */
    public AssimentController() {
        selectedCourse = 0;
        seletedClass = 0;
        currentra = new Requestassiment(1);
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
    private String folderToUpload;
    //Save a file from file upload

    public void AssimentFileUpload(UploadedFile file) {

//        FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");  
//        FacesContext.getCurrentInstance().addMessage(null, msg);

        String uploadedFolder = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadFolder");

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File(extContext.getRealPath("//" + uploadedFolder) + "//" + file.getFileName());
        System.out.println(result.getAbsolutePath());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[(int) file.getSize()];

            int bulk;
            InputStream inputStream = file.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            setFilePath(result + "/" + file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Complete upload", filePath));

        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The files were not uploaded!", filePath));
        }
    }
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
        
        FacesMessage msg = new FacesMessage("Succesful", filename + " is uploaded from " + GETACCOUNT().getMfullname() + " for " + newsass.getRaid().getRaname());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    public void HackRAID(){
        Requestassiment ra = new Requestassiment();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            HttpSession session = request.getSession(true);
            session.setAttribute("assimentid", Integer.parseInt(request.getParameter("assimentid")));
        } catch (Exception ex) {
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
     * @return the folderToUpload
     */
    public String getFolderToUpload() {
        return folderToUpload;
    }

    /**
     * @param folderToUpload the folderToUpload to set
     */
    public void setFolderToUpload(String folderToUpload) {
        this.folderToUpload = folderToUpload;
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
}
