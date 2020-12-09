package com.ibm.currencyconv.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.currencyconv.converter.CurrencyConverter;
import com.ibm.currencyconv.dto.ManageCurrencyConvDTO;
import com.ibm.currencyconv.entity.ManageCurrencyConversion;
import com.ibm.currencyconv.repository.CurrConvRepository;

@Service
public class ManageCurrencyService {
	
	@Autowired
	CurrConvRepository currConvRepo;
	
	public ManageCurrencyConversion addConversionFactor(ManageCurrencyConvDTO dto) {
		
		CurrencyConverter converter=new CurrencyConverter();
		
		
		return currConvRepo.save(converter.convertToEntity(dto));
		
	}
	
	public Optional<ManageCurrencyConversion> getConversionFactor(String countryCode) {
		
		return currConvRepo.findById(countryCode);
	}

	public Object updateConversionFactor(ManageCurrencyConvDTO dto) {
		CurrencyConverter converter=new CurrencyConverter();
		ManageCurrencyConversion mccUpdate = converter.convertToEntity(dto);
		if(mccUpdate!=null && mccUpdate.getCountryCode()!=null) {
			ManageCurrencyConversion mccExisting = currConvRepo.findById(mccUpdate.getCountryCode()).get();
			if(mccExisting!=null) {
				mccExisting.setConversionFactor(mccUpdate.getConversionFactor());
				return currConvRepo.save(mccExisting);
			}	
		}
		return null;
	}

}
