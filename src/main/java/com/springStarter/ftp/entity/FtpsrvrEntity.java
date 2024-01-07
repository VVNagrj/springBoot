package com.springStarter.ftp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Ftp")
public class FtpsrvrEntity {
	
	@JsonProperty("Host")
	private String host;
	@JsonProperty("Port")
	private int port;
	@JsonProperty("UserName")
	private String username;
	@JsonProperty("Password")
	private String password;
	@JsonProperty("FolderPath")
	private String folderpath;
	@JsonProperty("FileName")
	private String filename;
}
