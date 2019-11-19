/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareatrie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author CD
 */
public class TareaTrie {
    
    public static void main(String[] args) throws FileNotFoundException, InterruptedException{
        for(int t = 5; t <=100;t+=5){
            Scanner s = new Scanner(new File("words.txt"));
            ArrayList<String> lista = new ArrayList<>();
            String arre[] = new String[100005];
            String p;
            for(int i = 0; i < 1000*t; ++i){  
                p = s.next();
                if(p.contains("#!comment:")){
                    s.nextLine();
                    p = s.next();
                }
                p = p.toLowerCase();
                lista.add(p);
                arre[i] = p;
            }
            System.out.print("Con "+(1000*t)+" palabras: ");
            long startTime, endTime; 
               startTime = System.currentTimeMillis();
               MergeSort.mergeSort(arre, 0, 1000*t-1);
               TimeUnit.SECONDS.sleep(1);
               endTime = System.currentTimeMillis();
               System.out.print("Merge: "+(endTime-startTime-1000));
               
               startTime = System.currentTimeMillis();
               Trie.ordenaLexicograficamente(lista);
               TimeUnit.SECONDS.sleep(1);
               endTime = System.currentTimeMillis();
               System.out.println("  Trie: "+(endTime-startTime-1000));
            }
         }
}
