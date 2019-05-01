package huffman_encoding_decoding;



/*this class has written to provide easier parsing for the information to be written in csv
 * it implement comparable interface for easier comparison --> explained in compareTo */

public class CSVParser implements Comparable  {
	
	int size ; 
	long time ;
	
	
	public CSVParser(int size, long encodingTime) {
		super();
		this.size = size;
		this.time = encodingTime;
	}

// overriding comparable interface native method compareTo for future use in Collections library sort methods 
	@Override
	public int compareTo(Object o) {
		CSVParser object = (CSVParser) o ; 
		if ((long) this.time < (long) object.time)
	        return -1;
	    if (this.time == object.time)
	        return 0;

	    return 1;
	}
	
	@Override
	public String toString() {
		return "size : "+ this.size +" time : " + this.time ;
	}









}
