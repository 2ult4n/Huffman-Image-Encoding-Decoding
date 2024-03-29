package huffman_encoding_decoding;
 
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
/* this class forked from https://github.com/EslaMx7/AI-Tasks-JADE-Tests/blob/master/src/trees/tasks/treeGUI.java 
 * to show Huffman tree after encoding  * edited to suit the needs of the project  */

public class treeGUI extends JFrame {
 
    private JPanel contentPane;
    public LinkedBT tree;
    public DrawTree drawer;

    public treeGUI(LinkedBT tree) {
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        drawer = new DrawTree(tree);
        drawer.revalidate();
        contentPane.add(drawer);
        setContentPane(contentPane);
        this.tree = tree;
        setVisible(true);
    }
 
}
 
class DrawTree extends JPanel{
   
    public LinkedBT tree;
   
    public DrawTree(LinkedBT tree){
        this.tree = tree;
        
        
    }
   
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
   
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        //g.drawString(String.valueOf(tree.root.data), this.getWidth()/2, 30);
       
 
            //DrawNode(g, tree.root,100, 50,2);
 
         
			DrawTree(g, 0, getWidth(), 0, getHeight() / tree.getheight(tree.root), tree.root);
		
    }
   
    public void DrawNode(Graphics g,BTNode n,int w,int h,int q){
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
       
        if(n!=null){
        	
            g.drawString(String.valueOf(n.data), (this.getWidth()/q)+w, h);
            if(n.left !=null)
                DrawNode(g, n.left, -w, h*2, q);
                //DrawNode(g, n.left, -w, h*2, q);
                //g.drawString(String.valueOf(n.left.data), (this.getWidth()/q)-w, h+50);
            if(n.right !=null)
                DrawNode(g, n.right, w*2, h*2, q);
            //g.drawString(String.valueOf(n.right.data), (this.getWidth()/q)+w, h+50);
        }
   
    }

    public void DrawTree(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, BTNode node)  {
        String data = String.valueOf(node.data);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        
        FontMetrics fm = g.getFontMetrics();
        int dataWidth = fm.stringWidth(data);
        g.drawString(data, (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);
        
        //g.fillOval((StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2, 50, 50);
        if(node.left!=null) {
            g.drawLine((StartWidth + EndWidth) / 2 - dataWidth / 2,  StartHeight + Level / 2, (StartWidth + (StartWidth + EndWidth) / 2) / 2 - dataWidth / 2, StartHeight + Level + Level / 2);
            g.drawString("(0)",  ((StartWidth + EndWidth) / 2 - dataWidth / 2)-dataWidth-30, (StartHeight + Level / 2)+40);
       }
        
        if(node.right!=null) {
            g.drawLine((StartWidth + EndWidth) / 2 - dataWidth / 2,StartHeight + Level / 2,((StartWidth + EndWidth) / 2 + EndWidth) / 2 - dataWidth / 2,StartHeight + Level + Level / 2);
            g.drawString("(1)", ((StartWidth + EndWidth) / 2 - dataWidth / 2)+dataWidth, (StartHeight + Level / 2)+40);
        }
 
        if (node.left != null)            
            DrawTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.left);
       
        if (node.right != null)
            DrawTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.right);
    }
   
   
}