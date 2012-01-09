/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Kai
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
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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
    @Column(name = "MBIRTHDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mbirthdate;
    @Lob
    @Column(name = "MAVARTA")
    private byte[] mavarta;
    @OneToMany(mappedBy = "stuffmid")
    private Collection<Requestassiment> requestassimentCollection;
    @OneToMany(mappedBy = "mid")
    private Collection<Markassiment> markassimentCollection;
    @JoinColumn(name = "MPERMISSION", referencedColumnName = "MPID")
    @ManyToOne
    private Memberpermission mpermission;
    @OneToMany(mappedBy = "mid")
    private Collection<Submitassiment> submitassimentCollection;
    @OneToMany(mappedBy = "mid")
    private Collection<Classmember> classmemberCollection;
    @OneToMany(mappedBy = "mid")
    private Collection<Feedback> feedbackCollection;

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

    @XmlTransient
    public Collection<Requestassiment> getRequestassimentCollection() {
        return requestassimentCollection;
    }

    public void setRequestassimentCollection(Collection<Requestassiment> requestassimentCollection) {
        this.requestassimentCollection = requestassimentCollection;
    }

    @XmlTransient
    public Collection<Markassiment> getMarkassimentCollection() {
        return markassimentCollection;
    }

    public void setMarkassimentCollection(Collection<Markassiment> markassimentCollection) {
        this.markassimentCollection = markassimentCollection;
    }

    public Memberpermission getMpermission() {
        return mpermission;
    }

    public void setMpermission(Memberpermission mpermission) {
        this.mpermission = mpermission;
    }

    @XmlTransient
    public Collection<Submitassiment> getSubmitassimentCollection() {
        return submitassimentCollection;
    }

    public void setSubmitassimentCollection(Collection<Submitassiment> submitassimentCollection) {
        this.submitassimentCollection = submitassimentCollection;
    }

    @XmlTransient
    public Collection<Classmember> getClassmemberCollection() {
        return classmemberCollection;
    }

    public void setClassmemberCollection(Collection<Classmember> classmemberCollection) {
        this.classmemberCollection = classmemberCollection;
    }

    @XmlTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
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
        return "entities.Members[ mid=" + mid + " ]";
    }
    
}
