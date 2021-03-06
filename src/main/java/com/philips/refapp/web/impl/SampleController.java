/*
 * (C) Koninklijke Philips Electronics N.V. 2014
 * 
 * All rights are reserved. Reproduction or transmission in whole or in part, in any form or by any
 * means, electronic, mechanical or otherwise, is prohibited without the prior written consent of
 * the copyright owner.
 * 
 * File name: SampleController.java
 */

package com.philips.refapp.web.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.philips.refapp.domain.Exception_Messages;
import com.philips.refapp.domain.SampleEntity;
import com.philips.refapp.exception.GlobalException;
import com.philips.refapp.service.BaseService;
import com.philips.refapp.web.AbstractController;

/**
 * The Class SampleController.
 *
 * @author Sushanta Dutta
 */

@RestController
public class SampleController extends AbstractController {

	/** The sample service. */
	@Autowired
	@Qualifier(value = "sampleService")
	private BaseService<SampleEntity> sampleService;

	@Autowired
	@Qualifier(value = "exceptionMessagesServiceImpl")
	private BaseService<Exception_Messages> exceptionService;

	/**
	 * View json.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/viewjson", produces = { "application/json",
			"application/xml" }, consumes = { "application/json",
			"application/xml" })
	public ResponseEntity<?> viewJson() {
		throw new GlobalException("Unprocessable Entity", null,
				HttpStatus.UNPROCESSABLE_ENTITY);
		// return new
		// ResponseEntity<Exception_Messages>(exceptionService.doSomething(exception_Messages),
		// HttpStatus.OK);
	}

}
