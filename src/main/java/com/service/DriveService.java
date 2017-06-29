package com.service;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Edvard Piri on 27.06.2017.
 */
public interface DriveService {
    /**
     * copy
     * Creates a copy of a file and applies any requested updates with patch semantics.
     * <p>
     * create
     * Creates a new file.
     * <p>
     * delete
     * Permanently deletes a file owned by the user without moving it to the trash.
     * If the file belongs to a Team Drive the user must be an organizer on the parent.
     * If the target is a folder, all descendants owned by the user are also deleted.
     * <p>
     * get
     * Gets a file's metadata or content by ID.
     * <p>
     * update
     * Updates a file's metadata and/or content with patch semantics.
     */

    File copy(String id) throws IOException;

    File create() throws IOException;

    int delete(String id) throws IOException;

    File get(String id) throws IOException;

    FileList list() throws IOException;
}
