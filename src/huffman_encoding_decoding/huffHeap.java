package huffman_encoding_decoding;
/*regular heap class forked from : https://gist.github.com/flexelem/70b120ac9bf2965f419f 
 * edited to suit the needs of the project  */
public class huffHeap  {
	   
    private BTNode[] heap;
    private int size;
    private int maxSize;
 
    public huffHeap(int maxSize){
        this.maxSize = maxSize;
        this.size = 0;
        heap = new BTNode[this.maxSize + 1];
        BTNode a = new BTNode(Integer.MIN_VALUE,0);
        heap[0]=a;
       
       
    }
 
    private int parent(int element){
        return element / 2;
    }
 
    private int leftChild(int element){
        return (2 * element);
    }
   
    private int rightChild(int element){
        return (2 * element) + 1;
    }
 
    private boolean isLeaf(int element) {
        if (element >= (size / 2) && element <= size) {
            return true;
        }
        return false;
    }
 
    private void swap(int fpos, int spos)
    {
        BTNode tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void minHeapify(int i) {

        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= size - 1 && (int)heap[left].data < (int)heap[i].data) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= size - 1 && (int)heap[right].data < (int)heap[smallest].data) {
            smallest = right;
        }

        // if the smallest key is not the current key then bubble-down it.
        if (smallest != i) {

            swap(i, smallest);
            minHeapify(smallest);
        }
    }
 
    public void insert(BTNode element)
    {
        heap[++size] = element;
        int current = size;
        while ((int)heap[current].data < (int)heap[parent(current)].data)
        {
            swap(current,parent(current));
            current = parent(current);
        }  
    }
     public int getSize() {
            return size;
        }
   
     public BTNode remove()
        {
            BTNode popped = heap[1];
            heap[1] = heap[size--];
            minHeapify(1);
           // print();
            return popped;
        }
    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.print(" PARENT : " + heap[i].data + " LEFT CHILD : " + heap[2*i].data
                + " RIGHT CHILD :" + heap[2 * i  + 1].data);
            System.out.println();
        }
    }

}