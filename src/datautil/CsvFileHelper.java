package datautil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
import datafetching.ICSVAble;

public class CsvFileHelper {
	public static void writeAsCsvFile(String runDir, String filename,
			char delim, ICSVAble csvAble) {
		try {
			File csvFile = new File(runDir);
			if (!csvFile.exists()) {
				csvFile.mkdir();
			}
			CSVWriter writer = new CSVWriter(new FileWriter(filename), delim);
			List<String[]> csv = new LinkedList<String[]>();
			csv.add(csvAble.getCSVHeader().split(","));
			csv.add(csvAble.getRow().split(","));
			writer.writeAll(csv);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
