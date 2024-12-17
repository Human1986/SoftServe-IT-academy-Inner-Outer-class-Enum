package jom.com.softserve.s3.task3;

public class Operation {
	public static double subtractBfromA(int a, int b) {
		Strategy strategy = new Strategy() {
			@Override
			public double doOperation(int a, int b) {
				return a - b;
			}
		};
		System.out.println(strategy.doOperation(a, b));
		return strategy.doOperation(a, b);
	}

	public static double addAtoB(int a, int b) {
		Strategy strategy = new Strategy() {
			@Override
			public double doOperation(int a, int b) {
				return a + b;
			}
		};
		System.out.println(strategy.doOperation(a, b));
		return strategy.doOperation(a, b);
	}
	public static double divideAbyB(int a, int b) {
		Strategy strategy = new Strategy() {
			@Override
			public double doOperation(int a, int b) {
				return a / b;
			}
		};
		System.out.println(strategy.doOperation(a, b));
		return strategy.doOperation(a, b);
	}


	public static double multiplyAbyB(int a, int b) {
		Strategy strategy = new Strategy() {
			@Override
			public double doOperation(int a, int b) {
				return a * b;
			}
		};
		System.out.println(strategy.doOperation(a, b));
		return strategy.doOperation(a, b);
	}
}
