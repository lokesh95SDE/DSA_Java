public class recursion_problems {



public static void main (String[] args){
    System.out.println (recusrionMultiplyOf2numbersWithoutXOperator(5,4));
//// DP
    System.out.println (recursion_fibo(5));
    System.out.println (StaircaseClimbing(6));
}

static int recusrionMultiplyOf2numbersWithoutXOperator(int n, int r){
    if(n==1){
            return r;
        }
        int recu = recusrionMultiplyOf2numbersWithoutXOperator(n-1,r);
        return recu+r;
}

    //// 0,1,1,2,3,5,8,13,21 = 5
    /// (n-1)+(n-2)
    /// 4+3=7
    ///
static int recursion_fibo(int n) {
    if(n==0 || n==1){
        return n;
    }
    int fib1 = recursion_fibo(n-1);
    int fib2 = recursion_fibo(n-2);
    int recur = fib1+fib2;
    return recur;
}

static int StaircaseClimbing(int n) {
//// i can use 1, 2, 3 steps to reach
    if(n==1){
        return 1;
    } else if (n==2) {
        return 2;
    }else if (n==3) {
        return 4;
    }
    int st1 = StaircaseClimbing(n-1);
    int st2 = StaircaseClimbing(n-2);
    int st3 = StaircaseClimbing(n-3);
    int recur = st1+st2+st3;
    return recur;
}































}
