package LabSession2.ex3;

class JoinTestThread extends Thread{
    Thread t;
    static int sum = 0;
    JoinTestThread(String n, Thread t){
        this.setName(n);
        this.t=t;
    }
    public void run() {

        System.out.println("Thread "+this.getName()+" has entered the run() method");

        try {
            if (t != null) t.join();
            int numberToCheck = this.getName().equals("Thread 1") ? 50001 : 20001;
            int divisorsSum = calculateDivisorsSum(numberToCheck);

            sum += divisorsSum;
            System.out.println("Thread "+this.getName()+" executing operation.");
            System.out.println(String.format("Thread %s result: %d", this.getName(), sum));
            Thread.sleep(3000); System.out.println("Thread "+this.getName()+" has terminated operation.");
        } catch(Exception e){
            e.printStackTrace();}


    }
    private int calculateDivisorsSum(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
