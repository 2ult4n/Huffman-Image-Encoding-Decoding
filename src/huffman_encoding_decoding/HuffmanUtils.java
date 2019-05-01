package huffman_encoding_decoding;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Compression;

public class HuffmanUtils {
	
	LinkedBT huffmanTree = new LinkedBT();
	HashMap<Integer,Integer> frequencyColorMap = new HashMap<Integer,Integer>() ;
	HashMap<String, BTNode> huffmanLetterCodes ;
	HashMap<String, Integer> decodingMap = new HashMap<String, Integer>() ;
	HashMap<Integer, String> encodingMap = new HashMap<Integer, String>();
	HashMap<Integer, String> binaryMap = new HashMap<Integer, String>();
	compressedForm saveForm ;
	
	// Complexity :o(n)
	/*this function takes an input btnode and string , this function is recursive function the base
	 *  when the node is leaf we put in hashmap huffmanLetterCodes the key letter (string) and the value of the key is btnode
	the recursive call is if the node have left child we call the same function but with parameter of the left child and letter+"0"
	if the node have right child we call the same function but with parameter of the right child and the letter + "1"*/
		public void FillingTheEncodingAndDecodingMap() {
			for(Integer key : frequencyColorMap.keySet() ) {
				for(String key1 : huffmanLetterCodes.keySet() ) {
					if(frequencyColorMap.get(key)==huffmanLetterCodes.get(key1).data&&key==huffmanLetterCodes.get(key1).pixels) {
						decodingMap.put(key1, key);
						encodingMap.put(key, key1);
					}
				}
			}


			System.out.println("decoding");
			System.out.println(decodingMap.toString());
			System.out.println("encoding");
			System.out.println(encodingMap.toString());
		}
		
		//this function will take min heap and will build the huffman tree [Complexity: O(nlogn)]
		/*this function takes an input of huffheap , and go for loop until the heap size is 1
		initialize first BTNode and call the function remove() that get the root of the heap and than heapify
		and also initialize second BTNode and call also the function remove()
		and than initialize a new BTNode of name sumOftwoMin that have the data of the first node + the second node and 0
		and make the BTNode sumOftwoMin left child= first node and the right child = second node 
		and than insert the new node sumOfTwoMin to the heap
		last after the loop end we make the huffmenTree root = the root of the heap */
		public void BuildTheHuffmanTree(huffHeap heap) {
			while(heap.getSize()!=1) {
		        BTNode firstMin = heap.remove();
		        BTNode secondeMin = heap.remove();
		        BTNode sumOfTwoMins = new BTNode((int)firstMin.data+(int)secondeMin.data,0);
		        sumOfTwoMins.left = firstMin;
		        sumOfTwoMins.right = secondeMin;
		        heap.insert(sumOfTwoMins);
		    }
			
			huffmanTree.root = heap.remove() ;
			
		}
		
		//This Function will build the heap [complexity : O(nLogn)]
		/*this function does not take any input , first we initialize a new heap of huffheap(min heap) with size of the hashmap frequencyColorMap 
		for each key in the frequencyColorMap key set we initialize a new btnode with value of the key in frequencyColorMap and the key
		and than insert the new key to the heap
		last thing return the heap*/
		public huffHeap buildTheHeap() {
			huffHeap heap = new huffHeap(frequencyColorMap.size());
			System.out.println();

			for ( Integer key : frequencyColorMap.keySet() ) {
		           BTNode a =new BTNode(frequencyColorMap.get(key),key);
		            heap.insert(a);//O(Log n)
		        }
			return heap;
			
		}
		
		//This Function will Calculate the Frequency of pixels [complexity : O(H*W)]
		/*this function take an input of image and than look for every pixel in the image 
		if the pixel is a key in the hashmap frequencyColorMap we take the value and increment it
		and if does not contain the key we put it as a new key with value of 0*/
		public void calcTheFreq(Image image) {
			for(int i=0; i <image.buffImage.getHeight() ; i++) {
				for(int j = 0 ; j < image.buffImage.getWidth(); j++) {
					int key = image.buffImage.getRGB(j, i);
		            if(!frequencyColorMap.containsKey(image.buffImage.getRGB(j, i))) {
		            	frequencyColorMap.put(key, 0);
		            }
		            if(frequencyColorMap.containsKey(image.buffImage.getRGB(j, i))) {
		            	frequencyColorMap.put(key, frequencyColorMap.get(key)+1);
		            }
				}
				System.out.println();
			}
			System.out.println(frequencyColorMap.toString());
		}
		
		/* This function takes an input of image and than pass the image as a parameter to the function sittingthesaveform 
		and than again pass the image as a parameter to the function calcTheFreq and than initialize heap from the class huffheap and buildTheHeap
		after building the heap we call the function BuildTheHuffmanTree and than we initialize an empty string and initialize the huffmanLetterCodes to be new hashmap of string as a key and btnode as a value of the key
		and than call the Function generateLetterCodes and pass as a parameter the root of the Huffman tree and the empty string
		and than we call FillingTheEncodingAndDecodingMap and last thing we call the function DoingTheSaveForm and pass as a parameter the image
		*/
		//takes an image and calculate the frequent for the pixels and than build the Huffman tree
		public long huffmanEncode(Image image) {
			long start = System.nanoTime() ;
			SittingTheSaveForm(image);
			calcTheFreq(image);//O(H*W)
			huffHeap heap = buildTheHeap();//O(nLogn)
			BuildTheHuffmanTree(heap);//O(nlogn)
			String test = "";
			huffmanLetterCodes = new HashMap<String,BTNode>();
			generateLetterCodes(huffmanTree.getRoot() , test);

			FillingTheEncodingAndDecodingMap();//o(n)
			DoingTheSaveForm(image);//O(H*W)
			long end = System.nanoTime();
			
			return (end-start)/100000 ;
		}
		
		/* we take as an input an image we take the height and the width of the image and initialize the saveForm to be new compressedForm 
		and we change the attributes of the saveForm height and width to height and width of the image 
		and last thing we call the function set*/		
		public void SittingTheSaveForm(Image image) {
			int height = image.buffImage.getHeight();
			int width = image.buffImage.getWidth();
			saveForm = new compressedForm();
			saveForm.height = height;
			saveForm.width = width;
			saveForm.set();
		}
		
		//filling the class saveform for saving the object  O(H*W)
		/*This function takes an input of image , loop for all the pixel in the image we put at the 2-dim in 
		 * the save form at i and j the letter code and we get the letter from the encodingMap and the key is 
		 * the pixel of the image at (j,i) and after the loop we make the decoding map in the saveform 
		 * equals the decoding map and the hiffmanLetterCodes from save form to equals the huffmanLetterCodes*/
		public void DoingTheSaveForm(Image image) {
			for(int i=0; i <image.buffImage.getHeight() ; i++) {
				for(int j = 0 ; j < image.buffImage.getWidth(); j++) {
					
					saveForm.imageCompressed[i][j] = encodingMap.get(image.buffImage.getRGB(j, i));
			//		System.out.print(saveForm.imageCompressed[i][j] +" ");
				}
		//		System.out.println();
			}
			saveForm.decodingMap = decodingMap ;
			saveForm.huffmanLetterCodes = huffmanLetterCodes ; 
		}
		
		//take an input of BTNode and empty string and traverse the tree in pre-order fashion  [complexity : O(n)]
		/*this function takes an input btnode and string , this function is recursive function the base when the node is
		 *  leaf we put in hashmap huffmanLetterCodes the key letter (string) and the value of the key is btnode
		the recursive call is if the node have left child we call the same function but with parameter of the left child and letter+"0"
		if the node have right child we call the same function but with parameter of the right child and the letter + "1"*/
		private void generateLetterCodes(BTNode node , String letter ) { 
			
	    	if (node.left == null && node.right == null) {
	    		
	    		huffmanLetterCodes.put(letter, node);
	    	}else {
	    		
	    		if(node.left != null)
	    			generateLetterCodes(node.left, letter + "0");
	    		if(node.right != null)
	    			generateLetterCodes(node.right, letter + "1");
	    	}
	    
	} 
	/* this method take an object of a compressedForm which basically matrix with decoding map 
	 * we take the matrix and iterate through every pixel decoding it and assigning the value to buffer Image 
	 * than saving the buffered image into an image which is the retrieved form  */
	public long huffmanDecode(compressedForm compressedImage) {
		
		BufferedImage bufferedImage = new BufferedImage(compressedImage.width, compressedImage.height , BufferedImage.TYPE_INT_RGB);
		long start = System.currentTimeMillis() ;
		for(int i=0; i <bufferedImage.getHeight() ; i++) {
			for(int j = 0 ; j < bufferedImage.getWidth(); j++) {
		//		System.out.println(compressedImage.imageCompressed[i][j]);
				bufferedImage.setRGB(j, i,compressedImage.decodingMap.get(compressedImage.imageCompressed[i][j]) );
				//System.out.print(bufferedImage.getRGB(j, i) +" ");
			}
			//System.out.println();
		}
		long end = System.currentTimeMillis() ;
		Image retrivedImage = new Image();
		retrivedImage.buffImage = bufferedImage ; 
		retrivedImage.writeImage("retrived.bmp");
		return end -start;
	}
	
			
		
		
	


}
