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
 * @author SVN - Team
 */
@Entity
@Table(name = "SUBMITASSIMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Submitassiment.findAll", query = "SELECT s FROM Submitassiment s"),
    @NamedQuery(name = "Submitassiment.findBySaid", query = "SELECT s FROM Submitassiment s WHERE s.said = :said"),
    @NamedQuery(name = "Submitassiment.findBySafilename", query = "SELECT s FROM Submitassiment s WHERE s.safilename = :safilename")})
public class Submitassiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAID")
    private Integer said;
    @Size(max = 80)
    @Column(name = "SAFILENAME")
    private String safilename;
    @OneToMany(mappedBy = "said")
    private List<Markassiment> markassimentList;
    @JoinColumn(name = "RAID", referencedColumnName = "RAID")
    @ManyToOne
    private Requestassiment raid;
    @JoinColumn(name = "MID", referencedColumnName = "MID")
    @ManyToOne
    private Member1 mid;

    public Submitassiment() {
    }

    public Submitassiment(Integer said) {
        this.said = said;
    }

    public Integer getSaid() {
        return said;
    }

    public void setSaid(Integer said) {
        this.said = said;
    }

    public String getSafilename() {
        return safilename;
    }

    public void setSafilename(String safilename) {
        this.safilename = safilename;
    }

    @XmlTransient
    public List<Markassiment> getMarkassimentList() {
        return markassimentList;
    }

    public void setMarkassimentList(List<Markassiment> markassimentList) {
        this.markassimentList = markassimentList;
    }

    public Requestassiment getRaid() {
        return raid;
    }

    public void setRaid(Requestassiment raid) {
        this.raid = raid;
    }

    public Member1 getMid() {
        return mid;
    }

    public void setMid(Member1 mid) {
        this.mid = mid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (said != null ? said.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Submitassiment)) {
            return false;
        }
        Submitassiment other = (Submitassiment) object;
        if ((this.said == null && other.said != null) || (this.said != null && !this.said.equals(other.said))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Submitassiment[ said=" + said + " ]";
    }
    
}
