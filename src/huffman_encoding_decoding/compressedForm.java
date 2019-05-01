package huffman_encoding_decoding;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;


public class compressedForm implements Serializable {
	public HashMap<Integer,String> binaryMap;
	HashMap<String, BTNode> huffmanLetterCodes ;
	HashMap<String, Integer> decodingMap ; 
	public int height;
	public int width;
	public String [][] imageCompressed;
	
	
	public void set() {
		imageCompressed = new String[height][width];
	}
	
	
	public compressedForm() {
	//Default Constructor :/ 
		
	}
	

	public void write(compressedForm a , String fileName) throws Exception{

        File f = new File(System.getProperty("user.dir")+"\\pics\\"+fileName);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream o = new ObjectOutputStream(fos);
        o.writeObject(a);

        o.close();
        fos.close();

	}
	public compressedForm read(String fileName) throws Exception {
    compressedForm a = new compressedForm();

        File f = new File(System.getProperty("user.dir")+"\\pics\\"+fileName);
        FileInputStream file = new FileInputStream(f);
        ObjectInputStream oi = new ObjectInputStream(file);


     a = (compressedForm) oi.readObject();
     oi.close();

    return a;
	}

}


