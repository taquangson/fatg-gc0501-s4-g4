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
@Table(name = "BATH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bath.findAll", query = "SELECT b FROM Bath b"),
    @NamedQuery(name = "Bath.findByBid", query = "SELECT b FROM Bath b WHERE b.bid = :bid"),
    @NamedQuery(name = "Bath.findByBname", query = "SELECT b FROM Bath b WHERE b.bname = :bname")})
public class Bath implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BID")
    private Integer bid;
    @Size(max = 80)
    @Column(name = "BNAME")
    private String bname;
    @OneToMany(mappedBy = "bid")
    private List<Sem> semList;

    public Bath() {
    }

    public Bath(Integer bid) {
        this.bid = bid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    @XmlTransient
    public List<Sem> getSemList() {
        return semList;
    }

    public void setSemList(List<Sem> semList) {
        this.semList = semList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bid != null ? bid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bath)) {
            return false;
        }
        Bath other = (Bath) object;
        if ((this.bid == null && other.bid != null) || (this.bid != null && !this.bid.equals(other.bid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Bath[ bid=" + bid + " ]";
    }
    
}
