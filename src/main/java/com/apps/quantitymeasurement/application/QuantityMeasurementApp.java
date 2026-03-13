package com.apps.quantitymeasurement.application;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

	private static QuantityMeasurementApp instance;
	private final QuantityMeasurementController controller;

	// Singleton
	private QuantityMeasurementApp() {
		// Factory pattern — create layers
		IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
		this.controller = new QuantityMeasurementController(service);
	}

	public static QuantityMeasurementApp getInstance() {
		if (instance == null) {
			instance = new QuantityMeasurementApp();
		}
		return instance;
	}

	public QuantityMeasurementController getController() {
		return controller;
	}

	public static void main(String[] args) {
		QuantityMeasurementController controller = QuantityMeasurementApp.getInstance().getController();

		System.out.println("Length Operations");
		controller.performComparison(new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
				new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES));
		controller.performConversion(new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET), "INCHES");
		controller.performAddition(new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
				new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES), "FEET");
		controller.performSubtraction(new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET),
				new QuantityDTO(6.0, QuantityDTO.LengthUnit.INCHES), "FEET");
		controller.performDivision(new QuantityDTO(10.0, QuantityDTO.LengthUnit.FEET),
				new QuantityDTO(2.0, QuantityDTO.LengthUnit.FEET));

		System.out.println("\nWeight Operations");
		controller.performComparison(new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM),
				new QuantityDTO(1000.0, QuantityDTO.WeightUnit.GRAM));
		controller.performConversion(new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM), "GRAM");
		controller.performAddition(new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM),
				new QuantityDTO(1000.0, QuantityDTO.WeightUnit.GRAM), "KILOGRAM");

		System.out.println("\nVolume Operations");
		controller.performComparison(new QuantityDTO(1.0, QuantityDTO.VolumeUnit.LITRE),
				new QuantityDTO(1000.0, QuantityDTO.VolumeUnit.MILLILITRE));
		controller.performConversion(new QuantityDTO(1.0, QuantityDTO.VolumeUnit.LITRE), "MILLILITRE");

		System.out.println("\nTemperature Operations");
		controller.performComparison(new QuantityDTO(0.0, QuantityDTO.TemperatureUnit.CELSIUS),
				new QuantityDTO(32.0, QuantityDTO.TemperatureUnit.FAHRENHEIT));
		controller.performConversion(new QuantityDTO(100.0, QuantityDTO.TemperatureUnit.CELSIUS), "FAHRENHEIT");
		// Unsupported
		controller.performAddition(new QuantityDTO(100.0, QuantityDTO.TemperatureUnit.CELSIUS),
				new QuantityDTO(50.0, QuantityDTO.TemperatureUnit.CELSIUS), "CELSIUS");

		System.out.println("\nCross-Category Prevention");
		controller.performComparison(new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET),
				new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM));
	}
}