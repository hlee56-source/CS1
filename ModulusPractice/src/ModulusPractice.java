public class ModulusPractice {
    public static final int daysInYear = 365;
    public static final int daysInMonth = 30;
    public static final int daysInWeek = 7;

    public static void main(String[] args) {
        findNumberOfDays(1000);
        fizzBuzz();
    }
    public static void fizzBuzz() {
        for (int i = 1; i <= 100000; i ++){
            if (i % 3 == 0) {
                IO.print("Fizz");
            }
            if (i % 5 == 0) {
                IO.print("Buzz");
            }
            if (i % 8 == 0) {
                IO.print("Fuzz");
            }
            if (i % 9 == 0) {
                IO.print("Biff");
            }
            if (i % 12 == 0) {
                IO.print("Fazz");
            }

            if (i % 3 != 0 && i % 5 != 0 && i % 8 != 0) { // if it's not 3, it won't be 9 or 12
                IO.print(i);
            }
            IO.print("\n");
        }
    }
    public static void findNumberOfDays(int input) {
        int years = input / daysInYear;
        int daysLeft = input % daysInYear;
        int months = daysLeft / daysInMonth;
        daysLeft = daysLeft % daysInMonth;
        int weeks = daysLeft / daysInWeek;
        daysLeft = daysLeft % daysInWeek;
        IO.println("There are " + years + " years, " + months + " months, " + weeks + " weeks and " + daysLeft + " days");
    }

}

