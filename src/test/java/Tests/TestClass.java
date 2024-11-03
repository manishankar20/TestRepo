package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestClass {

    // Simple Calculator Methods
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Division by zero");
        return (double) a / b;
    }

    // String Manipulation Methods
    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public int countVowels(String str) {
        return (int) str.chars().filter(ch -> "aeiouAEIOU".indexOf(ch) != -1).count();
    }

    // Array Operations Methods
    public int[] sortArray(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public int findMax(int[] arr) {
        return Arrays.stream(arr).max().orElseThrow(IllegalArgumentException::new);
    }

    // Test Cases
    @Test
    public void testCalculator() {
        Assert.assertEquals(add(1, 1), 2);
        Assert.assertEquals(subtract(5, 3), 2);
        Assert.assertEquals(multiply(3, 4), 12);
        Assert.assertEquals(divide(10, 2), 5.0);
        Assert.assertThrows(IllegalArgumentException.class, () -> divide(1, 0));
    }

    @Test
    public void testStringManipulator() {
        Assert.assertEquals(reverse("hello"), "olleh");
        Assert.assertEquals(countVowels("hello"), 2);
        Assert.assertEquals(countVowels("xyz"), 0);
    }

    @Test
    public void testArrayOperations() {
        Assert.assertEquals(sortArray(new int[]{3, 1, 2}), new int[]{1, 2, 3});
        Assert.assertEquals(findMax(new int[]{1, 2, 3, 4}), 4);
        Assert.assertThrows(IllegalArgumentException.class, () -> findMax(new int[]{}));
    }
}
