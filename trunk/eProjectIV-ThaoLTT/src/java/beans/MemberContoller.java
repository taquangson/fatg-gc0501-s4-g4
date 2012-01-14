/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Members;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import stateless.MemberService;

/**
 *
 * @author Thao
 */
@ManagedBean
@RequestScoped
public class MemberContoller {
    @EJB
    private MemberService memberService;
    int id;
    String userName;
    String password;
    Date birthday;
    String email;
    Members mem;
    byte[] image;
    String address;
    String fullname;
    String newPwd1;
    String newPwd2;
    String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNewPwd1() {
        return newPwd1;
    }

    public void setNewPwd1(String newPwd1) {
        this.newPwd1 = newPwd1;
    }

    public String getNewPwd2() {
        return newPwd2;
    }

    public void setNewPwd2(String newPwd2) {
        this.newPwd2 = newPwd2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Members getMem() {
        return mem;
    }

    public void setMem(Members mem) {
        this.mem = mem;
    }
     
    /** Creates a new instance of MemberContoller */
    public MemberContoller() {
    }
    
    public String convertToMd5(String pwd) throws NoSuchAlgorithmException{
            String pwdMd5;
     
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
     
            byte byteData[] = md.digest();
     
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            pwdMd5 = sb.toString();
            return pwdMd5;
    }
    
    public String loginMember() throws NoSuchAlgorithmException{  
        
        String pwdMd5 = convertToMd5(password);
        mem = memberService.getMemberByUserAndPwd(userName, pwdMd5);
        id = mem.getMid();        
        
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession ss = rq.getSession(true);
        ss.setAttribute("id", id);
        MemberById();
        return "editMember";
    }
    
    public void MemberById(){
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession ss = rq.getSession(true);
        
        id = Integer.parseInt(ss.getAttribute("id").toString());
        mem = new Members();
        mem = memberService.getMemberById(id);
    }
    
    public boolean checkValidate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date curDate = new Date();
        dateFormat.format(curDate);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date inpDate = birthday;
        format.format(inpDate);
        
        if(!email.contains("@")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid email", "Error"));        
            return false;
        }
        int i = email.indexOf("@");
        if(email.indexOf(".") == i+1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid email", "Error"));        
            return false;
        }
        if(!email.substring(i+1).contains("."))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid email", "Error"));        
            return false;
        }
        if(inpDate.after(curDate)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid date", "Error"));        
            return false;
        }else{
            return true;
        }
    }
    
    public String editMemInfo()
    {
        MemberById();
        mem.setMfullname(fullname);
        mem.setMadress(address);
        mem.setMemail(email);
        mem.setMbirthdate(birthday);
        if(checkValidate()){
            memberService.editMemInfo(mem);
            return "success";
        } else{
            return null;
        }
    }
    
    public String editMemPwd() throws NoSuchAlgorithmException
    {
        MemberById();
        String pwdMd5;
        pwdMd5 = convertToMd5(password);
        if(!mem.getMpassword().equals(pwdMd5)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Current password is not correct!", "Error"));        
            return null;
        } else if(!newPwd1.equals(newPwd2))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"The re-type new password must like new password!", "Error"));        
            return null;
        } else{
            String newPwd;
            newPwd = convertToMd5(newPwd1);
            mem.setMpassword(newPwd);
            memberService.editMemInfo(mem);
            return "success";
        }
    }
    
    public String editMemAva(){
        MemberById();
        mem.setMavarta(image);
        memberService.editMemInfo(mem);
        return "success";
    }
    
    public void handleFileUpload(FileUploadEvent event)
    {
        String uploadedFolder = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadFolder");

        ExternalContext extContext = FacesContext.getCurrentInstance().
                getExternalContext();
        File result = new File(extContext.getRealPath("//" + uploadedFolder) + "//" + event.getFile().getFileName());
        System.out.println(result.getAbsolutePath());

        try {
            image = new byte[(int)event.getFile().getSize()];
            
            FileInputStream fis = new FileInputStream(result);
            fis.read(image);
            fis.close();
            System.out.println(image);

        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    
    public String redirectEditInformation(){
        MemberById();        
        id = mem.getMid();
        fullname = mem.getMfullname();
        address = mem.getMadress();
        email = mem.getMemail();
        return "editMemberInformation";
    }
    
    public String redirectEditPassword(){
        MemberById();
        return "editMemberInformation";
    }
    
    public String redirectEditAvatar(){
        MemberById();
        return "editMemberAvatar";
    }
    
    public String cancelEdit(){
        MemberById();
        return "editMember";
    }
}
