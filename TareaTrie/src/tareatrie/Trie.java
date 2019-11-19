/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareatrie;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CD
 */

/*
    Trie
    -también conocidos como prefix tree
    -Son árboles de búsqueda
/   -A diferencia de los árboles de búsqueda vistos hadta ahora,
    los nodos en un Trie no almacenan la llave completa, sino que almacenan sólo
    un símbolo de la llave. De hecho almacenan muchos símbolos en la secuencia 
    de muchas llaves. => las llaves
    tienen que ser cadenas de símbolos

    -la posición de un nodo en el árbol(comenzando desde la raíz) corresponde a
     posición del símbolo en la llave almacenada
    
    -La llabe indica como moverte por el árbol para llegar al dato deseado
    (muchas veces el dato es la llave deseada)

    la a en ascci empieza en 97
*/
public class Trie {
    class NodoTrie{
        NodoTrie hijos[];
        int hijosInicializados, cantHijos;
        boolean finPalabra;
        
        public NodoTrie(){ 
            hijos = new NodoTrie[256];
            cantHijos = 256;
            hijosInicializados = 0;
            this.finPalabra = false;
        }
        
        public NodoTrie(int numHijos){
            hijos = new NodoTrie[numHijos];
            cantHijos = numHijos;
            hijosInicializados = 0;
            this.finPalabra = false;
        }
        
        //public int getPosSig(Character l, Character[] simbolos) deberia ser con Hash
        
        //regresa el indice del arreglo en el que se encuentra el caracter l
        
    }
    
    private static Character simbolos[];
    private int simbolosLength;
    private NodoTrie raiz;
    
    public Trie(){
        simbolos = new Character[256];
        for(int i = 0; i < 256; ++i)
            simbolos[i] = (char)(i);
        simbolosLength = 256;
        raiz = new NodoTrie();
    }
    
    public Trie(Character simbolos[]){
        Arrays.sort(simbolos);
        Trie.simbolos = simbolos.clone();
        simbolosLength = simbolos.length;
        raiz = new NodoTrie();
    }
    
    
    public boolean busca(String llave){
       if(llave == null)
           throw new NullPointerException();
       else
           return busca(llave, 0, llave.length(), raiz);
    }
    
    private boolean busca(String llave, int charActual, int longitud, NodoTrie actual){
        if(charActual == longitud)
            return actual.finPalabra;
        else{
            int pos = (int)llave.charAt(charActual);//actual.getPosChar((int)llave.charAt(charActual), simbolos)
            return actual.hijos[pos] == null? 
                    false : busca(llave, 1+charActual, longitud,actual.hijos[pos]);
        }
    }
    
    public void inserta(String llave){
        if(llave == null)
           throw new NullPointerException();
        else
           inserta(llave, 0, llave.length(), raiz);
    }
    
    private void inserta(String llave,int charActual, int longitud, NodoTrie actual){
        if(charActual == longitud)
            actual.finPalabra = true;
        else{
            int pos = (int)llave.charAt(charActual); //actual.getPosChar((int)llave.charAt(charActual), simbolos)
            if(actual.hijos[pos] == null){
                actual.hijosInicializados += 1;
                actual.hijos[pos] = new NodoTrie(simbolosLength);
            }
            inserta(llave, 1+charActual, longitud, actual.hijos[pos]);
        } 
    }   
    
    public boolean borra(String llave){
        if(llave == null)
           throw new NullPointerException();
        else
            return borra(llave, 0, llave.length(), raiz); 
    }
    
    private boolean borra(String llave, int charActual, int longitud, NodoTrie actual){
        boolean resp;
        //si si esta, la borro
        if(charActual == longitud){
            resp = actual.finPalabra;
            actual.finPalabra = false;
            return resp;
        }
        
        //primero lo busco, y de regreso, si el hijo de donde se borro no tiene
        // tiene hijos inicializados y su fin de palabra es falso, lo hago nulo
        else{
            int pos = (int)llave.charAt(charActual);//actual.getPosChar((int)llave.charAt(charActual), simbolos)
            NodoTrie sig = actual.hijos[pos];
            //si el caracter actual no esta inicializado, no esta la palabra
            if(sig == null)
                return false;
            //si el caracter actual si esta, puede que si exista la palabra
            else{
                resp = borra(llave, charActual+1, longitud, sig);
                //despues de regresar de la llamada recursiva
                if(sig.hijosInicializados == 0 && sig.finPalabra == false){
                    sig = null;
                    actual.hijosInicializados -=1;
                }
            }
            return resp;
        }  
    }
    
    private void preOrder(ArrayList<String> lista){
        preOrder("", raiz, lista);
    }
    
    private void preOrder(String palabra, NodoTrie actual, ArrayList<String> lista){
            if(actual.finPalabra)
                lista.add(palabra);
            int i = -1, cont = 0;
            for(NodoTrie h : actual.hijos){
                ++i;
                if(h != null){
                    preOrder(palabra + simbolos[i], h, lista);
                    ++cont;
                }
                if(actual.hijosInicializados < i)
                    break;
            }
    }
    
    public static ArrayList<String> ordenaLexicograficamente(ArrayList<String> palabras){
        if(palabras == null)
            throw new NullPointerException();
        Trie t = new Trie();
        for(String p : palabras)
            t.inserta(p);
        palabras = new ArrayList<>();
        t.preOrder(palabras);
        return palabras;
    }
    
    @Override
    public String toString(){
        ArrayList<String> palabras = new ArrayList<>();
        preOrder(palabras);
        StringBuilder sb = new StringBuilder();
        sb.append("Palabras en el árbol:\n");
        for(String p:palabras)
            sb.append('\t').append("•").append(p).append('\n');
        return sb.toString();
    }
    
    public static void main(String args[]){
        Trie a = new Trie();
        a.inserta("hola");
        a.inserta("heuristica");
        a.inserta("kha");
        a.inserta("hormiga");
        String s = a.toString();
        System.out.println(s);
        System.out.println(a.borra("hola"));
        System.out.println(a.borra("kha"));
        System.out.println(a.borra("kiubo"));
        System.out.println(a);
        
        System.out.println(a.busca("heuristica"));
        
        ArrayList<String> b;
        b= new ArrayList<>(Arrays.asList("no", "se", "como", 
                "ordenar", "esto"));
        b = Trie.ordenaLexicograficamente(b);
        System.out.println(b);
        
        b= new ArrayList<>(Arrays.asList("a", "aa", "ab", "ac", "ad", "b", "bc", "c"));
        b = Trie.ordenaLexicograficamente(b);
        System.out.println(b); 
        
        b= new ArrayList<>(Arrays.asList("b", "c", "z", "bc", "a", "aab", "az", "c"));
        b = Trie.ordenaLexicograficamente(b);
        System.out.println(b); 
    }
    
}
