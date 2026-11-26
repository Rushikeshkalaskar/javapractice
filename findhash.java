
import java.util.*;

public class MachineTest {

    // Q1. Pattern Printing
    static void printPattern() {
        String[] pattern = {
            "          A",
            "        A A B",
            "      B A A",
            "    C     C",
            "  A A   D   D",
            "A A B C D E E D C B A A",
            "  D   D",
            "    A A",
            "      C     C",
            "        B A A",
            "          A"
        };

        for (String row : pattern) {
            System.out.println(row);
        }
    }

    // Q2. Find all contiguous subarrays with target sum
    static void findSubarrays(int[] arr, int target) {
        System.out.println("Subarrays with sum " + target + ":");

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;

            for (int j = i; j < arr.length; j++) {
                sum += arr[j];

                if (sum == target) {
                    System.out.print("[");
                    for (int k = i; k <= j; k++) {
                        System.out.print(arr[k]);
                        if (k < j) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");
                }
            }
        }
    }

    // Q3. Longest palindromic substring without reverse()
    static String longestPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < str.length(); i++) {
            // Odd-length palindrome
            int left = i;
            int right = i;

            while (left >= 0 && right < str.length()
                    && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    start = left;
                    maxLength = right - left + 1;
                }
                left--;
                right++;
            }

            // Even-length palindrome
            left = i;
            right = i + 1;

            while (left >= 0 && right < str.length()
                    && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    start = left;
                    maxLength = right - left + 1;
                }
                left--;
                right++;
            }
        }

        return str.substring(start, start + maxLength);
    }

    // Q4. Group anagrams using Map without sorting or streams
    static void groupAnagrams(String[] arr) {
        Map<String, List<String>> map = new LinkedHashMap<>();

        for (String word : arr) {
            int[] frequency = new int[26];

            // Build character frequency manually
            for (int i = 0; i < word.length(); i++) {
                char ch = Character.toLowerCase(word.charAt(i));

                if (ch >= 'a' && ch <= 'z') {
                    frequency[ch - 'a']++;
                }
            }

            // Create a unique key from the frequencies
            StringBuilder keyBuilder = new StringBuilder();

            for (int count : frequency) {
                keyBuilder.append(count).append('#');
            }

            String key = keyBuilder.toString();

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }

            map.get(key).add(word);
        }

        System.out.println(map.values());
    }

    // Q5. Maximum contiguous subarray sum using Kadane's Algorithm
    static void maxSubarraySum(ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        int currentSum = list.get(0);
        int maxSum = list.get(0);

        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < list.size(); i++) {
            int value = list.get(i);

            if (value > currentSum + value) {
                currentSum = value;
                tempStart = i;
            } else {
                currentSum += value;
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        System.out.println("Maximum Sum: " + maxSum);
        System.out.print("Subarray: [");

        for (int i = start; i <= end; i++) {
            System.out.print(list.get(i));
            if (i < end) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }

    // Q6. Employee class implements Comparable
    static class Employee implements Comparable<Employee> {
        int id;
        String name;
        String department;
        double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        // Natural ordering: employee ID ascending
        @Override
        public int compareTo(Employee other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return "ID: " + id
                    + ", Name: " + name
                    + ", Department: " + department
                    + ", Salary: " + salary;
        }
    }

    static void sortEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee(103, "Rahul", "IT", 50000));
        employees.add(new Employee(101, "Amit", "HR", 60000));
        employees.add(new Employee(105, "Rahul", "IT", 50000));
        employees.add(new Employee(102, "Priya", "Sales", 70000));
        employees.add(new Employee(104, "Amit", "IT", 60000));

        System.out.println("Before sorting:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        // Comparator: salary descending, name ascending, ID ascending
        Comparator<Employee> comparator = (e1, e2) -> {
            int result = Double.compare(e2.salary, e1.salary);

            if (result == 0) {
                result = e1.name.compareTo(e2.name);
            }

            if (result == 0) {
                result = Integer.compare(e1.id, e2.id);
            }

            return result;
        };

        employees.sort(comparator);

        System.out.println("\nAfter sorting:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        // Demonstrate Comparable: sort by employee ID ascending
        Collections.sort(employees);

        System.out.println("\nSorted using Comparable (ID ascending):");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("========== Q1: PATTERN ==========");
        printPattern();

        System.out.println("\n========== Q2: TARGET SUM ==========");
        int[] arr = {1, 2, 3, -2, 5, -3, 2};
        findSubarrays(arr, 5);

        System.out.println("\n========== Q3: LONGEST PALINDROME ==========");
        String str = "forgeeksskeegfor";
        System.out.println("Longest palindrome: " + longestPalindrome(str));

        System.out.println("\n========== Q4: GROUP ANAGRAMS ==========");
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(words);

        System.out.println("\n========== Q5: KADANE'S ALGORITHM ==========");
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(-2);
        numbers.add(1);
        numbers.add(-3);
        numbers.add(4);
        numbers.add(-1);
        numbers.add(2);
        numbers.add(1);
        numbers.add(-5);
        numbers.add(4);

        maxSubarraySum(numbers);

        System.out.println("\n========== Q6: EMPLOYEE SORTING ==========");
        sortEmployees();
    }
}
