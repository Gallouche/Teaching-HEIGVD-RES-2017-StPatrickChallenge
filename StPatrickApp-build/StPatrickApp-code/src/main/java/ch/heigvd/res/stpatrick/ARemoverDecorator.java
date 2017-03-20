/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.stpatrick;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 *
 * @author Gallouche
 */
public class ARemoverDecorator implements IStreamDecoratorController{

   @Override
   public Reader decorateReader(Reader inputReader) {
      return new Reader(inputReader){
         @Override
         public int read(char[] cbuf, int off, int len) throws IOException {
            int c = inputReader.read(cbuf, off, len);
            
            if(c != 'a' || c!= 'A')
               return c;
            else
               return inputReader.read(cbuf,off+1,len);
         }

         @Override
         public void close() throws IOException {
            inputReader.close();
         } 
      };
   }

   @Override
   public Writer decorateWriter(Writer outputWriter) {
      return new Writer(outputWriter){
         @Override
         public void write(char[] cbuf, int off, int len) throws IOException {
            for(int i = off; i< len;i++){
               if(cbuf[i] != 'a' && cbuf[i] != 'A')
                  outputWriter.write(cbuf[i]);
            }
         }

         @Override
         public void flush() throws IOException {
            outputWriter.flush();
         }

         @Override
         public void close() throws IOException {
            outputWriter.close();
         }    
      };
   }
   
}
