package com.cpfei.utils.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSort {

    /**
     * Sort elements by its compares array
     *
     * @param elements List of complex object
     * @param compares Data used when compare object
     * @param low Start position to sort
     * @param high End position to sort
     * @param isAsc Sort type
     */
    public static <T> void quickSort(List<T> elements, long[] compares, int low,
            int high, boolean isAsc) {
        if (elements.size() != compares.length) {
            return;
        }

        int i = low;
        int j = high;

        // int RandIndex = (low + high)/2;
        int RandIndex = (int) (Math.random() * (high - low + 1) + low);
        long middle = compares[RandIndex];

        long temp;
        T tempElement;
        if (isAsc) {// Ascending
            while (i <= j) {
                while ((compares[i] < middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] > middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        } else {// Descending
            while (i <= j) {
                while ((compares[i] > middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] < middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        }

        if (j > low) {
            quickSort(elements, compares, low, j, isAsc);
        }
        if (i < high) {
            quickSort(elements, compares, i, high, isAsc);
        }
    }

    /**
     * Sort elements by its compares array
     *
     * @param elements List of complex object
     * @param compares Data used when compare object
     * @param low Start position to sort
     * @param high End position to sort
     * @param isAsc Sort type
     */
    public static <T> void quickSort(List<T> elements, int[] compares, int low,
            int high, boolean isAsc) {
        if (elements.size() != compares.length) {
            return;
        }

        int i = low;
        int j = high;

        // int RandIndex = (low + high)/2;
        int RandIndex = (int) (Math.random() * (high - low + 1) + low);
        int middle = compares[RandIndex];

        int temp;
        T tempElement;
        if (isAsc) {// Ascending
            while (i <= j) {
                while ((compares[i] < middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] > middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        } else {// Descending
            while (i <= j) {
                while ((compares[i] > middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] < middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        }

        if (j > low) {
            quickSort(elements, compares, low, j, isAsc);
        }
        if (i < high) {
            quickSort(elements, compares, i, high, isAsc);
        }
    }

    /**
     * Sort elements by its compares array
     *
     * @param elements List of complex object
     * @param compares Data used when compare object
     * @param low Start position to sort
     * @param high End position to sort
     * @param isAsc Sort type
     */
    public static <T> void quickSort(List<T> elements, float[] compares, int low,
            int high, boolean isAsc) {
        if (elements.size() != compares.length) {
            return;
        }

        int i = low;
        int j = high;

        // int RandIndex = (low + high)/2;
        int RandIndex = (int) (Math.random() * (high - low + 1) + low);
        float middle = compares[RandIndex];

        float temp;
        T tempElement;
        if (isAsc) {// Ascending
            while (i <= j) {
                while ((compares[i] < middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] > middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        } else {// Descending
            while (i <= j) {
                while ((compares[i] > middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] < middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        }

        if (j > low) {
            quickSort(elements, compares, low, j, isAsc);
        }
        if (i < high) {
            quickSort(elements, compares, i, high, isAsc);
        }
    }

    /**
     * Sort elements by its compares array
     *
     * @param <T>
     * @param elements List of complex object
     * @param compares Data used when compare object
     * @param low Start position to sort
     * @param high End position to sort
     * @param isAsc Sort type
     */
    public static <T> void quickSort(List<T> elements, double[] compares, int low,
            int high, boolean isAsc) {
        if (elements.size() != compares.length) {
            return;
        }

        int i = low;
        int j = high;

        // int RandIndex = (low + high)/2;
        int RandIndex = (int) (Math.random() * (high - low + 1) + low);
        double middle = compares[RandIndex];

        double temp;
        T tempElement;
        if (isAsc) {// Ascending
            while (i <= j) {
                while ((compares[i] < middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] > middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        } else {// Descending
            while (i <= j) {
                while ((compares[i] > middle) && (i < high)) {
                    i++;
                }
                while ((compares[j] < middle) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    temp = compares[i];
                    compares[i] = compares[j];
                    compares[j] = temp;

                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        }

        if (j > low) {
            quickSort(elements, compares, low, j, isAsc);
        }
        if (i < high) {
            quickSort(elements, compares, i, high, isAsc);
        }
    }

    /**
     * Sort elements. The elements must implement Comparable<T>. When use this
     * method, must make sure that the function compareTo() is speed enough!
     *
     * @param elements The elements must implement Comparable<T>
     * @param low
     * @param high
     * @param isAsc Sort type
     */
    @SuppressWarnings("unchecked")
    public static <T> void quickSort(List<T> elements, int low, int high, boolean isAsc) {
        int i = low;
        int j = high;

        // int RandIndex = (low + high)/2;
        int RandIndex = (int) (Math.random() * (high - low + 1) + low);
        T middle = elements.get(RandIndex);

        T tempElement;
        if (isAsc) {// Ascending
        	while (i <= j) {
                while ((((Comparable<T>) elements.get(i)).compareTo(middle) < 0) && (i < high)) {
                    i++;
                }
                while ((((Comparable<T>) elements.get(j)).compareTo(middle) > 0) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        } else {
        	while (i <= j) {
                while ((((Comparable<T>) elements.get(i)).compareTo(middle) > 0) && (i < high)) {
                    i++;
                }
                while ((((Comparable<T>) elements.get(j)).compareTo(middle) < 0) && (j > low)) {
                    j--;
                }

                if (i <= j) {
                    tempElement = elements.get(i);
                    elements.set(i, elements.get(j));
                    elements.set(j, tempElement);

                    i++;
                    j--;
                }
            }
        }
        

        if (j > low) {
            quickSort(elements, low, j, isAsc);
        }
        if (i < high) {
            quickSort(elements, i, high, isAsc);
        }
    }
    
    /**
     * Sort elements by comparator.
     * @param elements
     * @param low
     * @param high
     * @param c The comparator of element
     */
    public static <T> void quickSort(List<T> elements, int low, int high, Comparator<? super T> c) {
        int i = low;
        int j = high;

        // int RandIndex = (low + high)/2;
        int RandIndex = (int) (Math.random() * (high - low + 1) + low);
        T middle = elements.get(RandIndex);

        T tempElement;
        while (i <= j) {
        	while ((c.compare(elements.get(i), middle) < 0) && (i < high)) {
                i++;
            }
            while ((c.compare(elements.get(j), middle) > 0) && (j > low)) {
                j--;
            }

            if (i <= j) {
                tempElement = elements.get(i);
                elements.set(i, elements.get(j));
                elements.set(j, tempElement);

                i++;
                j--;
            }
        }

        if (j > low) {
            quickSort(elements, low, j, c);
        }
        if (i < high) {
            quickSort(elements, i, high, c);
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
    
    private static class TestElementComparator implements Comparator<TestElement> {
    	
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

        // Make data
        float[] compares2 = new float[count];
        List<Float> elements2 = new ArrayList<>(count);
        List<TestElement> elements3 = new ArrayList<>(count);
        List<TestElement> elements4 = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            float a = (float) (Math.random() * count);
            elements2.add(a);
            compares2[i] = a;
            elements3.add(new TestElement(a));
            elements4.add(new TestElement(a));
        }

        // Test begin...
        long t1 = System.currentTimeMillis();
        quickSort(elements2, compares2, 0, elements2.size() - 1, true);
        long t2 = System.currentTimeMillis();
        System.out.println("dt2 = " + (t2 - t1) + "ms");

        t1 = System.currentTimeMillis();
        quickSort(elements3, 0, elements3.size() - 1, true);
        t2 = System.currentTimeMillis();
        System.out.println("dt3 = " + (t2 - t1) + "ms");
        
        t1 = System.currentTimeMillis();
        quickSort(elements4, 0, elements4.size() - 1, new TestElementComparator());
        t2 = System.currentTimeMillis();
        System.out.println("dt4 = " + (t2 - t1) + "ms");

		for (int i = 0; i < count; i++) {
			System.out.println(elements3.get(i).getData());
		}
    }

}
