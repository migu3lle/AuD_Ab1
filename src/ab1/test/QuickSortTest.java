package ab1.test;

import ab1.impl.GagglGundackerKopali.QuickSort;
import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {

    QuickSort quickSort = new QuickSort();
    int[] array = {4, 3, 2, 1};


    @Test
    public void printSorted(){
        System.out.println(Arrays.toString(array));
        quickSort.sort(array);

        System.out.println(Arrays.toString(array));
    }
}
