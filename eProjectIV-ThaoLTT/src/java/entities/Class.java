/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Thao
 */
@Entity
@Table(name = "CLASS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c"),
    @NamedQuery(name = "Class.findByCid", query = "SELECT c FROM Class c WHERE c.cid = :cid"),
    @NamedQuery(name = "Class.findByCname", query = "SELECT c FROM Class c WHERE c.cname = :cname")})
public class Class implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CID")
    private Integer cid;
    @Size(max = 80)
    @Column(name = "CNAME")
    private String cname;
    @OneToMany(mappedBy = "class1")
    private List<Classincourse> classincourseList;
    @OneToMany(mappedBy = "cid")
    private List<Requestassiment> requestassimentList;
    @OneToMany(mappedBy = "cid")
    private List<Classmember> classmemberList;

    public Class() {
    }

    public Class(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @XmlTransient
    public List<Classincourse> getClassincourseList() {
        return classincourseList;
    }

    public void setClassincourseList(List<Classincourse> classincourseList) {
        this.classincourseList = classincourseList;
    }

    @XmlTransient
    public List<Requestassiment> getRequestassimentList() {
        return requestassimentList;
    }

    public void setRequestassimentList(List<Requestassiment> requestassimentList) {
        this.requestassimentList = requestassimentList;
    }

    @XmlTransient
    public List<Classmember> getClassmemberList() {
        return classmemberList;
    }

    public void setClassmemberList(List<Classmember> classmemberList) {
        this.classmemberList = classmemberList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Class[ cid=" + cid + " ]";
    }
    
}
