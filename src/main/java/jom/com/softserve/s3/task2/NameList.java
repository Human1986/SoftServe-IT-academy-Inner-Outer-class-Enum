package jom.com.softserve.s3.task2;

class NameList {
	private final String[] names = {"Mike", "Emily", "Nick", "Patric", "Sara"};

	public Iterator getIterator() {
		return new Iterator();
	}

	public class Iterator implements java.util.Iterator<String> {
		private int counter = 0;
		private String[] list;

		private Iterator() {
			list = names;
		}


		@Override
		public boolean hasNext() {
			return counter < list.length;
		}

		@Override
		public String next() {
			int i = counter;
			counter = i + 1;
			return list[i];
		}
	}
}