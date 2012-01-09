/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Kai
 */
@ManagedBean
@RequestScoped
public class Sems implements Serializable{
    private int sid;
    private String sname;
    private Baths bid;

    public Sems(int sid, String sname, Baths bid) {
        this.sid = sid;
        this.sname = sname;
        this.bid = bid;
    }

    public Baths getBid() {
        return bid;
    }

    public void setBid(Baths bid) {
        this.bid = bid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    
    /** Creates a new instance of Sems */
    public Sems() {
    }
}
