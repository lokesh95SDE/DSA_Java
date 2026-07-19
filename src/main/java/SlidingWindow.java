public class SlidingWindow {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 3};
////      ****Sliding_Window_Algo****
        maxSumFixedArray_FixedSlidingWindow(arr);
        miniLengthArrayTargetedSum_VariableSlidingWindow(arr);
    }

    ////*************************************************************************************************************
    static void maxSumFixedArray_FixedSlidingWindow(int[] arr) {
        int maxSum = 0;
        int windowSum = 0;
        int k = 3;
//        int maxindex =0;
//        int[] fixedArry = new int[k];
        for (int i = 0; i < arr.length; i++) {
            windowSum += arr[i];  // starting-- summ is added sum from right
            if (i >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);  // in between if maximum of all it is sotred
                windowSum -= arr[(i - k + 1)]; //ending -- summ is subtracted from left
            }
//            maxindex = i;
        }
//        fixedArry[0]=arr[maxindex-2];
//        fixedArry[1]=arr[maxindex-1];
//        fixedArry[2]=arr[maxindex];
//
//        System.out.println(Arrays.toString(fixedArry));
        System.out.println(windowSum);
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
