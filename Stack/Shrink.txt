public class Main {

    int stack[] = new int[5];
    int top = -1;

    void resize() {
        int newStack[] = new int[stack.length * 2];
        for (int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    void shrink() {
        int newStack[] = new int[stack.length / 2];
        for (int i = 0; i <= top; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    void push(int x) {
        if (top == stack.length - 1) {
            resize();
        }
        stack[++top] = x;
    }

    int pop() {
        int x = stack[top--];
        if (top < stack.length / 2 && stack.length > 5) {
            shrink();
        }
        return x;
    }

    void display() {
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Main s = new Main();

        s.push(10);
        s.push(20);
        s.push(30);
        s.push(40);
        s.push(50);
        s.push(60);

        s.display();

        s.pop();
        s.pop();
        s.pop();

        s.display();
    }
}