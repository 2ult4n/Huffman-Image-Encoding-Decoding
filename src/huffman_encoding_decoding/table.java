package huffman_encoding_decoding;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class table extends JFrame {

	 public table(HashMap<String, BTNode> huffmanLetterCodes){
		 
	        //headers for the table
	        String[] columns = new String[] {
	            "Pixel", "Frequncy", "LetterCode"
	        };
	         
	        //actual data for the table in a 2d array
	        Object[][] data = new Object[huffmanLetterCodes.size()][3];
	        int i = 0 ; 
	        for ( String key : huffmanLetterCodes.keySet() ) {
				data[i][0] = huffmanLetterCodes.get(key).pixels ; 
				data[i][1] = huffmanLetterCodes.get(key).data ; 
				data[i][2] = key ; 
				i++; 
		        }
	        
	        //create table with data
	        final Class[] columnClass = new Class[] {
	                Integer.class, Integer.class, String.class
	            };
	            //create table model with data
	            DefaultTableModel model = new DefaultTableModel(data, columns) {
	                @Override
	                public boolean isCellEditable(int row, int column)
	                {
	                    return false;
	                }
	                @Override
	                public Class<?> getColumnClass(int columnIndex)
	                {
	                    return columnClass[columnIndex];
	                }
	            };
	             
	            JTable table = new JTable(model);
	        //add the table to the frame
	        this.add(new JScrollPane(table));
	        
	        this.setTitle("Letter Code Table");
	        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	        this.pack();
	        this.setVisible(true);
	        
	    }
	 
	

}
