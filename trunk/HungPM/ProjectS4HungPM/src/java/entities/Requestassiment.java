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
@Table(name = "REQUESTASSIMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requestassiment.findAll", query = "SELECT r FROM Requestassiment r"),
    @NamedQuery(name = "Requestassiment.findByRaid", query = "SELECT r FROM Requestassiment r WHERE r.raid = :raid"),
    @NamedQuery(name = "Requestassiment.findByRaname", query = "SELECT r FROM Requestassiment r WHERE r.raname = :raname"),
    @NamedQuery(name = "Requestassiment.findByRainfo", query = "SELECT r FROM Requestassiment r WHERE r.rainfo = :rainfo"),
    @NamedQuery(name = "Requestassiment.findByRafilename", query = "SELECT r FROM Requestassiment r WHERE r.rafilename = :rafilename")})
public class Requestassiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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
    @JoinColumn(name = "STUFFMID", referencedColumnName = "MID")
    @ManyToOne
    private Members stuffmid;
    @JoinColumn(name = "CID", referencedColumnName = "CID")
    @ManyToOne
    private Class cid;
    @OneToMany(mappedBy = "raid")
    private Collection<Markassiment> markassimentCollection;
    @OneToMany(mappedBy = "raid")
    private Collection<Submitassiment> submitassimentCollection;

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

    public Members getStuffmid() {
        return stuffmid;
    }

    public void setStuffmid(Members stuffmid) {
        this.stuffmid = stuffmid;
    }

    public Class getCid() {
        return cid;
    }

    public void setCid(Class cid) {
        this.cid = cid;
    }

    @XmlTransient
    public Collection<Markassiment> getMarkassimentCollection() {
        return markassimentCollection;
    }

    public void setMarkassimentCollection(Collection<Markassiment> markassimentCollection) {
        this.markassimentCollection = markassimentCollection;
    }

    @XmlTransient
    public Collection<Submitassiment> getSubmitassimentCollection() {
        return submitassimentCollection;
    }

    public void setSubmitassimentCollection(Collection<Submitassiment> submitassimentCollection) {
        this.submitassimentCollection = submitassimentCollection;
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
        return "entities.Requestassiment[ raid=" + raid + " ]";
    }
    
}
