package com.impactanalysis.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impactanalysis.entities.AuditEntity;
import com.impactanalysis.entities.DeploymentEntity;
import com.impactanalysis.pojo.Requestor;
import com.impactanalysis.repositories.AuditRepository;
import com.impactanalysis.services.DeploymentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value="/Deployment")
@Api(value="Deployment", description="API for Deployment related activities")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "You are not authorized to view the resource"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
public class DeploymentController {
	
	@Autowired
	private DeploymentService deploymentService;
	
	@Autowired
	private AuditRepository auditRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ApiOperation(value = "Deployment APIs HealthCheck", response = String.class)
	@GetMapping("/healthCheck")
	public String testApp() {
		return "Deployment APIs are fine";
	}
	
	@ApiOperation(value = "Save Deployment Info", response = DeploymentEntity.class)
	@PostMapping(value = "/createDeploymentInfo", consumes = "application/json")
	public DeploymentEntity createDeploymentInfo(@RequestBody DeploymentEntity deploymentRequest) {
		long startTime = System.currentTimeMillis();
		DeploymentEntity deploymentEntity = deploymentService.createDeploymentInfo(deploymentRequest);
		logger.info(String.format("API::fetchImpactedTestSuites Request=%s, Response=%s, TimeTaken=%s Milliseconds", deploymentRequest, deploymentEntity, System.currentTimeMillis()-startTime));
		auditRepository.save(new AuditEntity("createDeploymentInfo", deploymentRequest.toString(), deploymentEntity.toString()));
		return deploymentEntity;
	}
	
	@ApiOperation(value = "Get all Deployments", response = List.class)
	@GetMapping(value = "/getAllDeploymentsInfo", consumes = "application/json")
	public List<DeploymentEntity> getAllDeploymentsInfo() {
		long startTime = System.currentTimeMillis();
		List<DeploymentEntity> deploymentEntities = deploymentService.getAllDeploymentsInfo();
		logger.info(String.format("API::fetchImpactedTestSuites Response=%s, TimeTaken=%s Milliseconds", deploymentEntities, System.currentTimeMillis()-startTime));
		return deploymentEntities;
	}
	
	@ApiOperation(value = "Get all Deployments", response = DeploymentEntity.class)
	@PostMapping(value = "/getLatestDeploymentInfo", consumes = "application/json", produces= "application/json")
	public DeploymentEntity getLatestDeploymentInfo(@RequestBody DeploymentEntity deploymentDetails) {
		long startTime = System.currentTimeMillis();
		DeploymentEntity deploymentEntity = deploymentService.getLatestDeploymentInfo(deploymentDetails);
		logger.info(String.format("API::fetchImpactedTestSuites Response=%s, TimeTaken=%s Milliseconds", deploymentEntity, System.currentTimeMillis()-startTime));
		return deploymentEntity;
	}
	
	@ApiOperation(value = "Get all Deployments", response = DeploymentEntity.class)
	@DeleteMapping(value = "/deleteDeploymentInfoById/{deploymentId}", consumes = "application/json")
	public void deleteDeploymentInfoById(@RequestBody Requestor requestor, @PathVariable("deploymentId") Long deploymentId) {
		long startTime = System.currentTimeMillis();
		deploymentService.deleteDeploymentInfoById(requestor, deploymentId);
		logger.info(String.format("API::fetchImpactedTestSuites deploymentId=%s, TimeTaken=%s Milliseconds", deploymentId, System.currentTimeMillis()-startTime));
		auditRepository.save(new AuditEntity("deleteDeploymentInfoById", "DeploymentId=" + deploymentId + "::Requestor=" + requestor, null));
	}
}