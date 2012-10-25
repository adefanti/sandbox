package org.sandbox.upload.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.sandbox.upload.bean.FileContainer;

@WebService(name = "uploadService")
public interface UploadWebService {

	void uploadFile(@WebParam(name = "fileContainer") FileContainer file);
}
