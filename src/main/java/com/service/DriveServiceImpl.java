package com.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.googledrive.Quickstart;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Edvard Piri on 25.06.2017.
 */
public class DriveServiceImpl implements DriveService {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME =
            "Other client 2";

    /**
     * Directory to store user credentials for this application.
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.dir"), "main/resources");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(DriveScopes.DRIVE_FILE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    private static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                Quickstart.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

//         Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    private static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public File copy(String id /*or File java.io.File file (more operations)*/) throws IOException {
        return getDriveService().files().copy(id, null)
                .setFields("id, name") //Response fields
                .execute();
    }

    //Create = insert = upload
    public File create(/*String fileName, java.io.File upFile or String path*/) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName("fileName");
        FileContent mediaContent = new FileContent("text/csv", new java.io.File("path"));
        File file = getDriveService().files().create(fileMetadata, mediaContent)
                .setFields("id, name") //Response fields
                .execute();
        return file;
    }

    public int delete(String id) throws IOException{
        getDriveService().files().delete(id);
        return 0;
    }

    public File get(String id /*or File java.io.File file (more operations)*/) throws IOException {
        return getDriveService().files().get(id)
                .setFields("id, name") //Response fields
                .execute();
    }

    public FileList list() throws IOException { //get all files
        return getDriveService().files().list()
                .execute();
    }

    public static void main(String[] args) throws IOException {
        DriveService driveService = new DriveServiceImpl();
        FileList list = driveService.list();
        String id = list.getFiles().get(0).getId();
//        driveService.create();
        File fileMetadata = driveService.get(id);
        driveService.copy(id);
    }
}
