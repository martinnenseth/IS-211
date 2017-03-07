package hp11c;


import java.util.ArrayList;

/**
 * Created by Sindre on 07.03.2017.
 */
public class Hp11cEngine implements CalculatorEngine {
    public static int MAX = 10;
    private float[] stack;
    private int top;


    public Hp11cEngine() {
        this.stack = new float[MAX];
        this.top = 0;


    }

    @Override
    public void numberPressed(int number) {
        this.push(number);
    }

    @Override
    public void pointPressed() {

    }

    @Override
    public void chsPressed() {

    }

    @Override
    public void clearPressed() {
        this.stack = new float[MAX];
        this.top = 0;

    }

    @Override
    public void plusPressed() {
        float n = pop();
        float n1 = pop();
        push((n1+n));
    }

    @Override
    public void minusPressed() {
        float n = pop();
        float n1 = pop();
        push((n1-n));


    }

    @Override
    public void multiplyPressed() {
        float n = pop();
        float n1 = pop();
        push((n1*n));
    }

    @Override
    public void dividePressed() {
        float n = pop();
        float n1 = pop();
        push((n1/n));
    }

    @Override
    public void enterPressed() {

    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public String getDisplayValue() {
        String display = ""+ getTop();
        return  display.toString();
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }

    public void push(float e){
        if (top >= 10) throw new StackOverflowError();
        this.stack[top] = e;
        top ++;
    }

    public float pop(){
        if (top <= 0) throw new StackOverflowError("Stack underflow");
        top --;
        return this.stack[top];
    }

    public float getTop(){
        return this.stack[top-1];
    }

    public void debug(){
        System.out.println(stack);
    }
}
