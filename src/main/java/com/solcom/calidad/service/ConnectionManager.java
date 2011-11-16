package com.solcom.calidad.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class ConnectionManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	
	private static ConnectionManager ref;
	private static PartnerConnection connection;
	private static ConnectorConfig config;
	
	
	//TODO - Sacar estas credenciales a un archivo de Configuracion
	private static final String username = "admin@calidad.com";
	private static final String password = "calidad15TTdMBz5JKppMCaImFyFETlo";
	
	//TODO - invertigar como implementar Oauth en lugar del login normal. 
	
	private ConnectionManager(){ }
	
	
	public static ConnectionManager getConnectionManager(){
		
		if(ref == null){
			ref = new ConnectionManager();		
		}
		
		return ref;
	}
	
	
	public PartnerConnection getConnection(){
		
		try{
			
			config = new ConnectorConfig();
			config.setUsername(username);
			config.setPassword(password);
			config.setAuthEndpoint("https://login.salesforce.com/services/Soap/u/22.0/");
			connection = Connector.newConnection(config);
			
		}catch ( ConnectionException ce){
			logger.error(" Connection Exception "+ ce);
		}
		
		logger.info("::: Session Id : "+ config.getSessionId());
		
		return connection;
	}
		
	
	

}
