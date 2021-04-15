// Helper method to print out the integer array.
private static void printArray(int[] array) {

        for(int i: array) {
        System.out.print(i + " ");
        }
        System.out.println();

        }module de.dhbw_mannheim.student {
    requires javafx.controls;
    exports de.dhbw_mannheim.student;
}