package TaskSets.TaskSet2;

public class Task2_4 {
    public static void main(final String[] args) {
        double value = Math.PI;
        int noOfDecimals = 3;
        double scale = 0;
        double newValue = 0;
        scale = Math.pow (10, noOfDecimals);
        newValue = Math.round (value * scale) / scale;

        System.out.println();
    }
}
