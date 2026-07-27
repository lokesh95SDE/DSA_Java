public class SlidingWindow {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 3};
////      ****Sliding_Window_Algo****
        AverageOfmaxSumFixedArray_FixedSlidingWindow(arr,3);
        miniLengthArrayTargetedSum_VariableSlidingWindow(arr);
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


}
