import java.util.Random;
import java.util.Scanner;

public class MathPuzzles {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            System.out.println("Welcome to the Math Puzzles game!");

            while (true) {
                System.out.print("\nPlease enter your age (5-18) or type 'exit' to quit: ");
                String ageInput = scanner.nextLine();

                if (ageInput.equalsIgnoreCase("exit")) {
                    System.out.println("Thanks for playing! Goodbye.");
                    break;
                }

                try {
                    int age = Integer.parseInt(ageInput);

                    if (age < 5 || age > 18) {
                        System.out.println("Age must be between 5 and 18. Please try again.");
                        continue;
                    }

                    generateAndSolvePuzzle(age, scanner, random);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number for your age or 'exit'.");
                }
            }
        }
    }

    /**
     * Generates a math puzzle based on the user's age and checks the answer.
     *
     * @param age The age of the user, used to determine difficulty.
     * @param scanner The Scanner object for user input.
     * @param random The Random object for generating numbers.
     */
    private static void generateAndSolvePuzzle(int age, Scanner scanner, Random random) {
        String question = "";
        int correctAnswer = 0;

        // Determine difficulty based on age
        if (age >= 5 && age <= 7) {
            // Simple addition/subtraction within 10-20
            int num1 = random.nextInt(10) + 1; // 1-10
            int num2 = random.nextInt(10) + 1; // 1-10
            if (random.nextBoolean()) { // Randomly choose addition or subtraction
                question = "What is " + num1 + " + " + num2 + "?";
                correctAnswer = num1 + num2;
            } else {
                // Ensure result is not negative for subtraction
                if (num1 < num2) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                question = "What is " + num1 + " - " + num2 + "?";
                correctAnswer = num1 - num2;
            }
        } else if (age >= 8 && age <= 10) {
            // Addition/subtraction within 100, simple multiplication/division
            int type = random.nextInt(4); // 0:add, 1:sub, 2:mul, 3:div
            int num1, num2;

            switch (type) {
                case 0 -> { // Addition
                    num1 = random.nextInt(90) + 10; // 10-99
                    num2 = random.nextInt(90) + 10;
                    question = "What is " + num1 + " + " + num2 + "?";
                    correctAnswer = num1 + num2;
                }
                case 1 -> { // Subtraction
                    num1 = random.nextInt(90) + 10;
                    num2 = random.nextInt(num1 - 5) + 5; // Ensure num1 > num2 and result is at least 5
                    question = "What is " + num1 + " - " + num2 + "?";
                    correctAnswer = num1 - num2;
                }
                case 2 -> { // Multiplication (single digit)
                    num1 = random.nextInt(9) + 2; // 2-10
                    num2 = random.nextInt(9) + 2; // 2-10
                    question = "What is " + num1 + " * " + num2 + "?";
                    correctAnswer = num1 * num2;
                }
                case 3 -> { // Division (exact)
                    num2 = random.nextInt(9) + 2; // Divisor 2-10
                    correctAnswer = random.nextInt(9) + 2; // Quotient 2-10
                    num1 = num2 * correctAnswer; // Dividend
                    question = "What is " + num1 + " / " + num2 + "?";
                }
            }
        } else if (age >= 11 && age <= 13) {
            // Two-step operations, multiplication/division with larger numbers, basic fractions/decimals
            int type = random.nextInt(3); // 0:two-step, 1:larger mul, 2:larger div
            int num1, num2, num3;

            switch (type) {
                case 0 -> { // Two-step operation (e.g., (A + B) * C)
                    num1 = random.nextInt(20) + 5;
                    num2 = random.nextInt(20) + 5;
                    num3 = random.nextInt(5) + 2;
                    if (random.nextBoolean()) {
                        question = "What is (" + num1 + " + " + num2 + ") * " + num3 + "?";
                        correctAnswer = (num1 + num2) * num3;
                    } else {
                        question = "What is (" + num1 + " - " + num2 + ") + " + num3 + "?";
                        correctAnswer = (num1 - num2) + num3;
                    }
                }
                case 1 -> { // Larger multiplication
                    num1 = random.nextInt(50) + 10;
                    num2 = random.nextInt(10) + 2;
                    question = "What is " + num1 + " * " + num2 + "?";
                    correctAnswer = num1 * num2;
                }
                case 2 -> { // Larger division (exact)
                    num2 = random.nextInt(15) + 5; // Divisor 5-19
                    correctAnswer = random.nextInt(15) + 5; // Quotient 5-19
                    num1 = num2 * correctAnswer; // Dividend
                    question = "What is " + num1 + " / " + num2 + "?";
                }
            }
        } else if (age >= 14 && age <= 16) {
            // Order of operations, basic algebra (solving for x), percentages
            int type = random.nextInt(3); // 0:order of ops, 1:solve x, 2:percentage

            switch (type) {
                case 0 -> { // Order of operations (e.g., A + B * C)
                    int a = random.nextInt(15) + 5;
                    int b = random.nextInt(10) + 2;
                    int c = random.nextInt(5) + 2;
                    question = "What is " + a + " + " + b + " * " + c + "?";
                    correctAnswer = a + b * c;
                }
                case 1 -> { // Solve for x (e.g., A + x = B)
                    int val1 = random.nextInt(30) + 10;
                    int val2 = random.nextInt(30) + 10;
                    if (random.nextBoolean()) { // A + x = B
                        question = "If " + val1 + " + x = " + (val1 + val2) + ", what is x?";
                        correctAnswer = val2;
                    } else { // x - A = B
                        question = "If x - " + val1 + " = " + val2 + ", what is x?";
                        correctAnswer = val1 + val2;
                    }
                }
                case 2 -> { // Percentage
                    int base = (random.nextInt(10) + 1) * 10; // Multiples of 10 for easier percentages
                    int percent = random.nextInt(5) * 5 + 10; // 10, 15, 20, 25, 30
                    question = "What is " + percent + "% of " + base + "?";
                    correctAnswer = (base * percent) / 100;
                }
            }
        } else if (age >= 17 && age <= 18) {
            // More complex algebra, basic geometry formulas, word problems
            int type = random.nextInt(3); // 0:complex algebra, 1:geometry, 2:word problem

            switch (type) {
                case 0 -> { // Complex algebra (e.g., 2x + 5 = 15)
                    int coeff = random.nextInt(3) + 2; // Coefficient 2-4
                    int constant = random.nextInt(10) + 1; // Constant 1-10
                    correctAnswer = random.nextInt(10) + 1; // Solution 1-10
                    int result = coeff * correctAnswer + constant;
                    question = "If " + coeff + "x + " + constant + " = " + result + ", what is x?";
                }
                case 1 -> { // Basic geometry (Area of rectangle/triangle)
                    int length = random.nextInt(10) + 5;
                    int width = random.nextInt(10) + 5;
                    if (random.nextBoolean()) { // Area of rectangle
                        question = "A rectangle has a length of " + length + " units and a width of " + width + " units. What is its area?";
                        correctAnswer = length * width;
                    } else { // Area of triangle (base * height / 2)
                        int base = random.nextInt(10) + 5;
                        int height = (random.nextInt(5) + 2) * 2; // Ensure height is even for integer result
                        question = "A triangle has a base of " + base + " units and a height of " + height + " units. What is its area?";
                        correctAnswer = (base * height) / 2;
                    }
                }
                case 2 -> { // Simple word problem
                    int item1 = random.nextInt(10) + 5;
                    int item2 = random.nextInt(10) + 5;
                    int price1 = random.nextInt(5) + 1;
                    int price2 = random.nextInt(5) + 1;
                    question = "John bought " + item1 + " apples at $" + price1 + " each and " + item2 + " oranges at $" + price2 + " each. How much did he spend in total?";
                    correctAnswer = (item1 * price1) + (item2 * price2);
                }
            }
        }

        System.out.println(question);
        System.out.print("Your answer: ");
        try {
            int userAnswer = Integer.parseInt(scanner.nextLine());
            if (userAnswer == correctAnswer) {
                System.out.println("Correct! Well done!");
            } else {
                System.out.println("Incorrect. The correct answer was " + correctAnswer + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}