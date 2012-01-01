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
@Table(name = "MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByMid", query = "SELECT m FROM Member1 m WHERE m.mid = :mid"),
    @NamedQuery(name = "Member1.findByMusername", query = "SELECT m FROM Member1 m WHERE m.musername = :musername"),
    @NamedQuery(name = "Member1.findByMpassword", query = "SELECT m FROM Member1 m WHERE m.mpassword = :mpassword"),
    @NamedQuery(name = "Member1.findByMfullname", query = "SELECT m FROM Member1 m WHERE m.mfullname = :mfullname"),
    @NamedQuery(name = "Member1.findByMadress", query = "SELECT m FROM Member1 m WHERE m.madress = :madress"),
    @NamedQuery(name = "Member1.findByMemail", query = "SELECT m FROM Member1 m WHERE m.memail = :memail"),
    @NamedQuery(name = "Member1.findByMbirthdate", query = "SELECT m FROM Member1 m WHERE m.mbirthdate = :mbirthdate")})
public class Member1 implements Serializable {
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
    @JoinColumn(name = "MPERMISSION", referencedColumnName = "MPID")
    @ManyToOne
    private Memberpermission mpermission;
    @OneToMany(mappedBy = "mid")
    private List<Markassiment> markassimentList;
    @OneToMany(mappedBy = "mid")
    private List<Submitassiment> submitassimentList;
    @OneToMany(mappedBy = "mid")
    private List<Classmember> classmemberList;
    @OneToMany(mappedBy = "mid")
    private List<Feedback> feedbackList;

    public Member1() {
    }

    public Member1(Integer mid) {
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

    public Memberpermission getMpermission() {
        return mpermission;
    }

    public void setMpermission(Memberpermission mpermission) {
        this.mpermission = mpermission;
    }

    @XmlTransient
    public List<Markassiment> getMarkassimentList() {
        return markassimentList;
    }

    public void setMarkassimentList(List<Markassiment> markassimentList) {
        this.markassimentList = markassimentList;
    }

    @XmlTransient
    public List<Submitassiment> getSubmitassimentList() {
        return submitassimentList;
    }

    public void setSubmitassimentList(List<Submitassiment> submitassimentList) {
        this.submitassimentList = submitassimentList;
    }

    @XmlTransient
    public List<Classmember> getClassmemberList() {
        return classmemberList;
    }

    public void setClassmemberList(List<Classmember> classmemberList) {
        this.classmemberList = classmemberList;
    }

    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
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
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Member1[ mid=" + mid + " ]";
    }
    
}