package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

	private final IQuantityMeasurementService service;

	public QuantityMeasurementController(IQuantityMeasurementService service) {
		this.service = service;
	}

	public boolean performComparison(QuantityDTO dto1, QuantityDTO dto2) {
		try {
			boolean result = service.compare(dto1, dto2);
			System.out.println("compare(" + dto1 + ", " + dto2 + ") = " + result);
			return result;
		} catch (QuantityMeasurementException e) {
			System.out.println("[ERROR] compare: " + e.getMessage());
			return false;
		}
	}

	public QuantityDTO performConversion(QuantityDTO dto, String targetUnit) {
		try {
			QuantityDTO result = service.convert(dto, targetUnit);
			System.out.println("convert(" + dto + ", " + targetUnit + ") = " + result);
			return result;
		} catch (QuantityMeasurementException e) {
			System.out.println("[ERROR] convert: " + e.getMessage());
			return null;
		}
	}

	public QuantityDTO performAddition(QuantityDTO dto1, QuantityDTO dto2, String targetUnit) {
		try {
			QuantityDTO result = service.add(dto1, dto2, targetUnit);
			System.out.println("add(" + dto1 + ", " + dto2 + ", " + targetUnit + ") = " + result);
			return result;
		} catch (QuantityMeasurementException e) {
			System.out.println("[ERROR] add: " + e.getMessage());
			return null;
		}
	}

	public QuantityDTO performSubtraction(QuantityDTO dto1, QuantityDTO dto2, String targetUnit) {
		try {
			QuantityDTO result = service.subtract(dto1, dto2, targetUnit);
			System.out.println("subtract(" + dto1 + ", " + dto2 + ", " + targetUnit + ") = " + result);
			return result;
		} catch (QuantityMeasurementException e) {
			System.out.println("[ERROR] subtract: " + e.getMessage());
			return null;
		}
	}

	public double performDivision(QuantityDTO dto1, QuantityDTO dto2) {
		try {
			double result = service.divide(dto1, dto2);
			System.out.println("divide(" + dto1 + ", " + dto2 + ") = " + result);
			return result;
		} catch (QuantityMeasurementException e) {
			System.out.println("[ERROR] divide: " + e.getMessage());
			return Double.NaN;
		}
	}
}