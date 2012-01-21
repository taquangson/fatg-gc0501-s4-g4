/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SVN - Team
 */
@Entity
@Table(name = "MEMBERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Members.findAll", query = "SELECT m FROM Members m"),
    @NamedQuery(name = "Members.findByMid", query = "SELECT m FROM Members m WHERE m.mid = :mid"),
    @NamedQuery(name = "Members.findByMusername", query = "SELECT m FROM Members m WHERE m.musername = :musername"),
    @NamedQuery(name = "Members.findByMpassword", query = "SELECT m FROM Members m WHERE m.mpassword = :mpassword"),
    @NamedQuery(name = "Members.findByMfullname", query = "SELECT m FROM Members m WHERE m.mfullname = :mfullname"),
    @NamedQuery(name = "Members.findByMadress", query = "SELECT m FROM Members m WHERE m.madress = :madress"),
    @NamedQuery(name = "Members.findByMemail", query = "SELECT m FROM Members m WHERE m.memail = :memail"),
    @NamedQuery(name = "Members.findByMbirthdate", query = "SELECT m FROM Members m WHERE m.mbirthdate = :mbirthdate")})
public class Members implements Serializable {
    @Column(name =     "MBIRTHDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mbirthdate;
    @Lob
    @Column(name = "MAVARTA")
    private byte[] mavarta;
    @OneToMany(mappedBy = "stuffmid")
    private List<Requestassiment> requestassimentList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MID")
    private Integer mid;
    @Size(max = 32)
    @Column(name = "MUSERNAME")
    private String musername;
    @Size(max = 32)
    @Column(name = "MPASSWORD")
    private String mpassword;
    @Size(max = 80)
    @Column(name = "MFULLNAME")
    private String mfullname;
    @Size(max = 80)
    @Column(name = "MADRESS")
    private String madress;
    @Size(max = 80)
    @Column(name = "MEMAIL")
    private String memail;
    @JoinColumn(name = "MPERMISSION", referencedColumnName = "MPID")
    @ManyToOne
    private Memberpermission mpermission;

    public Members() {
    }

    public Members(Integer mid) {
        this.mid = mid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }

    public String getMfullname() {
        return mfullname;
    }

    public void setMfullname(String mfullname) {
        this.mfullname = mfullname;
    }

    public String getMadress() {
        return madress;
    }

    public void setMadress(String madress) {
        this.madress = madress;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    public Memberpermission getMpermission() {
        return mpermission;
    }

    public void setMpermission(Memberpermission mpermission) {
        this.mpermission = mpermission;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Members)) {
            return false;
        }
        Members other = (Members) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Members[ mid=" + mid + " ]";
    }

    @XmlTransient
    public List<Requestassiment> getRequestassimentList() {
        return requestassimentList;
    }

    public void setRequestassimentList(List<Requestassiment> requestassimentList) {
        this.requestassimentList = requestassimentList;
    }

    public Date getMbirthdate() {
        return mbirthdate;
    }

    public void setMbirthdate(Date mbirthdate) {
        this.mbirthdate = mbirthdate;
    }

    public byte[] getMavarta() {
        return mavarta;
    }

    public void setMavarta(byte[] mavarta) {
        this.mavarta = mavarta;
    }
    
}
