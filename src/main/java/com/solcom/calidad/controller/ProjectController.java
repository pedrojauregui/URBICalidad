package com.solcom.calidad.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.solcom.calidad.service.ConnectionManager;

import com.solcom.calidad.model.Project;

@Controller
public class ProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@RequestMapping(value = "/project/", method = RequestMethod.GET)
	public String listProjects(Model model) {
		
		logger.info("Entering to listProjects");
		
		PartnerConnection connection =  ConnectionManager.getConnectionManager().getConnection();
		QueryResult result = null;
		
		
		try {
			logger.info("Getting the listProjects  : " + "Select id, Name from Desarrollo__c");
			result = connection.query("Select id, Name from Desarrollo__c");

		} catch (ConnectionException e) {
			logger.error("ConnectionException  : " + e);
			e.printStackTrace();
		}
		
		logger.info("Result Records  : " + result.getRecords());
		List<Project> myListOfProjects = new ArrayList<Project>();
		
		for(SObject o: result.getRecords()){
			Project p = new Project();
			
			p.setId(o.getField("Id").toString());
			p.setName(o.getField("Name").toString());
			
			myListOfProjects.add(p);
			logger.info("Result Record : " + p);
		}
		
		model.addAttribute("projects", myListOfProjects);
		
		return "project_list";
	}
	
	@RequestMapping(value="/project/", method=RequestMethod.POST)
	public String update(ModelAndView mv, @ModelAttribute("project") SObject project) {
	    	
		logger.info("updating /project");
		
		PartnerConnection connection =  ConnectionManager.getConnectionManager().getConnection();
				
		try {
			
			SObject projectToCreate = new SObject();
			projectToCreate.setType("Desarrollo__c");
			
			SObject[] projects = {projectToCreate};
			connection.create(projects);

		} catch (ConnectionException e) {
			logger.error("ConnectionException  : " + e);
			e.printStackTrace();
		}	
	    	
		return "redirect:/project/";
	}

}
