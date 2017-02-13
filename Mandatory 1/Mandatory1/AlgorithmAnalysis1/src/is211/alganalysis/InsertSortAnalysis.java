/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is211.alganalysis;

/**
 *
 * @author evenal
 */
public class InsertSortAnalysis extends EmpiricalAnalysis {

    public InsertSortAnalysis() {
        super("Insertion sort");
    }



    protected void run(int[] shuffledData) {
        insertSort(shuffledData);   
    }

    public void insertSort(int[] data) {
        int n = assign(data.length);
        
        for (int i = assign(1); lessThan(i, n); assign(i++)) {
            int nextval = assign(data[i]);
            int j = assign(i-1);
            while ((greaterThan(j,0) || equal(j,0)) && greaterThan(data[j], nextval)) {
                data[j + 1] = assign(data[j]);
                    assign(j--);
            }
            data[j+1] = assign(nextval);
        }
    }

    private static void pp(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(" " + data[i]);
        }
        System.out.println();
        System.out.flush();
    }
}
