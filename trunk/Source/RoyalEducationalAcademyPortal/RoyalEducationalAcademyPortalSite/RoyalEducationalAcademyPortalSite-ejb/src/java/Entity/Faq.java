/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SVN - Team
 */
@Entity
@Table(name = "FAQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faq.findAll", query = "SELECT f FROM Faq f"),
    @NamedQuery(name = "Faq.findByFid", query = "SELECT f FROM Faq f WHERE f.fid = :fid"),
    @NamedQuery(name = "Faq.findByFquestion", query = "SELECT f FROM Faq f WHERE f.fquestion = :fquestion"),
    @NamedQuery(name = "Faq.findByFanswer", query = "SELECT f FROM Faq f WHERE f.fanswer = :fanswer")})
public class Faq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FID")
    private Integer fid;
    @Size(max = 1073741823)
    @Column(name = "FQUESTION")
    private String fquestion;
    @Size(max = 1073741823)
    @Column(name = "FANSWER")
    private String fanswer;

    public Faq() {
    }

    public Faq(Integer fid) {
        this.fid = fid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFquestion() {
        return fquestion;
    }

    public void setFquestion(String fquestion) {
        this.fquestion = fquestion;
    }

    public String getFanswer() {
        return fanswer;
    }

    public void setFanswer(String fanswer) {
        this.fanswer = fanswer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faq)) {
            return false;
        }
        Faq other = (Faq) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Faq[ fid=" + fid + " ]";
    }
    
}
