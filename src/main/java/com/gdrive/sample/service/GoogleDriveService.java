package com.gdrive.sample.service;

import com.gdrive.sample.util.ApplicationConfig;
import com.gdrive.sample.util.Constants;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@Service
public class GoogleDriveService {

	private Logger logger = LoggerFactory.getLogger(GoogleDriveService.class);

	private Drive driveService;

	@Autowired
	OAuthService OAuthService;

	@Autowired
	ApplicationConfig applicationConfig;

	@PostConstruct
	public void init() throws Exception {
		Credential credential = OAuthService.getCredentials();
		driveService = new Drive.Builder(Constants.HTTP_TRANSPORT, Constants.JSON_FACTORY, credential)
				.setApplicationName(Constants.APPLICATION_NAME).build();
	}

	public void uploadFile(MultipartFile multipartFile) throws Exception {
		logger.debug("Inside Upload Service...");

		String path = applicationConfig.getImageFolderPath();
		String fileName = multipartFile.getOriginalFilename();
		String contentType = multipartFile.getContentType();

		java.io.File transferedFile = new java.io.File(path, fileName);
		multipartFile.transferTo(transferedFile);

		File fileMetadata = new File();
		fileMetadata.setName(fileName);

		FileContent mediaContent = new FileContent(contentType, transferedFile);
		File file = driveService.files().create(fileMetadata, mediaContent).setFields("id").execute();

		logger.debug("File ID: " + file.getName() + ", " + file.getId());
	}
}
