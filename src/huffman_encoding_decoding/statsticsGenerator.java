package huffman_encoding_decoding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class statsticsGenerator {
	
	/*this class have been written to provide statistics for the experimentation
	 * it will parse the information in comma separated file csv in this form -- > Input Size,Time 
	 * and it will be later used from python script to plot its information 
	 * using matplotlib library  */

	public static void main(String[] args) {
		try {
		Scanner input = new Scanner(System.in);
		File file1 = new File(System.getProperty("user.dir")+"\\pics\\encodingStat.csv");
		File file2 = new File(System.getProperty("user.dir")+"\\pics\\decodingStat.csv");
		FileWriter statFile1;
		FileWriter statFile2;
		statFile1 = new FileWriter(file1);
		statFile2 = new FileWriter(file2);
		CSVWriter writer1 = new CSVWriter(statFile1); 
		CSVWriter writer2 = new CSVWriter(statFile2); 
		String[] header = { "Input Size", "Encoding Time"};
		writer1.writeNext(header);
		String[] header2 = { "Input Size", "Decoding Time"};
		writer2.writeNext(header2);
		System.out.print("Enter the size of dataset : ");
		int size = input.nextInt();
		
		LinkedList<CSVParser> encodingStat = new LinkedList<CSVParser>();
		LinkedList<CSVParser> decodingStat = new LinkedList<CSVParser>();
		for(int i = 1 ; i <= size ; i++) {
			
			Image image = new Image();
			System.out.println(String.valueOf(i));
			image.readImage(String.valueOf(i)+".bmp");
			HuffmanUtils h = new HuffmanUtils();
			encodingStat.add(new CSVParser(image.buffImage.getHeight()*image.buffImage.getWidth() , h.huffmanEncode(image) ) );
			compressedForm compressedImage = new compressedForm();
			compressedImage = compressedImage.read("compressed");
			decodingStat.add(new CSVParser(image.buffImage.getHeight()*image.buffImage.getWidth() , h.huffmanDecode(compressedImage) ) );

		}
		Collections.sort(encodingStat);
		Collections.sort(decodingStat);
		
		for(int i = 0 ; i < size ; i++) {
			
			String[] data1 = { String.valueOf(encodingStat.get(i).size), String.valueOf(encodingStat.get(i).time)};
			String[] data2 = {String.valueOf(decodingStat.get(i).size), String.valueOf(decodingStat.get(i).time)};
			writer1.writeNext(data1);
			writer2.writeNext(data2);
		}
		writer1.close();
		writer2.close();
		System.out.println("Generating Statsitcs Finished > > >");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
