
// Create a method that takes integer array return the index of Firtst Element 
// that is greater than both of is neighbour 
class Main {
    public static void main(String[] args) {
        int[] numbers = {3, 6, 7, 9, 3};

        int result = findPeakIndex(numbers);

        System.out.println("Index: " + result);
    }

    public static int findPeakIndex(int[] nums) {
        // Edge case: array too small
        if (nums == null || nums.length < 3) {
            return -1;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i; // first such index
            }
        }

        return -1; // no such element found
    }
}

// ========================