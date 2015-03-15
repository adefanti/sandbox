package org.sandbox.upload.service;

import org.sandbox.upload.bean.FileContainer;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "uploadService")
public interface UploadWebService {

    void uploadFile(@WebParam(name = "fileContainer") FileContainer file);
}
