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
public class Baths implements Serializable{
    private int bid;
    private String bname;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
    /** Creates a new instance of Baths */
    public Baths() {
    }

    public Baths(int bid, String bname) {
        this.bid = bid;
        this.bname = bname;
    }   
}
