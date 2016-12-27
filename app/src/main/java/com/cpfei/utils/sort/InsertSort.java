package com.cpfei.utils.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class InsertSort {

	public static <T> LinkedList<T> insertSort(List<T> list, boolean isAsc) {
		LinkedList<T> listSort = new LinkedList<>();
		for (T e : list) {
			insertSort(e, listSort, isAsc);
		}

		return listSort;
	}

	public static <T> LinkedList<T> insertSort(List<T> list,
			Comparator<? super T> c) {
		LinkedList<T> listSort = new LinkedList<>();
		for (T e : list) {
			insertSort(e, listSort, c);
		}

		return listSort;
	}

	public static <T> LinkedList<T> insertSort(List<T> list, int[] compares,
			boolean bAscending) {
		LinkedList<T> listSort = new LinkedList<>();
		LinkedList<Integer> compareSort = new LinkedList<>();

		int count = list.size();
		for (int i = 0; i < count; i++) {
			T e = list.get(i);
			insertSort(e, compares[i], listSort, compareSort, bAscending);
		}

		return listSort;
	}

	public static <T> LinkedList<T> insertSort(List<T> list, float[] compares,
			boolean bAscending) {
		LinkedList<T> listSort = new LinkedList<>();
		LinkedList<Float> compareSort = new LinkedList<>();

		int count = list.size();
		for (int i = 0; i < count; i++) {
			T e = list.get(i);
			insertSort(e, compares[i], listSort, compareSort, bAscending);
		}

		return listSort;
	}

	@SuppressWarnings("unchecked")
	public static <T> void insertSort(T element, LinkedList<T> list, boolean isAsc) {
		if (element == null || list == null) {
			return;
		}

		if (list.isEmpty()) {
			list.add(element);
		} else if (isAsc) {
			if (((Comparable<T>) element).compareTo(list.getFirst()) < 0) {
				list.addFirst(element);
			} else {
				for (T e : list) {
					if (((Comparable<T>) element).compareTo(e) < 0) {
						int index = list.indexOf(e);
						list.add(index, element);
						return;
					}
				}

				list.addLast(element);
			}
		} else if (((Comparable<T>) element).compareTo(list.getFirst()) > 0) {
			list.addFirst(element);
		} else {
			for (T e : list) {
				if (((Comparable<T>) element).compareTo(e) > 0) {
					int index = list.indexOf(e);
					list.add(index, element);
					return;
				}
			}

			list.addLast(element);
		}
	}

	public static <T> void insertSort(T element, LinkedList<T> list,
			Comparator<? super T> c) {
		if (element == null || list == null) {
			return;
		}

		if (list.isEmpty()) {
			list.add(element);
		} else {
			if (c.compare(element, list.getFirst()) < 0) {
				list.addFirst(element);
			} else {
				for (T e : list) {
					if (c.compare(element, e) < 0) {
						int index = list.indexOf(e);
						list.add(index, element);
						return;
					}
				}

				list.addLast(element);
			}
		}
	}

	public static <T> void insertSort(T element, Integer compare,
			LinkedList<T> list, LinkedList<Integer> compares, boolean bAscending) {
		if (element == null || list == null) {
			return;
		}

		if (list.isEmpty()) {
			list.add(element);
			compares.add(compare);
		} else if (bAscending) {
			if (compare < compares.getFirst()) {
				list.addFirst(element);
				compares.addFirst(compare);
			} else {
				for (Integer f : compares) {
					if (compare < f) {
						int index = compares.indexOf(f);
						list.add(index, element);
						compares.add(index, compare);
						return;
					}
				}

				list.addLast(element);
				compares.addLast(compare);
			}
		} else if (compare > compares.getFirst()) {
			list.addFirst(element);
			compares.addFirst(compare);
		} else {
			for (Integer f : compares) {
				if (compare > f) {
					int index = compares.indexOf(f);
					list.add(index, element);
					compares.add(index, compare);
					return;
				}
			}

			list.addLast(element);
			compares.addLast(compare);
		}
	}

	public static <T> void insertSort(T element, Float compare,
			LinkedList<T> list, LinkedList<Float> compares, boolean bAscending) {
		if (element == null || list == null) {
			return;
		}

		if (list.isEmpty()) {
			list.add(element);
			compares.add(compare);
		} else if (bAscending) {
			if (compare < compares.getFirst()) {
				list.addFirst(element);
				compares.addFirst(compare);
			} else {
				for (Float f : compares) {
					if (compare < f) {
						int index = compares.indexOf(f);
						list.add(index, element);
						compares.add(index, compare);
						return;
					}
				}

				list.addLast(element);
				compares.addLast(compare);
			}
		} else if (compare > compares.getFirst()) {
			list.addFirst(element);
			compares.addFirst(compare);
		} else {
			for (Float f : compares) {
				if (compare > f) {
					int index = compares.indexOf(f);
					list.add(index, element);
					compares.add(index, compare);
					return;
				}
			}

			list.addLast(element);
			compares.addLast(compare);
		}
	}

	private static class TestElement implements Comparable<TestElement> {

		private float data;

		public TestElement(float d) {
			data = d;
		}

		public float getData() {
			return data;
		}

		@Override
		public int compareTo(TestElement o) {
			if (data == o.data) {
				return 0;
			}
			return (data < o.data) ? -1 : 1;
		}

	}

	private static class TestElementComparator implements
			Comparator<TestElement> {

		@Override
		public int compare(TestElement o1, TestElement o2) {
			if (o1.data == o2.data) {
				return 0;
			}
			return (o1.data < o2.data) ? -1 : 1;
		}

	}

	public static void main(String... args) {
		int count = 1000;

		List<TestElement> elements = new ArrayList<>(count);
		List<TestElement> elements3 = new ArrayList<>(count);
		float[] compares2 = new float[count];
		List<Float> elements2 = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			float a = (float) (Math.random() * count);
			elements.add(new TestElement(a));
			elements3.add(new TestElement(a));
			elements2.add(a);
			compares2[i] = a;
		}

		long t1 = System.currentTimeMillis();
		LinkedList<TestElement> list = insertSort(elements, true);
		long t2 = System.currentTimeMillis();
		System.out.println("dt = " + (t2 - t1) + "ms");

		t1 = System.currentTimeMillis();
		LinkedList<TestElement> list3 = insertSort(elements3,
				new TestElementComparator());
		t2 = System.currentTimeMillis();
		System.out.println("dt3 = " + (t2 - t1) + "ms");

		t1 = System.currentTimeMillis();
		LinkedList<Float> list2 = insertSort(elements2, compares2, true);
		t2 = System.currentTimeMillis();
		System.out.println("dt2 = " + (t2 - t1) + "ms");

		for (int i = 0; i < count; i++) {
			System.out.println(list.get(i).getData());
		}
	}
}
