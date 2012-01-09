/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Bath;
import Entity.Classincourse;
import Entity.Course;
import Entity.Sem;
import Session.BathFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author SVN - Team
 */
@ManagedBean(name = "BathController")
@RequestScoped
public class BathController {
    @EJB
    private BathFacade bathFacade;
    private TreeNode selectedNode;
    /** Creates a new instance of BathController */
    public BathController() {
    }
    public List<Bath> SHOWALL(){
        return bathFacade.findAll();
    }
    private TreeNode root;  
  
    public void TreeBean() {
    }  
  
    public TreeNode getRoot() {
        
        List<Bath> rs = bathFacade.findAll();
        root = new DefaultTreeNode("Root", null);
        TreeNode[] BathNode = new TreeNode[rs.size()];
        for(int i = 0; i < rs.size() ; i++){
            //Add to root node
            BathNode[i] = new DefaultTreeNode(rs.get(i).getBname(), root);
            //A lot of loop for display all bath, sem, course
            List<Sem> rs0 = rs.get(i).getSemList();
            if(rs0!=null){
                TreeNode[] BathNode0 = new TreeNode[rs.size()];
                for(int y = 0; y < rs0.size() ; y++){
                    BathNode0[y] = new DefaultTreeNode(rs0.get(y).getSname(), BathNode[i]);
                    List<Course> rs00 = rs0.get(y).getCourseList();
                    if(rs00!=null){
                        TreeNode[] BathNode00 = new TreeNode[rs00.size()];
                        for(int z = 0; z < rs00.size() ; z++){
                            BathNode00[z] = new DefaultTreeNode(rs00.get(z).getCname(), BathNode0[z]);
                            List<Classincourse> rs000 = rs00.get(z).getClassincourseList();
                            if(rs000!=null){
                                TreeNode[] BathNode000 = new TreeNode[rs000.size()];
                                for(int f = 0; f < rs000.size() ; f++){
                                    //Add link to assiment view page.
                                   BathNode000[f] = new DefaultTreeNode("<a href=http://localhost:8080/RoyalEducationalAcademyPortalSite-war/faces/ViewAssiment.xhtml?class="+rs000.get(f).getClass1().getCid()+"&course="+rs000.get(f).getCourse().getCid()+">"+rs000.get(f).getClass1().getCname()+"</a>", BathNode00[f]);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return root;  
    }

    /**
     * @return the selectedNode
     */
    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    /**
     * @param selectedNode the selectedNode to set
     */
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
