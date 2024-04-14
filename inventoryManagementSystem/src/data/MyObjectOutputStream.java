package data;

//Importing input output classes
import java.io.*;

//Class 1
//helper class
class MyObjectOutputStream extends ObjectOutputStream {

 // Constructor of this class
 // 1. Default
 MyObjectOutputStream() throws IOException
 {

     // Super keyword refers to parent class instance
     super();
 }

 // Constructor of this class
 // 1. Parameterized constructor
 MyObjectOutputStream(OutputStream o) throws IOException
 {
     super(o);
 }

 // Method of this class
 public void writeStreamHeader() throws IOException
 {
     return;
 }
}