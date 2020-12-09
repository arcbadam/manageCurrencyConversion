package com.ibm.currencyconv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.currencyconv.entity.ManageCurrencyConversion;

@Repository
public interface CurrConvRepository extends JpaRepository<ManageCurrencyConversion, String>{

}
