/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SVN - Team
 */
@Entity
@Table(name = "CLASSMEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classmember.findAll", query = "SELECT c FROM Classmember c"),
    @NamedQuery(name = "Classmember.findByCmid", query = "SELECT c FROM Classmember c WHERE c.cmid = :cmid")})
public class Classmember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "CMID")
    private Integer cmid;
    @JoinColumn(name = "MID", referencedColumnName = "MID")
    @ManyToOne
    private Members mid;
    @JoinColumn(name = "CID", referencedColumnName = "CID")
    @ManyToOne
    private Class cid;

    public Classmember() {
    }

    public Classmember(Integer cmid) {
        this.cmid = cmid;
    }

    public Integer getCmid() {
        return cmid;
    }

    public void setCmid(Integer cmid) {
        this.cmid = cmid;
    }

    public Members getMid() {
        return mid;
    }

    public void setMid(Members mid) {
        this.mid = mid;
    }

    public Class getCid() {
        return cid;
    }

    public void setCid(Class cid) {
        this.cid = cid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmid != null ? cmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classmember)) {
            return false;
        }
        Classmember other = (Classmember) object;
        if ((this.cmid == null && other.cmid != null) || (this.cmid != null && !this.cmid.equals(other.cmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Classmember[ cmid=" + cmid + " ]";
    }
    
}
