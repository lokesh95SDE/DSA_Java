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
            int n = s.length();  List<Integer> result =  new ArrayList<>();
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


    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int n  = s.length();
        int counter = 0;
        int d =0;
        Map<Character, Integer> map = new HashMap<>();
        while(right<n){
            char c = s.charAt(right);                       //a     b   c   a       b    c   b
            map.put(c,map.getOrDefault(c,0)+1);  //a=1   b=1 c=1 a=2     b=2  c=2 b=2
            if(map.get(c)>1){                               //f     f   f   tr      tr   tr  tr
                counter++;                                  //              count=1 1    1    1
            }

            right++;                                        //r=1   r=2 r=3 r=4     r=5   r=6 r=7
            while(counter>0){                               //f     f   f   tr      tr    tr  tr    tr
                char ch = s.charAt(left);                   //              a       b     c    a   b
                if(map.get(ch)>1){                          //              tr      tr    tr  tr    tr
                    counter--;    }                         //             count=0   0    0   1same 0
                map.put(ch, map.get(ch)-1);                 //              a=1     b=1   c=1  a=0  b=1
                left++     ;                                //      l=0  0   1     l=2   l=3  l=4  l=5
            }
            d = Math.max(d,right-left);                     //1     2     3   3    3     3    3      2
        }
        return d;

    }

    //ninninja
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int start = 0, end = 0, counter = 0, len = 0;               //
        while(end < s.length()){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) == 1) counter++;//new char
            end++;
            while(counter > 2){
                char cTemp = s.charAt(start);
                map.put(cTemp, map.get(cTemp) - 1);
                if(map.get(cTemp) == 0){
                    counter--;
                }
                start++;
            }
            len = Math.max(len, end-start);
        }
        return len;
    }

    }

//lengthOfLongestSubstringTwoDistinct
//| Iter | `end` | `c` | Map (after `put`)      | `map.get(c)==1` | `counter` | `end++` | Inner `while(counter>2)` Steps                                                                                                                                                      | `start` | `len` |
//        | :--: | :---: | :-: | :--------------------- | :-------------: | :-------: | :-----: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-----: | :---: |
//        |   1  |   0   | `n` | `{n:1}`                |   Yes → **1**   |     1     |    1    | —                                                                                                                                                                                   |    0    | **1** |
//        |   2  |   1   | `i` | `{n:1, i:1}`           |   Yes → **2**   |     2     |    2    | —                                                                                                                                                                                   |    0    | **2** |
//        |   3  |   2   | `n` | `{n:2, i:1}`           |        No       |     2     |    3    | —                                                                                                                                                                                   |    0    | **3** |
//        |   4  |   3   | `n` | `{n:3, i:1}`           |        No       |     2     |    4    | —                                                                                                                                                                                   |    0    | **4** |
//        |   5  |   4   | `i` | `{n:3, i:2}`           |        No       |     2     |    5    | —                                                                                                                                                                                   |    0    | **5** |
//        |   6  |   5   | `n` | `{n:4, i:2}`           |        No       |     2     |    6    | —                                                                                                                                                                                   |    0    | **6** |
//        |   7  |   6   | `j` | `{n:4, i:2, j:1}`      |   Yes → **3**   |     3     |    7    | ① `start=0`: `n` 4→3 (counter 3)<br>② `start=1`: `i` 2→1 (counter 3)<br>③ `start=2`: `n` 3→2 (counter 3)<br>④ `start=3`: `n` 2→1 (counter 3)<br>⑤ `start=4`: `i` 1→0, **counter=2** |    5    | **6** |
//        |   8  |   7   | `a` | `{n:1, i:0, j:1, a:1}` |   Yes → **3**   |     3     |    8    | ① `start=5`: `n` 1→0, **counter=2**                                                                                                                                                 |    6    | **6** |


//longest-repeating-character-replacement
//    | Iter | `right` | `current` | Map (after `put`) | `maxFreq` | Init `windowSize` | `windowSize - maxFreq > 1` ? | Inner `while` steps                               | `left` | `maxLength` |
//        | :--: | :-----: | :-------: | :---------------- | :-------: | :---------------: | :--------------------------: | :------------------------------------------------ | :----: | :---------: |
//        |   1  |    0    |    `A`    | `{A:1}`           |   **1**   |         1         |        `0 > 1` → **F**       | —                                                 |    0   |    **1**    |
//        |   2  |    1    |    `A`    | `{A:2}`           |   **2**   |         2         |        `0 > 1` → **F**       | —                                                 |    0   |    **2**    |
//        |   3  |    2    |    `B`    | `{A:2, B:1}`      |   **2**   |         3         |        `1 > 1` → **F**       | —                                                 |    0   |    **3**    |
//        |   4  |    3    |    `A`    | `{A:3, B:1}`      |   **3**   |         4         |        `1 > 1` → **F**       | —                                                 |    0   |    **4**    |
//        |   5  |    4    |    `B`    | `{A:3, B:2}`      |   **3**   |         5         |        `2 > 1` → **T**       | `leftChar='A'`: `{A:2}`, `left=1`, `windowSize=4` |    1   |    **4**    |
//        |   6  |    5    |    `B`    | `{A:2, B:3}`      |   **3**   |         5         |        `2 > 1` → **T**       | `leftChar='A'`: `{A:1}`, `left=2`, `windowSize=4` |    2   |    **4**    |
//        |   7  |    6    |    `A`    | `{A:2, B:3}`      |   **3**   |         5         |        `2 > 1` → **T**       | `leftChar='B'`: `{B:2}`, `left=3`, `windowSize=4` |    3   |    **4**    |
