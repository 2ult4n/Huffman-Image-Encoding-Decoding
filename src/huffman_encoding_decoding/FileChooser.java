package huffman_encoding_decoding;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



/* this class have been forked from javadoc site as a template and edited 
 * to suit the project needs GUI wise 
 * its integrated with anthor gui swing application tree GUI */

public class FileChooser extends JFrame {
private JTextField filename = new JTextField(), dir = new JTextField() ,huffmanLetter = new JTextField() ; 
private JButton open = new JButton("Choose File") ,encode = new JButton("Compress Image"), decode = new JButton("Decompress Image");


	public FileChooser() {
		JPanel p = new JPanel();
		open.addActionListener(new OpenL());
		
		p.add(open);
		p.add(encode);
		p.add(decode);
		encode.setEnabled(false);
		decode.setEnabled(false);		
		Container cp = getContentPane();
		cp.add(p, BorderLayout.SOUTH);
		dir.setEditable(false);
		filename.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(filename);
		p.add(dir);
		huffmanLetter.setEditable(false);
		
	
		cp.add(p, BorderLayout.NORTH);
	}

	class OpenL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = new JFileChooser();
			c.setCurrentDirectory(new File(System.getProperty("user.dir")+"\\pics\\"));
			int rVal = c.showOpenDialog(FileChooser.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				filename.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());
				if(filename.getText().substring(filename.getText().length() -3).equals("bmp")) {
					encode.setEnabled(true);
					encode.addActionListener( new ActionListener() {
			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						Image imageFile = new Image();
						imageFile.readImage(dir.getText()+"\\"+filename.getText(), true);
						HuffmanUtils huffmanEncoder = new HuffmanUtils();
						huffmanEncoder.huffmanEncode(imageFile);
						treeGUI treeVisiulaizer = new treeGUI(huffmanEncoder.huffmanTree);
						//huffmanLetter.setText(huffmanEncoder.huffmanLetterCodes.toString());
						table t = new table(huffmanEncoder.huffmanLetterCodes);
						
						try {
							huffmanEncoder.saveForm.write(huffmanEncoder.saveForm, "compressed");
							filename.setText(filename.getText()+" file have been compressed sucsessfully");
							encode.setEnabled(false);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				
				}else {
					decode.setEnabled(true);
					decode.addActionListener( new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg1) {
							
							compressedForm compressedImage = new compressedForm();
		                    HuffmanUtils huffmanDecoder = new HuffmanUtils();
							try {
								compressedImage = compressedImage.read("compressed");
			                        huffmanDecoder.huffmanDecode(compressedImage);
			                        filename.setText(filename.getText()+" file have been Decompressed sucsessfully");
			                        encode.setEnabled(false);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					});
					
				}
			}
			
			
			if (rVal == JFileChooser.CANCEL_OPTION) {
				filename.setText("You pressed cancel");
				dir.setText("");
			}
		
		}
	}



	public static void main(String[] args) {
		
		 try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	        
	      
	        
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	
	  run(new FileChooser(), 500, 250);
	}

public static void run(JFrame frame, int width, int height) {
	frame.setTitle("Huffman Encoder Decoder");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(width, height);
	frame.setVisible(true);
	
	}

} 