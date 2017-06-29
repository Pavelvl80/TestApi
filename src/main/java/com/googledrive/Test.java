package com.googledrive;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.File;

/**
 * Created by Edvard Piri on 25.06.2017.
 */
public class Test {
    public void test() {
        File fileMetadata = new File();
        fileMetadata.setName("photo.jpg");
        java.io.File filePath = new java.io.File("files/photo.jpg");
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        File file = new File();

        System.out.println("File ID: " + file.getId());
    }
}
