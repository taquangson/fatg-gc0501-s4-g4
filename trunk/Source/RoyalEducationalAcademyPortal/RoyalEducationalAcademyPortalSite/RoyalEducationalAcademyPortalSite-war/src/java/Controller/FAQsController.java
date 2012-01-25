/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Faq;
import Session.FaqFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
    //Deleting a FAQs

    public void DELETE(String id) {
        faqFacade.DELETE(Integer.parseInt(id));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "FAQs Deleted!", "Completed!"));
    }
    //Creating a new FAQs

    public void newFAQs() {
        try {
            faqFacade.newFAQs(newQuestion, newAnswer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "FAQs added!", "Completed!"));
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            response.sendRedirect("http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/FAQs.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(FAQsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Show all FAQs by List

    public List<Faq> SHOWALL() {
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
