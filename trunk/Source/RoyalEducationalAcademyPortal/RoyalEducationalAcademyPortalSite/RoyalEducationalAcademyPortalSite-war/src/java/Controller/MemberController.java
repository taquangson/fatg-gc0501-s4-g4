/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Memberpermission;
import Entity.Members;
import Session.MembersFacade;
import com.sun.faces.facelets.tag.jsf.core.ConvertDateTimeHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name="MemberController")
@RequestScoped
public class MemberController {
    @EJB
    private MembersFacade member1Facade;
    private String username;
    private String password;
    private int login = 0;
    private int newpermission;
    private Members MemberEntity;
    private Members NewMemberEntity;
    private UploadedFile file;
    /** Creates a new instance of MemberController */
    public int GETPERMISSION(){
        return GETACCOUNT().getMpermission().getMpid();
    }
    public MemberController() {
        MemberEntity = new Members();
        NewMemberEntity = new Members();
    }
    public void DELETEMEMBER(String id){
        member1Facade.DELETE(Integer.parseInt(id));
    }
    public List<Members> SHOWALL(){
        return member1Facade.findAll();
    }
    public void SAVEUPLOADFILETOAVERTA(){
        MemberEntity = GETACCOUNT();
        InputStream inputStream = null;
        try {
            byte[] buffer = new byte[(int)getFile().getSize()];
            inputStream = getFile().getInputstream();
            inputStream.read(buffer);
            MemberEntity.setMavarta(buffer);
            FacesMessage msg = new FacesMessage("Succesful", getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SAVEACCOUNT();
    }
    public void ADDNEWMEMBER(){
        NewMemberEntity.setMpassword(new MD5().String2MD5(NewMemberEntity.getMpassword()));
        NewMemberEntity.setMpermission(new Memberpermission(newpermission));
        member1Facade.ADD(NewMemberEntity);
    }
    public void SETAVARTA(UploadedFile file){
        InputStream inputStream = null;
        try {
            byte[] buffer = new byte[(int)file.getSize()];
            inputStream = file.getInputstream();
            inputStream.read(buffer);
            //MemberEntity.setMavarta(buffer);
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void CHANGEPASSWORD(){
        FacesMessage msg = new FacesMessage("Succesful", " Password is change.");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }
    public void LOGIN(String username, String password){
        boolean valid = false;
        int permission = 0;
        int userid = 0;
        // get all members
        List<Members> lm = member1Facade.findAll();
        // md5 password
        password = new MD5().String2MD5(password);
        //valid each username and password, if true, set isValid
        for(int i = 0; i < lm.size() ; i++){
            if(lm.get(i).getMusername().equals(username)){
                if(lm.get(i).getMpassword().equals(password)){
                    valid = true;
                    permission = lm.get(i).getMpermission().getMpid();
                    userid = lm.get(i).getMid();
                }
            }
        }
        //Redirect to right page and set session
        if(valid == true){
            login = 1;
            String newpage = "http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/MemberPanel.xhtml";
            if(permission==1){
                newpage = "http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/AdminPanel.xhtml";
            }else if(permission==2){
                newpage = "http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/StuffPanel.xhtml";
            }
            
                try {
                    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login", 1);
                    session.setAttribute("userid", userid);
                    session.setAttribute("permission", permission);
                    response.sendRedirect(newpage);
                } catch (IOException ex) {
                    Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.sendRedirect("http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/Login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String GETLOGIN(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        if(session.getAttribute("login")!=null){
            return "none";
        }else{
            return "inline";
        }
    }
    
    public Members GETACCOUNT(){
        Members account = new Members();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        if(session.getAttribute("login")!=null){
            account.setMid(Integer.parseInt(session.getAttribute("userid").toString()));
            account = member1Facade.find(account.getMid());
            MemberEntity = account;
        }else{
            account = null;
        }
        return account;
    }
    public void LOGOUT(){
        try {
            String newpage = "http://localhost:8080/RoyalEducationalAcademyPortalSite-war/";
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(true);
            session.removeAttribute("login");
            session.removeAttribute("userid");
            session.removeAttribute("permission");
            response.sendRedirect(newpage);
        } catch (IOException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SAVEACCOUNT(){
        member1Facade.UPDATE(MemberEntity);
        FacesMessage msg = new FacesMessage("Succesful", "Information is saved!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the login
     */
    public int getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(int login) {
        this.login = login;
    }

    /**
     * @return the MemberEntity
     */
    public Members getMemberEntity() {
        return MemberEntity;
    }

    /**
     * @param MemberEntity the MemberEntity to set
     */
    public void setMemberEntity(Members MemberEntity) {
        this.MemberEntity = MemberEntity;
    }

    /**
     * @return the newpermission
     */
    public int getNewpermission() {
        return newpermission;
    }

    /**
     * @param newpermission the newpermission to set
     */
    public void setNewpermission(int newpermission) {
        this.newpermission = newpermission;
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
     * @return the NewMemberEntity
     */
    public Members getNewMemberEntity() {
        return NewMemberEntity;
    }

    /**
     * @param NewMemberEntity the NewMemberEntity to set
     */
    public void setNewMemberEntity(Members NewMemberEntity) {
        this.NewMemberEntity = NewMemberEntity;
    }

}
