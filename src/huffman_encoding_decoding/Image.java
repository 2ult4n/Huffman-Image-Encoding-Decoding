package huffman_encoding_decoding;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image {
	
	 BufferedImage buffImage ;
	 
	 /* this class provide easier utilities dealing with reading and writing an image */
	 
	 /* reading from original user directory */
	 public void readImage(String fileName) {
		 File file = new File(System.getProperty("user.dir")+"\\pics\\"+fileName);
	     try{
	         buffImage = ImageIO.read(file);
	     } 
	     catch (IOException e){
	         e.printStackTrace();
	     }
	 }
	 /* reading from user given directory */
	 public void readImage(String fileName,boolean noUse) {
		 File file = new File(fileName);
	     try{
	         buffImage = ImageIO.read(file);
	     } 
	     catch (IOException e){
	         e.printStackTrace();
	     }
	 }
	 /* writing to original user directory */
	 public void writeImage(String fileName) {
		 File file = new File(System.getProperty("user.dir")+"\\pics\\"+fileName);
	     try{
	         ImageIO.write(buffImage, "bmp", file);
	     } 
	     catch (IOException e){
	         e.printStackTrace();
	     }
	 }
	 /*  writing to user given directory */
	 public void writeImage(String fileName,boolean noUse) {
		 File file = new File(fileName);
	     try{
	         ImageIO.write(buffImage, "bmp", file);
	     } 
	     catch (IOException e){
	         e.printStackTrace();
	     }
	 }

}
