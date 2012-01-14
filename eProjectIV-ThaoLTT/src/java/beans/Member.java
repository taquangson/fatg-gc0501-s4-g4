/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Thao
 */
@ManagedBean
@RequestScoped
public class Member {
    private Integer mid;
    private String musername;
    private String mpassword;
    private String mfullname;
    private String madress;
    private String memail;
    private Date mbirthdate;
    private byte[] mavarta;

    public Member(Integer mid, String musername, String mpassword, String mfullname, String madress, String memail, Date mbirthdate, byte[] mavarta) {
        this.mid = mid;
        this.musername = musername;
        this.mpassword = mpassword;
        this.mfullname = mfullname;
        this.madress = madress;
        this.memail = memail;
        this.mbirthdate = mbirthdate;
        this.mavarta = mavarta;
    }

    public String getMadress() {
        return madress;
    }

    public void setMadress(String madress) {
        this.madress = madress;
    }

    public byte[] getMavarta() {
        return mavarta;
    }

    public void setMavarta(byte[] mavarta) {
        this.mavarta = mavarta;
    }

    public Date getMbirthdate() {
        return mbirthdate;
    }

    public void setMbirthdate(Date mbirthdate) {
        this.mbirthdate = mbirthdate;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    public String getMfullname() {
        return mfullname;
    }

    public void setMfullname(String mfullname) {
        this.mfullname = mfullname;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }
    /** Creates a new instance of Member */
    public Member() {
    }
}
