import java.util.*;

public class TwoPointer {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 3};
        int dataValues[] = {2, 3, 5, 9};

////        *****TwoPoint_Algo*****
        int[] arr_tp = {1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0};
        int[] arr_3tp = {2, 0, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 2, 0, 2, 1, 2, 0, 2, 2, 2};
        int[] arr_water1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] arr_twosumII = {2, 7, 11, 15};
//        int[] arr_Threesum = {1,2,0,1,0,0,0,0};
        int[] arr_Threesum = {-1,0,1,2,-1,-4};

        int[] arr_sortedArr = {0, -4, -2, 4, 7};
        System.out.println(palindrromString("A man, a plan, a canal: Panama"));
        squareOfsortedArray(arr_sortedArr);
        arranginginDescending(arr_tp);
        arranginginDescendingmorethen2number(arr_3tp);
        twosumII(arr_twosumII, 9);
        twosumIIhash(arr_twosumII, 9);
        threeSum(arr_Threesum, 0);
        maxWaterTankCapacity(arr_water1);
    }

    /// /*************************************************************************************************************
    ///
    /// @return
    static boolean palindrromString(String text1) {
        String text = text1.replaceAll("[^0-1a-zA-Z]", "");
        int l = 0;
        int r = text.length()-1;
        while (l < r) {
            if (Character.toLowerCase(text.charAt(l)) != Character.toLowerCase(text.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static void arranginginDescending(int[] nums) {
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
                i++;
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void arranginginDescendingmorethen2number(int[] nums) {
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        while (i <= k) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
                i++;
            } else if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
                k--;
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    static void twosumII(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int[] ar = new int[2];
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == target) {
                ar[0] = l + 1;
                ar[1] = r + 1;
                System.out.println(Arrays.toString(ar));
                return;
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
    }

    static int[] twosumIIhash(int[] arr, int target) {
        Map<Integer, Integer>  map  =  new HashMap<>();
        for(int i=0; i<arr.length; i++){
            int balance = target - arr[i];
            if(map.containsKey(balance)){
                return new int[]{map.get(balance), i};
            }
            map.put(arr[i],i);
        }
        return arr;
    }

    static void squareOfsortedArray(int[] sor_arr) {
        int l = 0;
        int r = sor_arr.length-1;
        int pos = sor_arr.length-1;
        int[] srOfSortArr = new int[r+1];
        while (l <= r) {
            int ls = sor_arr[l] * sor_arr[l];
            int rs = sor_arr[r] * sor_arr[r];
            if (ls > rs) {
                srOfSortArr[pos] = ls;
                l++;
            } else {
                srOfSortArr[pos] = rs;
                r--;
            }
            pos--;
        }

    }

    static void maxWaterTankCapacity(int[] arr) {
        int maxCapacity = 0;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int currentCapacity = Math.max(arr[l], arr[r]) * (r - l);
            maxCapacity = Math.max(currentCapacity, maxCapacity);
            if (arr[l] < arr[r]) {
                l++;
            } else {
                r--;
            }
        }
        System.out.println(maxCapacity);
    }

//  [-1,0,1,2,-1,-4]
//  [-4,-1,-1,0,1,2]
//    i  j        k
//    i     j     k
//    i       j   k
//    i         j k
//  [-4,-1,-1,0,1,2]
//       i  j     k
//       i    j   k
//       i      j k

    static List<List<Integer>> threeSum(int[] nums,int target){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
//     -2 is because you need at least three numbers to form a triplet. This avoids unnecessary iterations.
        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int sum = nums[i] + nums[j] + nums[k];

                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {

                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    j++;
                    k--;

                    // Skip duplicate second elements
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }

                    // Skip duplicate third elements
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }
        return result;
    }
}
