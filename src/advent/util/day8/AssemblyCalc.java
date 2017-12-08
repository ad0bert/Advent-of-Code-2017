package advent.util.day8;

import java.util.Map;

public class AssemblyCalc {

    private String varName;
    private Operator operator;
    private Integer value;
    private KeyWord keyWord;
    private String leftHand;
    private Operation operation;
    private Integer rightHand;

    public AssemblyCalc(String input) {
        String[] values = input.split("\\s");
        this.setVarName(values[0]);
        this.setOperator(Operator.getEnum(values[1]));
        this.setValue(Integer.parseInt(values[2]));
        this.setKeyWord(KeyWord.getEnum(values[3]));
        this.setLeftHand(values[4]);
        this.setOperation(Operation.getEnum(values[5]));
        this.setRightHand(Integer.parseInt(values[6]));
    }

    private boolean checkCondition(Integer value) {
        switch (this.operation) {
        case EQU:
            return value.equals(this.rightHand);
        case NEQ:
            return !value.equals(this.rightHand);
        case LSS:
            return value < this.rightHand;
        case LEQ:
            return value <= this.rightHand;
        case GRT:
            return value > this.rightHand;
        case GEQ:
            return value >= this.rightHand;
        default:
            return false;
        }
    }

    public void executeCalculation(Map<String, Integer> stack) {
        Integer leftValue = stack.get(this.leftHand);
        if (!checkCondition(leftValue)) {
            return;
        }

        switch (this.operator) {
        case INC:
            stack.put(this.varName, stack.get(this.varName) + this.value);
            break;
        case DEC:
            stack.put(this.varName, stack.get(this.varName) - this.value);
            break;
        }

    }

    public String getVarName() {
        return this.varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public Operation getOperation() {
        return this.operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public KeyWord getKeyWord() {
        return this.keyWord;
    }

    public void setKeyWord(KeyWord keyWord) {
        this.keyWord = keyWord;
    }

    public String getLeftHand() {
        return this.leftHand;
    }

    public void setLeftHand(String leftHand) {
        this.leftHand = leftHand;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Integer getRightHand() {
        return this.rightHand;
    }

    public void setRightHand(Integer rightHand) {
        this.rightHand = rightHand;
    }
}
