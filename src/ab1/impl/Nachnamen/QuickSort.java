package ab1.impl.Nachnamen;

import ab1.Sorter;

/**
 * QuickSort-Implementierung der Sorter-Schnittstelle.
 * Verwendet das mittlere Element eines Intervalls als Pivotelement.
 *
 * @author N.N.
 */
public class QuickSort implements Sorter {

    private int[] numbers;
    private int length;

    public void sort(int[] array) {
        this.numbers = array;
        length = array.length;
        quickSort(0, length - 1);
    }

    /**
     * @param i Left element of the array
     * @param j Right element of the array
     */
    private void quickSort(int i, int j) {
        int left = i;
        int right = j;

        // Choose Pivot element as last element of the list
        int pivot = numbers[j];

        // Divide into two lists
        while (left <= right) {
            /*
            Move from left to right until element > pivot found
             */
            while (numbers[left] < pivot) {
                left++;
            }
            /*
            Move from right to left until element < pivot found
             */
            while (numbers[right] > pivot) {
                right--;
            }
            /*
            As soon as two elements have been found, swap the elements
            Then increase left / decrease right (since they've already been checked)
             */
            if (left <= right) {
                swap(left, right);
                left++;
                right--;
            }
        }
        /*
        Now do recursive method call
         */
        if (i < right) {
            quickSort(i, right);
        }
        if (left < j) {
            quickSort(left, j);
        }
    }

    /*
    Method to mutually exchange elements of the array
     */
    private void swap(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
