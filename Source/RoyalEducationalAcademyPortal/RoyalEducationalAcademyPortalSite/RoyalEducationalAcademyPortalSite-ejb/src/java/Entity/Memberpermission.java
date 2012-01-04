/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
 * @author SVN - Team
 */
@Entity
@Table(name = "MEMBERPERMISSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memberpermission.findAll", query = "SELECT m FROM Memberpermission m"),
    @NamedQuery(name = "Memberpermission.findByMpid", query = "SELECT m FROM Memberpermission m WHERE m.mpid = :mpid"),
    @NamedQuery(name = "Memberpermission.findByMpname", query = "SELECT m FROM Memberpermission m WHERE m.mpname = :mpname")})
public class Memberpermission implements Serializable {
    @OneToMany(mappedBy = "mpermission")
    private List<Members> membersList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MPID")
    private Integer mpid;
    @Size(max = 50)
    @Column(name = "MPNAME")
    private String mpname;
    @OneToMany(mappedBy = "mpermission")
    private List<Members> member1List;

    public Memberpermission() {
    }

    public Memberpermission(Integer mpid) {
        this.mpid = mpid;
    }

    public Integer getMpid() {
        return mpid;
    }

    public void setMpid(Integer mpid) {
        this.mpid = mpid;
    }

    public String getMpname() {
        return mpname;
    }

    public void setMpname(String mpname) {
        this.mpname = mpname;
    }

    @XmlTransient
    public List<Members> getMember1List() {
        return member1List;
    }

    public void setMember1List(List<Members> member1List) {
        this.member1List = member1List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mpid != null ? mpid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memberpermission)) {
            return false;
        }
        Memberpermission other = (Memberpermission) object;
        if ((this.mpid == null && other.mpid != null) || (this.mpid != null && !this.mpid.equals(other.mpid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Memberpermission[ mpid=" + mpid + " ]";
    }

    @XmlTransient
    public List<Members> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Members> membersList) {
        this.membersList = membersList;
    }
    
}
