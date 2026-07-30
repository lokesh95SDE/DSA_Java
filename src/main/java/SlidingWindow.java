import java.util.*;

public class SlidingWindow {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 3};
////      ****Sliding_Window_Algo****
//        AverageOfmaxSumFixedArray_FixedSlidingWindow(arr,3);
        AverageOfmaxSumFixedArray_FixedSlidingWindowII(arr,3);
//        miniLengthArrayTargetedSum_VariableSlidingWindow(arr);
        findAnagrams("abcbcabcbb","abc");
    }

    ////*************************************************************************************************************
    static double AverageOfmaxSumFixedArray_FixedSlidingWindow(int[] nums, int k ) {
                int maxSum = 0;
                int curMax = 0;

                for(int i =0; i<k; i++){
                    curMax += nums[i];
                }
                maxSum = curMax;
                for(int j=k; j<nums.length; j++){
                    curMax = curMax + nums[j] - nums[j-k];
                    if(curMax>maxSum){
                        maxSum = curMax;
                    }
                }
                return (double)maxSum/k;

    }

    static double AverageOfmaxSumFixedArray_FixedSlidingWindowII(int[] nums, int k ) {
        int left = 0;
        int right = 0;
        int n = nums.length;
        int maxSum = 0;
        int sum = 0;
        while(right<n){
            sum += nums[right];
            if(right-left+1 == k){
                maxSum = Math.max(maxSum,sum);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        System.out.println((double)maxSum/k);
    return (double)maxSum/k;
    }


    static void miniLengthArrayTargetedSum_VariableSlidingWindow(int[] arr) {
        int target = 5;
        int k = 0;   ///no fixed length so every iteration assume as fixed length
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            windowSum += arr[i];
            while (windowSum >= target) {
                int actMinLength = i - k + 1;
                minLength = Math.min(minLength, actMinLength);
                windowSum -= arr[k];
                k++;
            }
        }

        System.out.println(minLength);

    }

        public static List<Integer> findAnagrams(String s, String p) {
            int left = 0;
            int right = 0;
            int n = s.length();
            List<Integer> result =  new ArrayList<>();
            Map<Character, Integer> map = new HashMap<>();
            for(Character c : p.toCharArray()){
                map.put(c,map.getOrDefault(c,0)+1);
            }
            int counter = map.size();
            while(right<n){
                char ch = s.charAt(right);
                if(map.containsKey(ch)) {
                    map.put(ch,map.get(ch)-1);
                    if(map.get(ch)==0){
                        counter--;
                    }
                }
                right++;

                while(counter==0){
                    char tch = s.charAt(left);
                    if(right-left == p.length()){
                        result.add(left);
                    }
                    if(map.containsKey(tch)){
                        map.put(tch,map.get(tch)+1);
                        if(map.get(tch)>0){
                            counter++;
                        }
                    }
                    left++;
                }
            }
            return result;
        }





}
