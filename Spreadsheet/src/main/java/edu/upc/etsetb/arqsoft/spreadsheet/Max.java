package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Map;

/**
 *
 * @author Daniel León
 */
public class Max extends Function {

    public Max(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) throws ExpressionException {
        
        Calculator calculator = new Calculator(cellMap, "");
        calculator.setIsAFunctionRange(true);
        for (int i = 0; i < factors.length; i++) {
            if (!this.factors[i].equals("")) {
                calculator.setContent(this.factors[i]);
                this.factors[i] = calculator.calculate();
                this.splittedFactor = this.factors[i].split(";");
                for (String splittedFactorItem : this.splittedFactor) {
                    if (i == 0) {
                        this.result = splittedFactorItem;
                    }
                    if (Double.parseDouble(this.result) < Double.parseDouble(splittedFactorItem)) {
                        this.result = splittedFactorItem;
                    }
                }
            }
        }
        return this.result;
    }
}
