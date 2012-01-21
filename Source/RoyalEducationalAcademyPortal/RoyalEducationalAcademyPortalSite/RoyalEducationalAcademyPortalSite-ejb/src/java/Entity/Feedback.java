/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SVN - Team
 */
@Entity
@Table(name = "FEEDBACK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFid", query = "SELECT f FROM Feedback f WHERE f.fbid = :fbid"),
    @NamedQuery(name = "Feedback.findByFbquestion", query = "SELECT f FROM Feedback f WHERE f.fbquestion = :fbquestion")})
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "FBID")
    private Integer fbid;
    @Size(max = 1073741823)
    @Column(name = "FBQUESTION")
    private String fbquestion;
    @Column(name = "FBDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fbdate;
    @JoinColumn(name = "MID", referencedColumnName = "MID")
    @ManyToOne
    private Members mid;

    public Feedback() {
    }

    public Feedback(Integer fid) {
        this.fbid = fid;
    }

    public Integer getFid() {
        return fbid;
    }

    public void setFid(Integer fid) {
        this.fbid = fid;
    }

    public String getFbquestion() {
        return fbquestion;
    }

    public void setFbquestion(String fquestion) {
        this.fbquestion = fquestion;
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
        return "Entity.Feedback[ fid=" + fbid + " ]";
    }

    /**
     * @return the fbdate
     */
    public Date getFbdate() {
        return fbdate;
    }

    /**
     * @param fbdate the fbdate to set
     */
    public void setFbdate(Date fbdate) {
        this.fbdate = fbdate;
    }
    
}
