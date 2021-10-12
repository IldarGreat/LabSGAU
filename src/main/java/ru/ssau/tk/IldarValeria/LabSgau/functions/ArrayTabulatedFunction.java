package ru.ssau.tk.IldarValeria.LabSgau.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;
    private final int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double step = (xTo - xFrom) / (count - 1);
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        xValues[0] = xFrom;
        yValues[0] = source.apply(xFrom);
        for (int element = 1; element < count; element++) {
            xValues[element] = xValues[element - 1] + step;
            yValues[element] = source.apply(xValues[element]);
        }
        this.count = count;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int element = 0; element < this.count; element++) {
            if (xValues[element] == x) {
                return element;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int element = 0; element < this.count; element++) {
            if (yValues[element] == y) {
                return element;
            }
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (indexOfX(x) != -1) {
            return indexOfX(x);
        }
        for (int element = 0; element < this.count; element++) {
            if (x < xValues[element] && element != 0) {
                return element - 1;
            }
            if (x < xValues[element]) {
                return 0;
            }
        }
        return this.count;
    }

    @Override
    public double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, 0);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, count - 2);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return yValues[0];
        }
        return super.interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

}