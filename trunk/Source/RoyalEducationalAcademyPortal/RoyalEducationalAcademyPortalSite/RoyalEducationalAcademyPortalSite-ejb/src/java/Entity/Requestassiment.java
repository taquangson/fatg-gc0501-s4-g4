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
@Table(name = "REQUESTASSIMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requestassiment.findAll", query = "SELECT r FROM Requestassiment r"),
    @NamedQuery(name = "Requestasssiment.findByRaid", query = "SELECT r FROM Requestassiment r WHERE r.raid = :raid"),
    @NamedQuery(name = "Requestassiment.findByRaname", query = "SELECT r FROM Requestassiment r WHERE r.raname = :raname"),
    @NamedQuery(name = "Requestassiment.findByRainfo", query = "SELECT r FROM Requestassiment r WHERE r.rainfo = :rainfo"),
    @NamedQuery(name = "Requestassiment.findByRafilename", query = "SELECT r FROM Requestassiment r WHERE r.rafilename = :rafilename"),
    @NamedQuery(name = "Requestassiment.findByRadate", query = "SELECT r FROM Requestassiment r WHERE r.radate = :radate"),
    @NamedQuery(name = "Requestassiment.findByRadeadline", query = "SELECT r FROM Requestassiment r WHERE r.radeadline = :radeadline")})
public class Requestassiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "RAID")
    private Integer raid;
    @Size(max = 80)
    @Column(name = "RANAME")
    private String raname;
    @Size(max = 1073741823)
    @Column(name = "RAINFO")
    private String rainfo;
    @Size(max = 80)
    @Column(name = "RAFILENAME")
    private String rafilename;
    @Column(name = "RADATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date radate;
    @Column(name = "RADEADLINE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date radeadline;
    @JoinColumn(name = "STUFFMID", referencedColumnName = "MID")
    @ManyToOne
    private Members stuffmid;
    @JoinColumn(name = "CID2", referencedColumnName = "CID")
    @ManyToOne
    private Course cid2;
    @JoinColumn(name = "CID", referencedColumnName = "CID")
    @ManyToOne
    private Class cid;

    public Requestassiment() {
    }

    public Requestassiment(Integer raid) {
        this.raid = raid;
    }

    public Integer getRaid() {
        return raid;
    }

    public void setRaid(Integer raid) {
        this.raid = raid;
    }

    public String getRaname() {
        return raname;
    }

    public void setRaname(String raname) {
        this.raname = raname;
    }

    public String getRainfo() {
        return rainfo;
    }

    public void setRainfo(String rainfo) {
        this.rainfo = rainfo;
    }

    public String getRafilename() {
        return rafilename;
    }

    public void setRafilename(String rafilename) {
        this.rafilename = rafilename;
    }

    public Date getRadate() {
        return radate;
    }

    public void setRadate(Date radate) {
        this.radate = radate;
    }

    public Date getRadeadline() {
        return radeadline;
    }

    public void setRadeadline(Date radeadline) {
        this.radeadline = radeadline;
    }

    public Members getStuffmid() {
        return stuffmid;
    }

    public void setStuffmid(Members stuffmid) {
        this.stuffmid = stuffmid;
    }

    public Course getCid2() {
        return cid2;
    }

    public void setCid2(Course cid2) {
        this.cid2 = cid2;
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
        hash += (raid != null ? raid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requestassiment)) {
            return false;
        }
        Requestassiment other = (Requestassiment) object;
        if ((this.raid == null && other.raid != null) || (this.raid != null && !this.raid.equals(other.raid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Requestassiment[ raid=" + raid + " ]";
    }
    
}
