/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Baths;
import beans.Classes;
import beans.Courses;
import beans.Sems;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import stateless.courseService;

/**
 *
 * @author Kai
 */
@ManagedBean
@RequestScoped
public class showCourseController {
    @EJB
    private courseService courseService;    
    private List<Sems> listSems;
    private List<Courses> listCourses;
    private List<Classes> listClasses;

    public List<Classes> getListClasses() {
        return listClasses;
    }

    public void setListClasses(List<Classes> listClasses) {
        this.listClasses = listClasses;
    }

    public List<Courses> getListCourses() {
        return listCourses;
    }

    public void setListCourses(List<Courses> listCourses) {
        this.listCourses = listCourses;
    }

    public List<Sems> getListSems() {
        return listSems;
    }

    public void setListSems(List<Sems> listSems) {
        this.listSems = listSems;
    }
    
    /** Creates a new instance of showCourseController */
    public showCourseController() {  
    }
    
    public List<Baths> getBaths(){
        return courseService.allBaths();
    }    
        
    public List<Sems> getSemsByBath(int id){
        return courseService.getSemsByBath(id);         
    }
    
    public List<Courses> getCoursesBySem(int id){
        return courseService.getCoursesBySem(id);
    }
    
    public List<Classes> getClassesByCourse(int id){
        return courseService.getClassesByCourse(id);
    }
    
    public String redirectSem(int id){
        listSems = getSemsByBath(id);
        return "semPage";
    }
    
    public String redirectCourse(int id){
        listCourses = getCoursesBySem(id);
        return "test";
    }
    
    public String redirectClasses(int id){
        listClasses = getClassesByCourse(id);
        return "classPage";
    }
}
