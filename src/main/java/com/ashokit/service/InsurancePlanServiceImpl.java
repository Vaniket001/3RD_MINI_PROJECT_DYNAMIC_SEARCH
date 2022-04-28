package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.entity.InsurancePlanEntity;
import com.ashokit.repository.InsurancePlanRepository;
import com.ashokit.request.SearchRequest;
import com.ashokit.response.PlanResponse;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {
	
	@Autowired
	private InsurancePlanRepository repo;

	@Override
	public List<PlanResponse> searchPlans(SearchRequest req) {
		
		InsurancePlanEntity entity=new InsurancePlanEntity();
		
		if(null!=req && req.getPlanName()!=null && !req.getPlanName().equals("")) {
			entity.setPlanName(req.getPlanName());
		}
		if(null!=req && req.getPlanStatus()!=null && !req.getPlanStatus().equals("")) {
			entity.setPlanStatus(req.getPlanStatus());
		}
		
		Example<InsurancePlanEntity> example=Example.of(entity);
		List<InsurancePlanEntity> findAll= repo.findAll(example);
		
		List<PlanResponse> planData =new ArrayList<>();
		for(InsurancePlanEntity ip: findAll) {
			PlanResponse plan = new PlanResponse();
			BeanUtils.copyProperties(ip, plan);
			planData.add(plan);
		}
		return planData;
	}

	@Override
	public List<String> getUniquePlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getUniuePlanStatus() {
		return repo.getPlanStatuses();
	}

}
