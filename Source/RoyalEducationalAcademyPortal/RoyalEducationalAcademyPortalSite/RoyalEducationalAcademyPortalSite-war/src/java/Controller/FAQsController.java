/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Faq;
import Session.FaqFacade;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "FAQsController")
@RequestScoped
public class FAQsController {
    private String newAnswer, newQuestion; 
    
    @EJB
    private FaqFacade faqFacade;
    
    

    /** Creates a new instance of FAQsController */
    public FAQsController() {
    }
    
    public void DELETE(String id){
        faqFacade.DELETE(Integer.parseInt(id));
    }
    
    public void newFAQs(){
        try {
            faqFacade.newFAQs(newQuestion, newAnswer);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.sendRedirect("http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/FAQs.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FAQsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Faq> SHOWALL(){
        return faqFacade.findAll();
    }

    /**
     * @return the newAnswer
     */
    public String getNewAnswer() {
        return newAnswer;
    }

    /**
     * @param newAnswer the newAnswer to set
     */
    public void setNewAnswer(String newAnswer) {
        this.newAnswer = newAnswer;
    }

    /**
     * @return the newQuestion
     */
    public String getNewQuestion() {
        return newQuestion;
    }

    /**
     * @param newQuestion the newQuestion to set
     */
    public void setNewQuestion(String newQuestion) {
        this.newQuestion = newQuestion;
    }
}
