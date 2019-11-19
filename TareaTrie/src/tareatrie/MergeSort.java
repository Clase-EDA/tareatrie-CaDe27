/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareatrie;

/**
 *
 * @author CD
 */

public class MergeSort {
    /*
    1. Si el arrego es de tam 1 o 0 ya termine
    2. Parto el arreglo en 2 subarreglos del mismo tamanio (+- 1 elem)
    3. Aplica merge sort recursivamente a cada mitad
    4. mezcla los 2 sublistas ordenadas
    
    */
    
    /*
        esta version, la version mixcoac
        se encarga de ir mezclando parejas de subarreglos consecutivos
        de tamanio 2^i donde i va creciendo en cada iteracion
        primero mezcla las parejas de subarreglo de tamanio 1 y esto deja 
        subarreglos de tamanio 2 ordenados
        luego mezcla esos subarreglos de tam 2 en arreglos de tam 4 y sucesivamente...
        
        ejemplo  10 9 8 7 6 5 4 3 2 1
        (9 10) (7 8) (5 6) (3 4) (1 2) despues de la primera iteracion i = 0
        (7 8 9 10) (3 4 5 6) (1 2) despues de la segunda iteracion i = 1
        (3 4 5 6 7 8 9 10) (1 2) despues de la tercera iteracion i = 2
        (1 2 3 4 5 6 7 8 9 10) despues de la cuarta iteracion i = 3 = log n
    */
    public static <T extends Comparable <T>> void merge(T arr[], int l, int m, int r) { 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        Object L[] = new Object[n1]; 
        Object R[] = new Object [n2]; 
  
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
        int i = 0, j = 0; 
        int k = l; 
        while (i < n1 && j < n2) 
        {   
            if( ( (T)L[i] ).compareTo( (T)(R[j]) ) <= 0 ) 
            { 
                arr[k] = (T)L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = (T)R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        while (i < n1) 
        { 
            arr[k] = (T)L[i]; 
            i++; 
            k++; 
        } 
  
        while (j < n2) 
        { 
            arr[k] =(T) R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    public static <T extends Comparable <T>> void mergeSort(T[] arr, int l, int r){ 
        if (l < r) 
        { 
            int m = l + (r-l)/2; 
            mergeSort(arr, l, m); 
            mergeSort(arr , m+1, r); 
            merge(arr, l, m, r); 
        } 
    }  
    
    public static <T> void imprime(T[] a){
        int s = a.length;
        for(int i = 0; i < s; ++i)
            System.out.print(a[i]+" ");
        System.out.println("");
    }
    
    public static void main(String args[]){
        int n = 10000;
        Integer arre[] = new Integer[n];
        for(int i = 0; i < n; ++i)
            arre[n-i-1] = i;
        mergeSort(arre,0, arre.length-1);
        imprime(arre);
    }
    
}
