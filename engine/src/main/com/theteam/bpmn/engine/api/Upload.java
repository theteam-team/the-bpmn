package com.theteam.bpmn.engine.api;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


 
@Path("/files")
public class Upload {


    //private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://work/server_upload/";
    private final String SERVER_UPLOAD_LOCATION_FOLDER = getClass().getResource("/files").getPath().substring(1);
    private final String SERVER_UPLOAD_LOCATION_FOLDER_DESIGN = getClass().getResource("/xml").getPath().substring(1);
 
    /**
     * Upload a File
     */
 
    @POST
    @Path("/designupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadDeisgnFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
 
        String filePath = SERVER_UPLOAD_LOCATION_FOLDER_DESIGN + contentDispositionHeader.getFileName();
 
        // save the file to the server
        saveFile(fileInputStream, filePath);
 
        String output = "File saved to server location : " + filePath;
 
        return Response.status(200).entity(output).build();
 
    }

    @POST
    @Path("/fileupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        String filePath = SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
 
        // save the file to the server
        saveFile(fileInputStream, filePath);
 
        String output = "File saved to server location : " + filePath;
 
        return Response.status(200).entity(output).build();
 
    }
 
    // save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream,
            String serverLocation) {

                /*
                Thread th = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        
                    }
                });
        
                th.start();
                */
 
        try {
            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
 
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
 
            e.printStackTrace();
        }
 
    }
 
}