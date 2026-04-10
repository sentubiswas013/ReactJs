
    public static void main(String[] args) {
        System.out.println(div(10, 2));
    }

    static int div(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("b cannot be zero");
        }
        return a / b;
    }
