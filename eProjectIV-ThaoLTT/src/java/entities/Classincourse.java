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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thao
 */
@Entity
@Table(name = "CLASSINCOURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classincourse.findAll", query = "SELECT c FROM Classincourse c"),
    @NamedQuery(name = "Classincourse.findByCcid", query = "SELECT c FROM Classincourse c WHERE c.ccid = :ccid")})
public class Classincourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCID")
    private Integer ccid;
    @JoinColumn(name = "COURSE", referencedColumnName = "CID")
    @ManyToOne
    private Course course;
    @JoinColumn(name = "CLASS", referencedColumnName = "CID")
    @ManyToOne
    private Class class1;

    public Classincourse() {
    }

    public Classincourse(Integer ccid) {
        this.ccid = ccid;
    }

    public Integer getCcid() {
        return ccid;
    }

    public void setCcid(Integer ccid) {
        this.ccid = ccid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Class getClass1() {
        return class1;
    }

    public void setClass1(Class class1) {
        this.class1 = class1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccid != null ? ccid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classincourse)) {
            return false;
        }
        Classincourse other = (Classincourse) object;
        if ((this.ccid == null && other.ccid != null) || (this.ccid != null && !this.ccid.equals(other.ccid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Classincourse[ ccid=" + ccid + " ]";
    }
    
}
