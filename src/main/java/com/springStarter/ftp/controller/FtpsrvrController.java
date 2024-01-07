package com.springStarter.ftp.controller;

import com.springStarter.ftp.entity.FtpsrvrEntity;
import com.springStarter.ftp.res.FtpRes;
import com.springStarter.ftp.service.FtpsrvrService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/srv")
public class FtpsrvrController {
	
	private static final Logger LOG = Logger.getLogger(FtpsrvrController.class.getName());

	@Autowired
	private FtpsrvrService sftpsrvrService;
	
	
	public FtpsrvrEntity connector(String host, int port, String username, String password,String folderpath) {
		FtpsrvrEntity ftp = new FtpsrvrEntity();
		try {

			ftp.setHost(host);
			ftp.setPassword(password);
			ftp.setPort(port);
			ftp.setUsername(username);
			ftp.setFolderpath(folderpath);

		} catch (Exception ex) {
			LOG.info("Error: " + ex.getMessage());
			ex.printStackTrace();
			ftp = null;
		}
		return ftp;
	}

	// This method to upload the file given by the user
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	@ApiOperation(value = "Upload")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Upload") })
	public ResponseEntity<FtpRes> upload(@RequestPart("file") MultipartFile fileDetail,
										 @RequestParam  String host,
										 @RequestParam  int port,
										 @RequestParam  String username,
										 @RequestParam  String password,
										 @RequestParam  String folderpath) {
		
		FtpsrvrEntity ftp =connector(host, port, username, password,folderpath);
		FtpRes res = sftpsrvrService.uploadFile(fileDetail,ftp);
		
		if (res.getStatus().equals("Success")) {
			return new ResponseEntity<FtpRes>(res, HttpStatus.OK);
		} else {
			res.setStatus("Failed");
			return new ResponseEntity<FtpRes>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// This method used to upload the file given by the user
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		@ApiOperation(value = "delete")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 200, message = "delete") })
		public ResponseEntity<String> delete(@RequestBody FtpsrvrEntity dow) {

			String res = sftpsrvrService.deleteFile(dow);

			if (res.equals("Success")) {
				return ResponseEntity.status(HttpStatus.OK).body("File Deleted Sucessfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Internal Server Error");
			}
		}
		//Download Document
		@RequestMapping(value = "/download", method = RequestMethod.POST)
		@ApiOperation(value = "download")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 200, message = "download") })
		public ResponseEntity<Object> download(@RequestBody FtpsrvrEntity dow) {

			try {
				FTPClient ftpClient = new FTPClient();
				HttpHeaders headers = new HttpHeaders();
				
				ftpClient.connect(dow.getHost(), dow.getPort());
				ftpClient.login(dow.getUsername(), dow.getPassword());
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

				String remoteFile2 = dow.getFolderpath() + "/" + dow.getFilename();
				InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
	            
	            InputStreamResource resource = new InputStreamResource(inputStream);
	            headers.add("Content-Disposition", "attachment;filename=" + FilenameUtils.getBaseName(dow.getFilename()) + '.' + FilenameUtils.getExtension(dow.getFilename()));
	             
	            return ResponseEntity.ok()
	                    .headers(headers)
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .body(resource);
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
			}
		}
	
}
