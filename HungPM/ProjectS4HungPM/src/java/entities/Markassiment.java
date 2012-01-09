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
 * @author Kai
 */
@Entity
@Table(name = "MARKASSIMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Markassiment.findAll", query = "SELECT m FROM Markassiment m"),
    @NamedQuery(name = "Markassiment.findByMaid", query = "SELECT m FROM Markassiment m WHERE m.maid = :maid"),
    @NamedQuery(name = "Markassiment.findByMamark", query = "SELECT m FROM Markassiment m WHERE m.mamark = :mamark")})
public class Markassiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAID")
    private Integer maid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MAMARK")
    private Double mamark;
    @JoinColumn(name = "SAID", referencedColumnName = "SAID")
    @ManyToOne
    private Submitassiment said;
    @JoinColumn(name = "RAID", referencedColumnName = "RAID")
    @ManyToOne
    private Requestassiment raid;
    @JoinColumn(name = "MID", referencedColumnName = "MID")
    @ManyToOne
    private Members mid;

    public Markassiment() {
    }

    public Markassiment(Integer maid) {
        this.maid = maid;
    }

    public Integer getMaid() {
        return maid;
    }

    public void setMaid(Integer maid) {
        this.maid = maid;
    }

    public Double getMamark() {
        return mamark;
    }

    public void setMamark(Double mamark) {
        this.mamark = mamark;
    }

    public Submitassiment getSaid() {
        return said;
    }

    public void setSaid(Submitassiment said) {
        this.said = said;
    }

    public Requestassiment getRaid() {
        return raid;
    }

    public void setRaid(Requestassiment raid) {
        this.raid = raid;
    }

    public Members getMid() {
        return mid;
    }

    public void setMid(Members mid) {
        this.mid = mid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maid != null ? maid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Markassiment)) {
            return false;
        }
        Markassiment other = (Markassiment) object;
        if ((this.maid == null && other.maid != null) || (this.maid != null && !this.maid.equals(other.maid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Markassiment[ maid=" + maid + " ]";
    }
    
}
