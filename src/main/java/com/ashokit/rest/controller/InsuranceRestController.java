package com.ashokit.rest.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.reports.ExcelReportGenerator;
import com.ashokit.reports.PdfReportGenerator;
import com.ashokit.request.SearchRequest;
import com.ashokit.response.PlanResponse;
import com.ashokit.service.InsurancePlanService;

@RestController
public class InsuranceRestController {
	
	@Autowired
	private InsurancePlanService service;
	
	@GetMapping("plannames")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> planNames=service.getUniquePlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}
	
	@GetMapping("planstatus")
	public ResponseEntity<List<String>> getPlanStatuses(){
		List<String> statusList=service.getUniuePlanStatus();
		return new ResponseEntity<>(statusList, HttpStatus.OK);
	}
	
	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> searchPlans(@RequestBody SearchRequest req){
		List<PlanResponse> plans=service.searchPlans(req);
		return new ResponseEntity<List<PlanResponse>>(plans,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment; filename=Plans.xlsx";
		
		response.setHeader(headerKey, headerValue);
		
		List<PlanResponse> plans=service.searchPlans(null);
		
		ExcelReportGenerator generator = new ExcelReportGenerator();
		generator.export(plans, response);
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		
		String headerKey= "Content-Disposition";
		String headerValue= "attachment; filename=Plans.pdf";
		
		response.setHeader(headerKey, headerValue);
		
		List<PlanResponse> plans=service.searchPlans(null);
		
		PdfReportGenerator generator = new PdfReportGenerator();
		generator.export(plans, response);
	}
}
