/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thao
 */
@Entity
@Table(name = "FEEDBACK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFbid", query = "SELECT f FROM Feedback f WHERE f.fbid = :fbid"),
    @NamedQuery(name = "Feedback.findByFbquestion", query = "SELECT f FROM Feedback f WHERE f.fbquestion = :fbquestion")})
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FBID")
    private Integer fbid;
    @Size(max = 1073741823)
    @Column(name = "FBQUESTION")
    private String fbquestion;
    @JoinColumn(name = "MID", referencedColumnName = "MID")
    @ManyToOne
    private Members mid;

    public Feedback() {
    }

    public Feedback(Integer fbid) {
        this.fbid = fbid;
    }

    public Integer getFbid() {
        return fbid;
    }

    public void setFbid(Integer fbid) {
        this.fbid = fbid;
    }

    public String getFbquestion() {
        return fbquestion;
    }

    public void setFbquestion(String fbquestion) {
        this.fbquestion = fbquestion;
    }

    public Members getMid() {
        return mid;
    }

    public void setMid(Members mid) {
        this.mid = mid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fbid != null ? fbid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.fbid == null && other.fbid != null) || (this.fbid != null && !this.fbid.equals(other.fbid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Feedback[ fbid=" + fbid + " ]";
    }
    
}
