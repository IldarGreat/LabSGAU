package ru.ssau.tk.IldarValeria.LabSgau.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArrayTabulatedFunctionTest {
    static final double DELTA = 0.0001;
    static final double STEP = (67.2 - 1.2) / 99.0;
    static double[] xValues = new double[]{3.4, 5.2, 6, 2.1};
    static double[] yValues = new double[]{-2.4, 1.2, 3, 5.1};
    static LnFunction lnObject = new LnFunction();
    static ArrayTabulatedFunction arrayTabulatedObject = new ArrayTabulatedFunction(xValues, yValues);
    static ArrayTabulatedFunction arrayTabulatedObjectTwo = new ArrayTabulatedFunction(lnObject, 1.2, 67.2, 100);

    @Test
    public static void testGetCount() {
        Assert.assertEquals(arrayTabulatedObject.getCount(), 4);
        Assert.assertEquals(arrayTabulatedObjectTwo.getCount(), 100);
    }

    @Test
    public static void testGetX() {
        Assert.assertEquals(arrayTabulatedObject.getX(0), 3.4);
        Assert.assertEquals(arrayTabulatedObject.getX(1), 5.2);
        Assert.assertEquals(arrayTabulatedObject.getX(2), 6.0);
        Assert.assertEquals(arrayTabulatedObject.getX(3), 2.1);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.getX(element), 1.2 + element * STEP, DELTA);
        }
    }

    @Test
    public static void testGetY() {
        Assert.assertEquals(arrayTabulatedObject.getY(0), -2.4);
        Assert.assertEquals(arrayTabulatedObject.getY(1), 1.2);
        Assert.assertEquals(arrayTabulatedObject.getY(2), 3.0);
        Assert.assertEquals(arrayTabulatedObject.getY(3), 5.1);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.getY(element), lnObject.apply(arrayTabulatedObjectTwo.getX(element)), DELTA);
        }
    }

    @Test
    public static void testLeftBound() {
        Assert.assertEquals(arrayTabulatedObject.leftBound(), 3.4, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.leftBound(), 1.2, DELTA);
    }

    @Test
    public static void testRightBound() {
        Assert.assertEquals(arrayTabulatedObject.rightBound(), 2.1, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.rightBound(), 67.2, DELTA);
    }

    @Test
    public static void testIndexOfX() {
        Assert.assertEquals(arrayTabulatedObject.indexOfX(3.4), 0);
        Assert.assertEquals(arrayTabulatedObject.indexOfX(5.2), 1);
        Assert.assertEquals(arrayTabulatedObject.indexOfX(6.0), 2);
        Assert.assertEquals(arrayTabulatedObjectTwo.indexOfX(1.1), -1);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.indexOfX(1.2 + element * STEP), element);
        }
    }

    @Test
    public static void testIndexOfY() {
        Assert.assertEquals(arrayTabulatedObject.indexOfY(-2.4), 0);
        Assert.assertEquals(arrayTabulatedObject.indexOfY(1.2), 1);
        Assert.assertEquals(arrayTabulatedObject.indexOfY(3.0), 2);
        Assert.assertEquals(arrayTabulatedObjectTwo.indexOfY(0.432), -1, DELTA);
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.indexOfY(lnObject.apply(arrayTabulatedObjectTwo.getX(element))), element);
        }
    }

    @Test
    public static void testFloorIndexOfX() {
        for (int element = 0; element < 100; element++) {
            Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(1.2 + element * STEP), element);
        }
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(1.1), 0);
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(4.6), 5);
        Assert.assertEquals(arrayTabulatedObjectTwo.floorIndexOfX(67.3), 100);
    }

    @Test
    public static void testExtrapolateLeft() {
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(1.1), 0.1160, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(0.9), -0.0165, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateLeft(Double.NEGATIVE_INFINITY), Double.NEGATIVE_INFINITY);
    }

    @Test
    public static void testExtrapolateRight() {
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(67.3), 4.2091, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(69), 4.2345, DELTA);
        Assert.assertEquals(arrayTabulatedObjectTwo.extrapolateRight(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY);
    }

    @Test
    public static void testSetY() {
        arrayTabulatedObject.setY(0, 3.7);
        arrayTabulatedObjectTwo.setY(0, 6.32);
        Assert.assertEquals(arrayTabulatedObject.getY(0), 3.7);
        Assert.assertEquals(arrayTabulatedObjectTwo.getY(0), 6.32);
    }
}