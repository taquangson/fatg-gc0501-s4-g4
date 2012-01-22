/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classmember;
import Entity.Course;
import Entity.Members;
import Entity.Requestassiment;
import Session.ClassFacade;
import Session.ClassmemberFacade;
import Session.CourseFacade;
import Session.RequestassimentFacade;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "AssimentController")
@RequestScoped
public class AssimentController {
    @EJB
    private ClassmemberFacade classmemberFacade;
    @EJB
    private RequestassimentFacade requestassimentFacade;
    @EJB
    private CourseFacade courseFacade;
    @EJB
    private ClassFacade classFacade;

    int selectedCourse;
    int seletedClass;
    /** Creates a new instance of AssimentController */
    public AssimentController() {
        selectedCourse = 0;
        seletedClass = 0;
    }
    public List<Requestassiment> GETASSIMENTBYACCOUNT(){
//        Classmember cmems = new Classmember();
//        cmems.setMid(mem);
//        Classmember cmem = classmemberFacade.find(cmems);
//        Entity.Class classes = cmem.getCid();
        Entity.Class classes = new Entity.Class();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        classes = classFacade.find(Integer.parseInt(request.getParameter("classid")));
        List<Requestassiment> rs = new ArrayList<Requestassiment>();
        rs = classes.getRequestassimentList();
        return rs;
    }
    public void SETUPASSIMENTVIEW(){
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        HttpSession session = request.getSession(true);
//        session.setAttribute("login", 1);
        seletedClass = Integer.parseInt(request.getParameter("classid"));
        selectedCourse = Integer.parseInt(request.getParameter("courseid"));
    }
    public Entity.Class GETSELECTEDCLASS(){
        Entity.Class classes = new Entity.Class();
        classes = classFacade.find(seletedClass);
        return classes;
    }
    public Course GETSELECTEDCOURSE(){
        Course cou = new Course();
        cou = courseFacade.find(selectedCourse);
        return cou;
    }
}
