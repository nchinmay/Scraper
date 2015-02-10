package datautil;

import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;

public class CsvFileHelper {
	public void writeAsCsvFile(String runDir, String filename, char delim) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(runDir + filename),
					delim);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
