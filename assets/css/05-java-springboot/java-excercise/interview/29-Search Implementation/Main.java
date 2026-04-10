

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // ## ✅ Binary Search Implementation
        // ===========================================
        int[] arrBi = {1, 3, 5, 7, 9};
        System.out.println(binarySearch(arrBi, 7));

        // ## ✅ Linear Search Implementation
        // ===========================================
        int[] arrLIn = {4, 2, 7, 1};
        System.out.println(linearSearch(arrLIn, 7));

        // ## ✅ Bubble Sort, Selection Sort, Insertion Sort
        // ===========================================
        int[] a = {5, 1, 4, 2, 8};

        int[] b1 = a.clone();
        bubbleSort(b1);
        System.out.println("Bubble Sort: " + Arrays.toString(b1));

        int[] b2 = a.clone();
        selectionSort(b2);
        System.out.println("Selection Sort: " + Arrays.toString(b2));

        int[] b3 = a.clone();
        insertionSort(b3);
        System.out.println("Insertion Sort: " + Arrays.toString(b3));

        // ## ✅ Quick Sort & Merge Sort
        // ===========================================
        int[] aa = {5, 2, 9, 1, 5, 6};

        int[] q = aa.clone();
        quickSort(q, 0, q.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(q));

        int[] m = aa.clone();
        mergeSort(m, 0, m.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(m));
    }

    // ## ✅ Binary Search Implementation
    static int binarySearch(int[] arr, int key) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            }

            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // ## ✅ Linear Search Implementation
    static int linearSearch(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // ## ✅ Bubble Sort, Selection Sort, Insertion Sort
    static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }

    static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }

    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // ## ✅ Quick Sort & Merge Sort
    static void quickSort(int[] a, int left, int right) {
        if (left >= right) return;

        int i = left, j = right;
        int pivot = a[left + (right - left) / 2];

        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;

            if (i <= j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                i++;
                j--;
            }
        }

        if (left < j) quickSort(a, left, j);
        if (i < right) quickSort(a, i, right);
    }

    static void mergeSort(int[] a, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);

        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        }

        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        System.arraycopy(temp, 0, a, left, temp.length);
    }
}
