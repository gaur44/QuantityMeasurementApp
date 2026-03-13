package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

	private static final String FILE_PATH = "quantity_measurements.ser";
	private static QuantityMeasurementCacheRepository instance;
	private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

	private QuantityMeasurementCacheRepository() {
		loadFromDisk();
	}

	public static QuantityMeasurementCacheRepository getInstance() {
		if (instance == null) {
			instance = new QuantityMeasurementCacheRepository();
		}
		return instance;
	}

	@Override
	public void save(QuantityMeasurementEntity entity) {
		cache.add(entity);
		saveToDisk(entity);
	}

	@Override
	public List<QuantityMeasurementEntity> getAllMeasurements() {
		return new ArrayList<>(cache);
	}

	private void saveToDisk(QuantityMeasurementEntity entity) {
		File file = new File(FILE_PATH);
		try {
			if (file.exists()) {
				try (AppendableObjectOutputStream out = new AppendableObjectOutputStream(
						new FileOutputStream(file, true))) {
					out.writeObject(entity);
				}
			} else {
				try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
					out.writeObject(entity);
				}
			}
		} catch (IOException e) {
			System.err.println("Failed to save entity to disk: " + e.getMessage());
		}
	}

	private void loadFromDisk() {
		File file = new File(FILE_PATH);
		if (!file.exists())
			return;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			while (true) {
				try {
					QuantityMeasurementEntity entity = (QuantityMeasurementEntity) in.readObject();
					cache.add(entity);
				} catch (EOFException e) {
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Failed to load entities from disk: " + e.getMessage());
		}
	}

	private static class AppendableObjectOutputStream extends ObjectOutputStream {

		public AppendableObjectOutputStream(OutputStream out) throws IOException {
			super(out);
		}

		@Override
		protected void writeStreamHeader() throws IOException {
			reset(); // Skip header to allow appending
		}
	}
}