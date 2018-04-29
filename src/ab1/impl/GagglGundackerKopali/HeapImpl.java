package ab1.impl.GagglGundackerKopali;

import ab1.Heap;
import ab1.Sorter;

import java.util.NoSuchElementException;

/**
 * Heap-Implementierung, die auf einem Array mit begrenzter Kapazität operiert.
 * Implementiert auch HeapSort.
 *
 * @author Felix Gaggl
 */
public class HeapImpl implements Heap, Sorter
{
    /**
     * Konstruktor.
     * @param capacity gewünschte Kapazität des Heap (maximale Anzahl von Elementen).
     */
    private int capacity;
    private int size;
    private int[] array;


    public HeapImpl(int capacity)
    {
        this.capacity = capacity;               //Übergebe Kapazität des Arrays dem Konstruktor
        this.array = new int[capacity];         //Erstelle ein int-array mit der vorher definierten Kapazität
    }


    /*
    * Überschreibt alle Inhalte des Arrays mit 0 und setzt die size des Arrays auf 0
    * */
    @Override
    public void clear()
    {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
        size = 0;
    }
    /*
     * Methode zum hinzufügen von Heap-Elementen
     * Zuerst wird gecheckt ob die Anzahl der Elemente nicht die Kapazität überschreitet, dies würde eine IndexOutOfBoundsException werfen
     * Dann wird der zu hinzufügende Key an die nächste freie Position im Array gespeichert und die variable size erhöht
     * Dann wird die Methode buildHeap aufgerufen, die die Heapeigenschaft wiederherstellt, da dies nach jedem hinzufügen erfolgen muss
     *  */
    @Override
    public void add(int key) throws IndexOutOfBoundsException
    {
        if (size >= capacity){
            throw new IndexOutOfBoundsException();
        }
        array[size] = key;
        size++;


        buildHeap(array);

    }
    /*
    * Methode die das Maximum des Heaps zurückliefert
    * Zuerst wird gecheckt ob der Heap überhaupt ein Element hat, sprich ob überhaupt ein Maximum vorhanden ist, sonst NoSuchElementException
    * Danach wird das erste Element(Maximum) mit dem letzten getauscht
    * und die Größe des Heaps reduziert da eines ja entfernt wurde
    * dann wird die Funktion buildHeap aufgerufen, die die Heapeigenschaft wiederherstellt
    * schließlich wird das Maximum returned*/
    @Override
    public int removeMax() throws NoSuchElementException
    {
        if(size == 0){
            throw new NoSuchElementException();
        }

        int temp = array[0];
        array[0] = array[size-1];
        array[size-1] = temp;

        size--;
        buildHeap(array);
        return temp;

    }
    /*
    * Methode die das Maximum des Heap zurückliefert
    * Zuerst wird gecheckt ob es überhaupt ein Element im Heap gibt, sonst NoSuchElementException
    * Dann wird das Element an der Stelle 0 returned, da dieses nach den Heapeigenschaften das Maximum ist*/
    @Override
    public int max() throws NoSuchElementException
    {
        if(size == 0){
            throw new NoSuchElementException();
        }
        return array[0];
    }
    /*
    * Methode die, die Anzahl der Elemente zurückliefert*/
    @Override
    public int size()
    {
        return size;
    }

    /*
    * Methode, die ein Array sortiert, welches übergeben wird
    * Zuerst wird die Größe des Heaps auf die Größe des Arrays gesetzt
    * Dann wird die Heapeigenschaft hergestellt mit der Methode Heapify, dies wird für Elemente mit dem Index < size ausgeführt, so wie es der Heap Algorithmus erfordert
    * In der zweiten for-Schleife dieser Methode wird dann jeweils das größte Element an die letzte Position getauscht und die Größe des Heaps verringert, somit steht der Teil nach dem Heap im Array als sortierter Teil
    * Nach jedem Tauschvorgang wird die Heapeigenschaft wiederhergestellt
    * */
    @Override
    public void sort(int[] array)
    {
        size = array.length;
        for (int i = size / 2; i >= 0; --i) {
            heapify(array, i);
        }

        for (int i=array.length-1; i>=0; i--)
        {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            size--;
            heapify(array, 0);
        }

    }

    /*
    * Hilfsmethode, die die Heapeigenschaft herstellt
    * Wenn der Heap nur die Größe 1, muss nicht weiter getauscht werden, da dieses Element immer richtig positioniert ist
    * Sonst werden für alle Elemente mit dem Index < size/2 die heapify methode aufgerufen*/
    private void buildHeap(int [] array){
        if(size == 1){

        }else {

            for (int i = size / 2; i >= 0; --i) {
                heapify(array, i);

            }
        }
    }
    /*
    * Methode, die die Tauschoperationen am Heap ausführt um die Heapeigenschaft herzustellen
    * Zuerst werden drei Variablen definiert, eine steht für den Knoten, eine für den linken Kindknoten und eine für den rechten Kindknoten
    * Dann wird überprüft ob sich der linke/rechte Knoten im Heap befinden und ob der Inhalt größer ist als der Inhalt des Elternknotens
    * Wenn dies beides zutrifft wird die Variable für den Elternknoten auf jeweils die Variable des Kindknotens gesetzt
    * Dies wird in der dritten if-Abfrage überprüft und wenn es eingetroffen ist wird der Inhalt des Eltern und des Kind- knotens vertauscht
    * Dies wird dann rekursiv auf den Knoten angewendet der im "Baum" nach unten getauscht wurde
    *
    * */
    private void heapify(int[] array, int i) {
        int temp = i;
        int l = 2*i+1;
        int r = l+1;

        if(l < size && array[l] > array[temp]){
            temp = l;
        }
        if(r < size && array[r] > array[temp]){
            temp = r;
        }

        if(temp != i){
            int swap = array[i];
            array[i] = array[temp];
            array[temp] = swap;

            heapify(array, temp);
        }




    }
}