/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Feedback;
import Entity.Members;
import Session.FeedbackFacade;
import Session.MembersFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "FeedBackController")
@RequestScoped
public class FeedBackController {

    @EJB
    private MembersFacade member1Facade;
    @EJB
    private FeedbackFacade feedbackFacade;
    private Feedback NewFeedBack;
    private int SeletedMemberID;

    /** Creates a new instance of FeedBackController */
    public FeedBackController() {
        NewFeedBack = new Feedback();
        SeletedMemberID = 1;
    }

    public void DELETE(int id) {
        feedbackFacade.DELETE(id);
    }

    public List<Feedback> SHOWFEEDBACKBYMEMBER(Members ms) {
        List<Feedback> rs = feedbackFacade.findAll();
        List<Feedback> rt = new ArrayList<Feedback>();
        for (int i = 0; i < rs.size(); i++) {
            if (rs.get(i).getMid().equals(ms)) {
                rt.add(rs.get(i));
            }
        }
        return rt;
    }

    public void ADDNEWFEEDBACK() {
        if (NewFeedBack.getFbquestion().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Content is empty.", "Type what you want to feedback."));
        } else {
            NewFeedBack.setFbdate(new Date());
            NewFeedBack.setMid(new Members(SeletedMemberID));
            feedbackFacade.ADDNEWFEEDBACK(NewFeedBack);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Feedback successful sent.", "Your feedback is complete."));
        }
        //feedbackFacade.ADDNEWFEEDBACK(NewFeedBack);
    }

    public Members GETACCOUNT() {
        Members account = new Members();
        account.setMid(1);
        account = member1Facade.find(account.getMid());
        return account;
    }

    /**
     * @return the NewFeedBack
     */
    public Feedback getNewFeedBack() {
        return NewFeedBack;
    }

    /**
     * @param NewFeedBack the NewFeedBack to set
     */
    public void setNewFeedBack(Feedback NewFeedBack) {
        this.NewFeedBack = NewFeedBack;
    }

    /**
     * @return the SeletedMemberID
     */
    public int getSeletedMemberID() {
        return SeletedMemberID;
    }

    /**
     * @param SeletedMemberID the SeletedMemberID to set
     */
    public void setSeletedMemberID(int SeletedMemberID) {
        this.SeletedMemberID = SeletedMemberID;
    }
}
