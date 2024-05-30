package com.igordalpicolo.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igordalpicolo.hrpayroll.entities.Payment;
import com.igordalpicolo.hrpayroll.entities.Worker;
import com.igordalpicolo.hrpayroll.feingclients.WorkerFeingClient;

@Service
public class PaymentService {
	
	@Autowired
	private WorkerFeingClient workerFeingClient;

	public Payment getPayment(Long workerId, int days) {
		
		
		Worker worker = workerFeingClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
	
}
