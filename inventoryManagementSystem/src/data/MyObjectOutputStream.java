/**********************************************
Workshop 5&6
Course:APD545 - Winter 2024
Last Name: Pillay
First Name:Steven David
ID:162218218
Section:ZAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature
Date:14-04-2024
**********************************************/
package data;
//This a custom output Stream that doesn't write the file header in the binary
//Importing input output classes
import java.io.*;


class MyObjectOutputStream extends ObjectOutputStream {

 MyObjectOutputStream() throws IOException {
     super();
 }

 MyObjectOutputStream(OutputStream o) throws IOException {
     super(o);
 }

 public void writeStreamHeader() throws IOException {
     return;
 }
}