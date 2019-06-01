# Huffman-Image-Encoding-Decoding
>This Project is an application of Huffman algorithm for encoding and decoding images 
>it was final project of Design & Analysis of Algorithms Course in KSU .
>it contain the core application with along of some tools to asses overall performance .
>the application have visualization of the final huffman tree.

Program starts from FileChoser Class

### FileChoser
> it will launch main graphical interface 

### File Chooser have three buttons 
  - Choose File
  - Compress File 
  - Decompress File

#### Choose File 
> it will open a panel to the system viewer in user directory to choose file 
> upon the selection will allow one button either Compress File or Decompress File 

#### Compress File 
> this class will call an instance of object HuffmanUtils and will call encode method to build
> huffman utils class and well give an output of Compressed File , Encoding Tree , Letter Codes and frequncy table
> the Compressed File will be at the file pics in the same directory that the executable saved on labled 'compressed'

#### Decompress File
> this class will call an instance of object HuffmanUtils and will call decode method to build
> huffman utils class and well give an output of Original File 
> the Reteived File will be at the file pics in the same directory that the executable saved on labled 'retrived'

### Closing
> program will exit upon closing the main interface you can encode and decode any number of images you need :)



