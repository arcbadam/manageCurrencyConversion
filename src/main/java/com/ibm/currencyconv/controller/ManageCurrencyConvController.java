package com.ibm.currencyconv.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import com.ibm.currencyconv.dto.ManageCurrencyConvDTO;
import com.ibm.currencyconv.entity.*;
import com.ibm.currencyconv.service.ManageCurrencyService;

//@RequestMapping("manageCurr")
@RestController
public class ManageCurrencyConvController {


	@Autowired
	ManageCurrencyService manageCurrencyService;

	@ApiOperation("This will return the currency conversion factor for a given country code")
	@GetMapping("/") //localhost:8080/product?id=1
	public ResponseEntity<ManageCurrencyConversion> getCurrencyConversion(@RequestParam(value="countryCode")String countryCode) {

		Optional<ManageCurrencyConversion> mcc= manageCurrencyService.getConversionFactor(countryCode);

		if(mcc.isPresent()) {

			return ResponseEntity.status(HttpStatus.OK).body(mcc.get());
		}else {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}
	
	@ApiOperation("This will return the currency conversion factor for a given country code")
	@GetMapping("/manageCurr/{countryCode}") //localhost:8080/product?id=1
	public Double getCurrencyConversion1(@RequestParam(value="countryCode")String countryCode) {

		Optional<ManageCurrencyConversion> mcc= manageCurrencyService.getConversionFactor(countryCode);

		if(mcc.isPresent()) {

			return mcc.get().getConversionFactor();
		}else {

			return null;
		}

	}
	
	@PostMapping("/add")
	@ApiOperation("This will add conversion factor in  manage_currency entity")
	public ResponseEntity<ManageCurrencyConversion> addConversionFactor(@RequestBody ManageCurrencyConvDTO dto) {
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(manageCurrencyService.addConversionFactor(dto));
	}
	
	@PostMapping("/update")
	@ApiOperation("This will update conversion factor in manage_currency entity")
	public ResponseEntity<ManageCurrencyConversion> updateConversionFactor(@RequestBody ManageCurrencyConvDTO dto) {
		
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(manageCurrencyService.addConversionFactor(dto));
	}
}
