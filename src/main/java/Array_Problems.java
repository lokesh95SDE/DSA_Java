import java.util.Arrays;
//InnerClass is an inner class, and it can access all members (including private) of its enclosing class OuterClass. It requires an instance of OuterClass to be instantiated.
//Because InnerClass is private, it cannot be instantiated outside the context of OuterClass. Any attempt to instantiate it from outside OuterClass (e.g., from another class) would result in a compilation error due to InnerClass being inaccessible due to its private access modifier.
public class Array_Problems {

    public static void main(String[] args) {
        int[] arr = {2, -3, 1, 2, 4, 3};
        int dataValues[] = {3,8,9,2,7,1};
        int dataValues1[] = {8,5,9,1,2,3};
//////        *****Sorting_BrutForce_Algo*****
//        bubbleSortins2(dataValues);     // need to move bigger number to end of the array by comparing [j]>[j+1]
//        selectionSorting(arr);        // need to find min number in array and swap it into first index and repeat the process for next index
        insertionSorting(dataValues1);
//
//////      ****Kadane_Algo****
////        maxSubArray(arr);
//
//////      ****Greedy_Algo*****
//        int[] arr_price = {7, 1, 5, 3, 6, 4};
//        int[] arr_majo = {7, 1,1,1, 5, 3,3,4,2,1, 4};
//        buyandSell_I_Greedy(arr_price);
//        buyandSell_II_Greedy(arr_price);
//        MajorityElement(arr_majo);
//
//////        *****Binary_Search_Algo****
//        minCapability(dataValues,2);
//
//////in-place matrix marking technique
//        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        setZeroes(matrix);
//        int[]  arr_pcr = {1, 2,3};
//        nextPermutation(arr_pcr);   ///pivot-->swap-->reverse
//
    }

    ////    *************************************************************************************************************|
    /// here we have O(n2) same time runs all for loop[]
    public static void bubbleSortins(int[] dataValues){
        int valueCount = dataValues.length-1;
        for(int i=0; i<=valueCount;i++){
            for(int j=0;j<valueCount;j++){
                if(dataValues[j]>dataValues[j+1]){
                    int temp = dataValues[j];
                    dataValues[j] = dataValues[j+1];
                    dataValues[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(dataValues));
    }
    /// here we have O(n2) same time runs half of all for loop[]
    public static void bubbleSortins1(int[] dataValues){ //{3,8,9,2,7,1}
        int n = dataValues.length; //6
        for(int i=1; i<=n-1;i++){//(6-1=5)
            for(int j=0;j<n-i;j++){//(6-1=5, 6-2=4, 6-3=3, 6-4=2, 6-5=1)
                if(dataValues[j]>dataValues[j+1]){     // 4>9,4>
                    int temp = dataValues[j];
                    dataValues[j] = dataValues[j+1];
                    dataValues[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(dataValues));
    }
    /// here making into O(n) by skipping the already sorted values
    public static void bubbleSortins2(int[] dataValues){ //{3,8,9,2,7,1}
        int n = dataValues.length; //6
        boolean swapped;
        for(int i=1; i<n-1;i++){//(6-1=5)
            swapped = false;
            for(int j=0;j<n-i;j++){//(6-1=5, 6-2=4, 6-3=3, 6-4=2, 6-5=1)
                if(dataValues[j]>dataValues[j+1]){     // 4>9,4>
                    int temp = dataValues[j];
                    dataValues[j] = dataValues[j+1];
                    dataValues[j+1] = temp;
                    swapped = true;
                }
            }
            if(swapped==false){
                break;
            }
        }
        System.out.println(Arrays.toString(dataValues));
    }

    // 5,4,2,1,3
    //i,j-->minIndex=i
    //j<minIndex-->minIndex=j
    //i,minIndex
//    i
//   {8,5,9,2,4,3,1}
//    m j
//      m j
//      m   j
//          m j
//          m   j
//          m     j
//                m
//    Swap final i and M

    public static void selectionSorting(int[] dataValues){
        int valueCount = dataValues.length;
        for(int i=0; i<valueCount; i++){
            int minIndex = i;
            for(int j=i+1; j<valueCount; j++){
                if(dataValues[j]<dataValues[minIndex]){
                    minIndex = j;
                }
            }
            int temp = dataValues[i];
            dataValues[i] = dataValues[minIndex];
            dataValues[minIndex] = temp;
        }
        System.out.println(Arrays.toString(dataValues));
    }



//    i=1
//   p
//{8,5,9,2,4,3,1}
// j i
//     p
//{5,8,9,2,4,3,1}
//   j i
//   1 2
//
//i=2 npo changes
//
//i=3
//       p
//{5,8,9,2,4,3,1}
//     j i
// 0 1 2 3
//{5,8,9,9,4,3,1}
//   j   i
// 0 1 2 3
//{5,8,8,9,4,3,1}
// j     i
// 0 1 2 3
//{5,5,8,9,4,3,1}
// j     i
// 0 1 2 3
//{2,5,8,9,4,3,1}

    public static void insertionSorting(int[] dataValues){//{3,8,9,2,7,1}
        int valueCount = dataValues.length;
        for(int i=1; i<valueCount;i++){
            // pick a card
            int eleValue = dataValues[i];
            int j =i-1;
            while (j>=0 && dataValues[j]>eleValue){
                dataValues[j+1]=dataValues[j];
                j--;
            }
            dataValues[j+1] = eleValue;
        }
        System.out.println(Arrays.toString(dataValues));
    }

    ////*************************************************************************************************************
    public static int maxSubArray(int[] nums) {
        // Initialize current sum (cs) and maximum sum (ms) with the first element // Also DP approach
        int cs = nums[0];
        int ms = nums[0];

        // Iterate from the second element
        for (int i = 1; i < nums.length; i++) {
            // Decide whether to extend the current subarray or start a new one
            cs = Math.max(nums[i], cs + nums[i]);
            // Update the maximum sum found so far
            ms = Math.max(ms, cs);
        }
        System.out.println(ms);
        return ms;
    }

////****************************************************************************************************************************************************************
    static void   buyandSell_II_Greedy(int[] prices) {
        //  *****If stocks buy sell multiple time in same day******
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
    }

    static void buyandSell_I_Greedy(int[] prices) {
//  *****If stocks buy sell one time in same day*****
    int miniprice= prices[0];
    int maxprofit= 0;
    for(int i=1;i<prices.length;i++){
        if(prices[i]<miniprice){
            miniprice = prices[i];
        }else {
            int currentprofit = prices[i]-miniprice;
            maxprofit = Math.max(currentprofit,maxprofit);
        }
    }
    System.out.println(maxprofit);
    }

    static void MajorityElement(int[] majoArr){
        int candidate = majoArr[0];
        int count =0;
        for(int i=0; i<majoArr.length;i++){
            if(candidate==majoArr[i]){
                count++;
            }else{
                count--;
            }
            if(count==0){
                candidate=majoArr[i];
                count++;
            }
        }

    }

    ////*************************************************************************************************************
    public static int minCapability(int[] nums, int k) {
//        Robbery Problem IV
        // find min and max value
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        // try every capability from small to big
        for (int cap = min; cap <= max; cap++) {

            if (canRob(nums, k, cap)) {
                return cap;   // first working capability
            }
        }
        return max;
    }

    private static boolean canRob(int[] nums, int k, int cap) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
             if (nums[i] <= cap) {
                count++;
                i++; // skip adjacent house
            }
            if (count >= k) return true;
        }
        return false;
    }

//############################1,3,5,4,2#########################################################################################################################
    public static void nextPermutation(int[] ar){
         int length = ar.length;
         int i = length-2;
//         Finding index of Pivot-->3 that current value is lesser the next value
         while(i>=0 && ar[i]>ar[i+1]){
             i--;
         }
//         finding index of the least smallest number-->2 after Pivot that available
         if(i>=0){

             int right = length-1;
             while(ar[right]<=ar[i]){
                 right--;
             }
//          Swapping the pivot with Lease smallest number (to look like 1,2,5,4,3)
             int temp = ar[right];
             ar[right] = ar[i];
             ar[i] = temp;
//           Reverse the the values that is after Pivot
             int rightRev = length-1;
             int leftRev = i +1;
             while(leftRev<rightRev){
                 int tempRe = ar[rightRev];
                 ar[rightRev] = ar[leftRev];
                 ar[leftRev] = tempRe;
                 rightRev--;
                 leftRev++;
             }
//             Log the result
             System.out.println(Arrays.toString(ar));

      }
             }

    public static void setZeroes(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            boolean firstRowZero = false;
            boolean firstColZero = false;

            // Check first row
            for(int j = 0; j < cols; j++){
                if(matrix[0][j] == 0){
                    firstRowZero = true;
                    break;
                }
            }

            // Check first column
            for(int i = 0; i < rows; i++){
                if(matrix[i][0] == 0){
                    firstColZero = true;
                    break;
                }
            }

            // Mark rows and columns
            for(int i = 1; i < rows; i++){
                for(int j = 1; j < cols; j++){
                    if(matrix[i][j] == 0){
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
            // Set cells to zero using markers
            for(int i = 1; i < rows; i++){
                for(int j = 1; j < cols; j++){
                    if(matrix[i][0] == 0 || matrix[0][j] == 0){
                        matrix[i][j] = 0;
                    }
                }
            }
            // Handle first row
            if(firstRowZero){
                for(int j = 0; j < cols; j++){
                    matrix[0][j] = 0;
                }
            }

            // Handle first column
            if(firstColZero){
                for(int i = 0; i < rows; i++){
                    matrix[i][0] = 0;
                }
            }

            for(int[] q: matrix){
                System.out.println(Arrays.toString(q));
            }

    }


    }
