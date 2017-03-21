package hp11c;

/**
 * Created by Martin on 07.03.2017.
 */
public class Hp11cEngine implements CalculatorEngine {

    private ArrayStack<Integer> stack;
    private String title;
    private String author;
    private String version;
    private int displayValue;



    public Hp11cEngine(String title, String author, String version, int displayValue) {
        this.title = title;
        this.author = author;
        this.version = version;
        this.displayValue = displayValue;
        stack = new ArrayStack<>();
    }

    @Override
    public void numberPressed(int number) {
        displayValue = number;
    }

    @Override
    public void pointPressed() {
        
    }

    @Override
    public void chsPressed() {
        displayValue = displayValue - (displayValue * 2);
    }

    @Override
    public void clearPressed() {
        displayValue = 0;

    }

    @Override
    public void plusPressed() {
        stack.push(displayValue);

        int temp1 = stack.pop();
        int temp2 = stack.pop();

        int operationResult = temp1 + temp2;
        displayValue = operationResult;
        stack.push(operationResult);
    }

    @Override
    public void minusPressed() {
        stack.push(displayValue);

        int temp1 = stack.pop();
        int temp2 = stack.pop();

        int operationResult = temp1 - temp2;
        displayValue = operationResult;
        stack.push(operationResult);
    }

    @Override
    public void multiplyPressed() {
        stack.push(displayValue);

        int temp1 = stack.pop();
        int temp2 = stack.pop();

        int operationResult = temp1 * temp2;
        displayValue = operationResult;
        stack.push(operationResult);
    }

    @Override
    public void dividePressed() {
        stack.push(displayValue);

        int temp1 = stack.pop();
        int temp2 = stack.pop();

        int operationResult = temp1 / temp2;
        displayValue = operationResult;
        stack.push(operationResult);
    }

    @Override
    public void enterPressed() {
        stack.push(displayValue);
        clearPressed();
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getDisplayValue() {
        return Integer.toString(displayValue);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getVersion() {
        return version;
    }
}
