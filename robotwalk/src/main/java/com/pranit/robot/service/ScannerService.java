package com.pranit.robot.service;

import com.pranit.robot.exception.ScanFailureException;

public interface ScannerService {

	/**
	 * Calls third party scanner implementation an provides the result
	 * @param barcode
	 * @throws ScanFailureException
	 */
	public Object scan(String barcode) throws ScanFailureException;
}
