/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classmember;
import Entity.Memberpermission;
import Entity.Members;
import Session.ClassmemberFacade;
import Session.FeedbackFacade;
import Session.MemberpermissionFacade;
import Session.MembersFacade;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "MemberController")
@RequestScoped
public class MemberController {

    @EJB
    private ClassmemberFacade classmemberFacade;
    @EJB
    private FeedbackFacade feedbackFacade;
    @EJB
    private MemberpermissionFacade memberpermissionFacade;
    @EJB
    private MembersFacade member1Facade;
    private String username;
    private String password;
    private int login = 0;
    private int newpermission;
    private Members MemberEntity;
    private Members NewMemberEntity;
    private UploadedFile file;
    private List<Members> MembersList;
    private DualListModel<Members> listmode;

    /** Creates a new instance of MemberController */
    public MemberController() {
        MemberEntity = new Members();
        NewMemberEntity = new Members();
        //MembersList = new ArrayList<Members>();


    }

    public void ONDROP(DragDropEvent ddEvent) {
        Members mem = ((Members) ddEvent.getData());
        listmode.getTarget().add(mem);
        listmode.getSource().remove(mem);
    }

    public void SAVECLASSMEMBER(List<Members> pickMember) {
        int size = listmode.getTarget().size();
        //List<Members> pickMember = listmode.getTarget();
        for (int i = 0; i < size; i++) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Member added!", listmode.getTarget().get(i) + ""));
        }
    }

    public void SETDUALLIST() {
        List<Members> sourceMember = member1Facade.findAll();
        sourceMember.remove(GETACCOUNT());
        List<Members> pickMember = new ArrayList<Members>();
        List<Classmember> cm = classmemberFacade.findAll();

        for (int i = 0; i < cm.size(); i++) {
            pickMember.add(cm.get(i).getMid());
            if (sourceMember.contains(cm.get(i).getMid())) {
                sourceMember.remove(cm.get(i).getMid());
            }
        }

        listmode = new DualListModel<Members>(sourceMember, pickMember);
    }

    public List<Members> GETSTUFFANDADMIN() {
        List<Members> rs = new ArrayList<Members>();
        Members madmin = new Members();
        madmin.setMfullname("Administrator");
        madmin.setMid(1);
        rs = memberpermissionFacade.FINDBYMPID(2).get(0).getMembersList();
        rs.add(madmin);
        return rs;
    }

    public int GETPERMISSION() {
        return GETACCOUNT().getMpermission().getMpid();
    }

    public String removeNonAlphabeta(String text) {
        //Solving Vietnamese (utf-8) problem :)
        text = ReplaceStringNew(text, "âấầẫẩậàáảãạ", 'a');
        text = ReplaceStringNew(text, "ÂẤẦẨẪẬÀÁẢẬ", 'A');
        text = ReplaceStringNew(text, "đ", 'd');
        text = ReplaceStringNew(text, "Đ", 'D');
        text = ReplaceStringNew(text, "êểễệéèẻẽẹ", 'e');
        text = ReplaceStringNew(text, "ÊỂỄỆÉÈẺẼẸ", 'E');
        text = ReplaceStringNew(text, "ìíỉĩị", 'i');
        text = ReplaceStringNew(text, "ÌÍỈĨỊ", 'I');
        text = ReplaceStringNew(text, "ôồốổỗộòóỏõọ", 'o');
        text = ReplaceStringNew(text, "ÔỒỐỔỖỘÒÓỎÕỌ", 'O');
        text = ReplaceStringNew(text, "ỳýỷỹỵ", 'y');
        text = ReplaceStringNew(text, "ỲÝỶỸỴ", 'Y');
        text = ReplaceStringNew(text, "ùúủũụ", 'u');
        text = ReplaceStringNew(text, "ÙÚỦŨỤ", 'U');
        return text;
    }

    public String ReplaceStringNew(String text, String list, char c) {
        for (int i = 0; i < list.length(); i++) {
            text = text.replace(list.charAt(i), c);
        }
        return text;
    }

    public String removeNonDigits(String text) {
        int length = text.length();
        StringBuffer buffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            char ch = text.charAt(i);
            if (Character.isDigit(ch)) {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    public String GETNEWMEMBERID() {
        int id = 0;
        String sid = "";
        List<Members> AMemberEntity = member1Facade.findAll();
        for (int i = 0; i < AMemberEntity.size(); i++) {
            String musername = removeNonDigits(AMemberEntity.get(i).getMusername());
            if (musername.length() >= 1) {
                if (Integer.parseInt(musername) > id) {
                    id = Integer.parseInt(musername);
                }
            }
        }
        int loop = 6 - ((id + "").length());
        for (int y = 0; y < loop; y++) {
            sid = "0" + sid;
        }
        id++;
        return (sid + id);
    }

    public void GETAUTOROLLNUMBER() {
        String rollnumber = "";// blank
        String name = "";
        String[] temp;//create a temp for rollnumber string
        String delimiter = " ";//space delimiter
        temp = NewMemberEntity.getMfullname().split(delimiter);
        for (int i = 0; i < temp.length; i++) {
            if (i == (temp.length - 1)) {
                name = temp[i].toString();
            } else {
                rollnumber += temp[i].charAt(0);
            }
        }
        rollnumber = removeNonAlphabeta(name + rollnumber + GETNEWMEMBERID());
        NewMemberEntity.setMusername(rollnumber);
    }

    public void DELETEMEMBER(String id) {
        member1Facade.DELETE(Integer.parseInt(id));
    }

    public List<Members> SHOWALL() {
        return member1Facade.findAll();
    }

    public void SAVEUPLOADFILETOAVERTA() {
        MemberEntity = GETACCOUNT();
        InputStream inputStream = null;
        try {
            byte[] buffer = new byte[(int) getFile().getSize()];
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

    public void ADDNEWMEMBER() {
        int error = 0;
        if (NewMemberEntity.getMfullname().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fullname is empty", "Input fullname!"));
            error++;
        }
        if (NewMemberEntity.getMadress().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No address", "Address can't empty!"));
            error++;
        }
        if (NewMemberEntity.getMbirthdate() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong birthdate", "Birthdate is wrong"));
            error++;
        }
        if (NewMemberEntity.getMemail().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is empty", "Input email!"));
            error++;
        }
        if (NewMemberEntity.getMpassword().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password is empty", "Input password"));
            error++;
        }
        NewMemberEntity.setMpassword(new MD5().String2MD5(NewMemberEntity.getMpassword()));
        NewMemberEntity.setMpermission(new Memberpermission(newpermission));
        if (error == 0) {
            member1Facade.ADD(NewMemberEntity);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Member " + NewMemberEntity.getMusername() + " added!", "Successful!"));
        }
    }

    public void SETAVARTA(UploadedFile file) {
        InputStream inputStream = null;
        try {
            byte[] buffer = new byte[(int) file.getSize()];
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

    public void CHANGEPASSWORD() {
        FacesMessage msg = new FacesMessage("Succesful", " Password is change.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void LOGIN(String username, String password) {
        username = username.toLowerCase();
        boolean valid = false;
        int permission = 0;
        int userid = 0;
        // get all members
        List<Members> lm = new ArrayList<Members>();
        try {
            lm = member1Facade.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // md5 password
        password = new MD5().String2MD5(password);
        //valid each username and password, if true, set isValid
        for (int i = 0; i < lm.size(); i++) {
            if (lm.get(i).getMusername().toLowerCase().equals(username)) {
                if (lm.get(i).getMpassword().equals(password)) {
                    valid = true;
                    permission = lm.get(i).getMpermission().getMpid();
                    userid = lm.get(i).getMid();
                }
            }
        }
        //Redirect to right page and set session
        if (valid == true) {
            login = 1;
            String newpage = "http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/MemberPanel.xhtml";
            if (permission == 1) {
                newpage = "http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/AdminPanel.xhtml";
            } else if (permission == 2) {
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
        } else {
            try {
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.sendRedirect("http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/Login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String GETLOGIN() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            return "none";
        } else {
            return "inline";
        }
    }

    public Members GETACCOUNT() {
        Members account = new Members();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            account.setMid(Integer.parseInt(session.getAttribute("userid").toString()));
            account = member1Facade.find(account.getMid());
            MemberEntity = account;
        } else {
            account = null;
        }
        return account;
    }

    public void LOGOUT() {
        String newpage = "http://localhost:8080/RoyalEducationalAcademyPortalSite-war/";
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            HttpSession session = request.getSession(true);
            session.removeAttribute("login");
            session.removeAttribute("userid");
            session.removeAttribute("permission");
            MemberEntity = new Members();
            NewMemberEntity = new Members();
            response.sendRedirect(newpage);
        } catch (Exception ex) {
        }
    }

    public void SAVEACCOUNT() {
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
}
