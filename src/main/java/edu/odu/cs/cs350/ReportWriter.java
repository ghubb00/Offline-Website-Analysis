package edu.odu.cs.cs350;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
//libraries for Excel
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.cliftonlabs.json_simple.JsonArray;
//libraries for JSON
import com.github.cliftonlabs.json_simple.JsonObject;

public class ReportWriter {
	Website website;
	String outPutFilePath;

	@SuppressWarnings("unused")
	private ReportWriter() {
	}

	public ReportWriter(Website website, String outPutFilePath) {
		this.website = website;
		this.outPutFilePath = outPutFilePath;
	}

	/**
	 * Create the standard file name without the extension
	 * 
	 * @return file name without the extension
	 */
	public String createFileName() {
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("YYYYMMDD-hhmmss-");
		LocalDateTime now = LocalDateTime.now();
		return formatDateTime.format(now) + "summary";

	}

	/**
	 * Create a text file that lists out all the pages sorted lexicographically by
	 * directory.
	 * 
	 * @throws IOException
	 */
	public String createTextFile() throws IOException {
		try {
			File myObj = new File(outPutFilePath + createFileName() + ".txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		String filePath = outPutFilePath + createFileName() + ".txt";

		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

		try {

			for (Iterator<HTMLDocument> iPage = website.getPageIterator(); iPage.hasNext();) {
				HTMLDocument page = iPage.next();

				if (page.getTotalImageSize() < 1000) {
					writer.write(page.getTotalImageSize() + "K");
				} else if (page.getTotalImageSize() > 1000) {
					writer.write((page.getTotalImageSize() / 1000) + "M");
				}
				writer.write("      ");
				writer.write(page.getPath().toString());
				writer.newLine();
			}
			writer.close();

			System.out.println("Successfully wrote to the file.");

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return "System created the following file: " + filePath + "\n";
	}

	public String createExcel() {
		// Create a workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("summary");

		// Create header row
		Row headerRow = sheet.createRow(0);

		// Create header row titles
		headerRow.createCell(0).setCellValue("Page");
		headerRow.createCell(1).setCellValue("# Images");
		headerRow.createCell(2).setCellValue("# CSS");
		headerRow.createCell(3).setCellValue("# Scripts");
		headerRow.createCell(4).setCellValue("# Links (Intra-Page)");
		headerRow.createCell(5).setCellValue("# Links (Internal)");
		headerRow.createCell(6).setCellValue("# Links (External)");

		// Create rows for the pages
		int rowNum = 1;
		for (Iterator<HTMLDocument> iPage = website.getPageIterator(); iPage.hasNext();) {
			Row row = sheet.createRow(rowNum++);
			HTMLDocument page = iPage.next();

			// Page value
			row.createCell(0).setCellValue(page.getPath().toString());

			// Images
			row.createCell(1).setCellValue(page.getTotalLocalImages() + page.getTotalExternalImages());

			// CSS
			row.createCell(2).setCellValue(page.getTotalStylesheets());

			// Scripts
			row.createCell(3).setCellValue(page.getTotalScripts());

			// Links (Intra-Page)
			row.createCell(4).setCellValue(page.getTotalIntraPageLinks());

			// Links (Internal)
			row.createCell(5).setCellValue(page.getTotalInternalLinks());

			// Links (External)
			row.createCell(6).setCellValue(page.getTotalExternalLinks());
		}

		// Write the output to a file
		String filePath = outPutFilePath + createFileName() + ".xlsx";
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
			// Closing the workbook
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "System created the following file: " + filePath + "\n";
	}

	public String createJSON() {
		// File Path for JSON file
		String jsonFilePath = outPutFilePath + createFileName() + ".json";

		// Creating new JSON object
		JsonObject json = new JsonObject();
		// Adding the basePath to json
		json.put("basePath", website.getPath().toString());

		/**
		 * Adding the URLs to JSON
		 */
		// creating JSON Arrary
		JsonArray urlArray = new JsonArray();
		// adding URLs (site roots) to array
		for (Iterator<URL> iURL = website.getSiteRootIterator(); iURL.hasNext();) {
			URL siteRoot = iURL.next();
			urlArray.add(siteRoot.toString());
		}
		// adding urls to JSON file
		json.put("urls", urlArray);

		/**
		 * Adding the pages to JSON
		 */
		// creating JSON Array
		JsonArray pagesArray = new JsonArray();

		for (Iterator<HTMLDocument> iPage = website.getPageIterator(); iPage.hasNext();) {
			HTMLDocument page = iPage.next();
			JsonObject pageJSON = new JsonObject();

			pageJSON.put("path", page.getPath().toString());
			
			//Images
			JsonObject imageLocal = new JsonObject();
			imageLocal.put("local", page.getTotalLocalImages());
			JsonObject imageExternal = new JsonObject();
			imageExternal.put("local", page.getTotalExternalImages());
			JsonArray imagesArray = new JsonArray();
			imagesArray.add(imageLocal);
			imagesArray.add(imageExternal);
			
			pageJSON.put("imageCount", imagesArray);
			
			//jsCount
			/**WORK In Progress */
			
			pagesArray.add(pageJSON);
		}
		//adding pages to JSON file
		json.put("pages", pagesArray);
		
		try (FileWriter file = new FileWriter(jsonFilePath)) {
			file.write(json.toJson());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "System created the following file: " + jsonFilePath +  "\n";

	}

}
