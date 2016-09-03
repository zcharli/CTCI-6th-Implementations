package lib;

/**
 * Created by czl on 25/08/16.
 */
public class MergeSort {

    public static void merge_sort(int[] a)
    {
        if (a == null || a.length == 1)
        {
            return;
        }
        _merge_sort(a, 0, a.length - 1);
    }

    public static void _merge_sort(int[] a, int start, int end)
    {
        if (start >= end)
        {
            return;
        }

        int mid = (end + start) / 2;
        _merge_sort(a, start, mid);
        _merge_sort(a, mid + 1, end);
        _merge(a, start, mid + 1, end);
    }

    public static void _merge(int[] a, int start, int mid, int end)
    {
        int left_n = mid - start;
        int right_n = end - mid + 1;
        int[] left = new int[left_n];
        int[] right = new int[right_n];

        for (int i = 0; i < left_n; i++)
        {
            left[i] = a[start + i];
        }
        for (int i = 0; i < right_n; i++)
        {
            right[i] = a[mid + i];
        }

        int cur_idx = start;
        int left_i = 0;
        int right_i = 0;
        while (left_i < left_n && right_i < right_n)
        {
            if (left[left_i] < right[right_i])
            {
                a[cur_idx] = left[left_i++];
            }
            else if (right_i < right_n)
            {
                a[cur_idx] = right[right_i++];
            }
            cur_idx++;
        }

        while (left_i < left_n)
        {
            a[cur_idx++] = left[left_i++];
        }

        while (right_i < right_n)
        {
            a[cur_idx++] = right[right_i++];
        }
    }

    public static void print_arraty(int[] a)
    {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i != a.length-1)
            {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        int[] a = new int[] {1,2,6,5,7,9,3,8,4};
        print_arraty(a);
        merge_sort(a);
        print_arraty(a);
    }
}
