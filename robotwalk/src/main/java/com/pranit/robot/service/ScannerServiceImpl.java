package com.pranit.robot.service;

import com.pranit.robot.exception.ScanFailureException;

public class ScannerServiceImpl implements ScannerService {

	private static final ScannerServiceImpl instance = new ScannerServiceImpl();
	
	private ScannerServiceImpl(){}
	
	public static ScannerServiceImpl getInstance() {
		return instance;
	}
	
	@Override
	public Object scan(String barcode) throws ScanFailureException {
		
		/*
		 * This service internally calls the third party scanner service.
		 * For simplicity of understanding the scanning function following dummy code is written 
		 */
		
		if(Math.random()%2 != 0){
			throw new ScanFailureException("Scan Failed due to system error...");
		}
		System.out.println("Scanning Complete...");
		
		/*
		 * Returns scanner object. 
		 */
		return new Object();
	}

}
