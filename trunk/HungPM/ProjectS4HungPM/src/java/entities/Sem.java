/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kai
 */
@Entity
@Table(name = "SEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sem.findAll", query = "SELECT s FROM Sem s"),
    @NamedQuery(name = "Sem.findBySid", query = "SELECT s FROM Sem s WHERE s.sid = :sid"),
    @NamedQuery(name = "Sem.findBySname", query = "SELECT s FROM Sem s WHERE s.sname = :sname")})
public class Sem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SID")
    private Integer sid;
    @Size(max = 80)
    @Column(name = "SNAME")
    private String sname;
    @JoinColumn(name = "BID", referencedColumnName = "BID")
    @ManyToOne
    private Bath bid;
    @OneToMany(mappedBy = "sid")
    private Collection<Course> courseCollection;

    public Sem() {
    }

    public Sem(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Bath getBid() {
        return bid;
    }

    public void setBid(Bath bid) {
        this.bid = bid;
    }

    @XmlTransient
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sem)) {
            return false;
        }
        Sem other = (Sem) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sem[ sid=" + sid + " ]";
    }
    
}
