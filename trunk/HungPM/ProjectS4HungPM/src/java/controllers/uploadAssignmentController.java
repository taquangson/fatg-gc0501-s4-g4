/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Kai
 */
@ManagedBean
@RequestScoped
public class uploadAssignmentController {    
    String filePath;

    public void handleFileUpload(FileUploadEvent event) {
        
        String uploadedFolder = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadFolder");

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File(extContext.getRealPath("//" + uploadedFolder) + "//" + event.getFile().getFileName());
        System.out.println(result.getAbsolutePath());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[(int)event.getFile().getSize()];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            filePath = result + "/" + event.getFile().getFileName();
        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    /** Creates a new instance of uploadAssignmentController */
    public uploadAssignmentController() {
    }
}
