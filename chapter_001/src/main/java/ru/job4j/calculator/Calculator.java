package ru.job4j.calculator;

/**
 * Class Calculator
 * @author dshustrov
 * @version 1
 * @since 28.10.2018
 */
public class Calculator {
    /**
     * Calculation result.
     */
    private double result;

    /**
     * Method add.
     * @param first  number one.
     * @param second number two.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract.
     * @param first  number one.
     * @param second number two.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method multiply.
     * @param first  number one.
     * @param second number two.
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method div.
     * @param first  number one.
     * @param second number two.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method getResult.
     * @return returns result.
     */
    public double getResult() {
        return this.result;
    }
}


