package as3;

import java.util.Arrays;

public class Experiment {
    private Sorter sorter;
    private Searcher searcher;

    public Experiment(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }

    public long measureSortTime(int[] arr, String type) {
        long startTime = System.nanoTime();

        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(arr);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(arr);
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();
        searcher.search(arr, target);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000, 5000};

        System.out.println("Algorithm Performance Comparison");
        System.out.println("================================");

        for (int size : sizes) {
            System.out.println("\n--- Array Size: " + size + " ---");

            int[] randomArray = sorter.generateRandomArray(size);

            int[] bubbleArray = Arrays.copyOf(randomArray, randomArray.length);
            long bubbleTime = measureSortTime(bubbleArray, "basic");
            System.out.printf("Bubble Sort Random: %d ns (%.4f ms)\n",
                    bubbleTime, bubbleTime / 1_000_000.0);

            int[] quickArray = Arrays.copyOf(randomArray, randomArray.length);
            long quickTime = measureSortTime(quickArray, "advanced");
            System.out.printf("Quick Sort Random:  %d ns (%.4f ms)\n",
                    quickTime, quickTime / 1_000_000.0);

            int target = quickArray[size / 2];
            long searchTime = measureSearchTime(quickArray, target);
            System.out.printf("Binary Search:      %d ns\n", searchTime);

            System.out.println("--- Already Sorted Array ---");

            int[] sortedBubbleArray = Arrays.copyOf(quickArray, quickArray.length);
            long bubbleSortedTime = measureSortTime(sortedBubbleArray, "basic");
            System.out.printf("Bubble Sort Sorted: %d ns (%.4f ms)\n",
                    bubbleSortedTime, bubbleSortedTime / 1_000_000.0);

            int[] sortedQuickArray = Arrays.copyOf(quickArray, quickArray.length);
            long quickSortedTime = measureSortTime(sortedQuickArray, "advanced");
            System.out.printf("Quick Sort Sorted:  %d ns (%.4f ms)\n",
                    quickSortedTime, quickSortedTime / 1_000_000.0);
        }
    }
}