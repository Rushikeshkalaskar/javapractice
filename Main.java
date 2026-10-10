
public class Main {

    static void generateSubsets(int[] arr, int index,
                                int[] subset, int size) {

        // Base condition
        if (index == arr.length) {
            System.out.print("[");
            for (int i = 0; i < size; i++) {
                System.out.print(subset[i]);
                if (i < size - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            return;
        }

        // Exclude current element
        generateSubsets(arr, index + 1, subset, size);

        // Include current element
        subset[size] = arr[index];
        generateSubsets(arr, index + 1, subset, size + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[] subset = new int[arr.length];

        generateSubsets(arr, 0, subset, 0);
    }
}