package com.cpfei.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    public static <T> List<T> getArrayList(T[] arr) {
        int count = arr.length;
        List<T> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(arr[i]);
        }

        return list;
    }

    public static long[] getArray(List<Long> list) {
		if (list != null && !list.isEmpty()) {
			int count = list.size();
			long[] array = new long[count];

			for (int i = 0; i < count; i++) {
				array[i] = list.get(i);
			}

			return array;
		}

		return null;
	}

    public static <T> void copyArray(T[] src, T[] dst) {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
    }

    public static void copyArray(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
    }

    public static void copyArray(float[] src, float[] dst) {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
    }

    public static void copyArray(double[] src, double[] dst) {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
    }

    public static <T> List<T> getSubArray(List<T> arr, int start, int end) {
        int count = arr.size();
        start = Math.max(start, 0);
        end = Math.min(end, count - 1);
        if (start > end) {
            return null;
        }

        List<T> list = new ArrayList<>(end - start + 1);
        for (int i = start; i <= end; i++) {
            T e = arr.get(i);
            list.add(e);
        }

        return list;
    }

}
