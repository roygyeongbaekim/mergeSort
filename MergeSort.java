package mergeSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class MergeSort {
	static int count = 0; // mergeȽ�� count�� ���� ����
	
	public static void merge(int[] left, int[] right, int mData[]) {
		int l = 0;	// ���� �迭 index
		int r = 0;	// ������ �迭 index
		int m = 0;	// merge�� ��� �迭 index

		while (l < left.length) {		// ���� ������ �迭�� ��� merge�Ҷ� ����
			if (r < right.length) {		// ������ ������ �迭�� ���� ������
				if (left[l] < right[r]) { 	// ���� �ش� index�� ���� ������ �ش� index���� ������
					mData[m] = left[l];		// ���� ������ �պ�
					l++;					// ���ʹ迭�� ���� index������ ����
				} else {				// ������ �ش� index�� ���� ���� �ش� index���� ������
					mData[m] = right[r];	// ������ ������ �պ�
					r++;					// ������ �迭�� ���� index������ ����
				}
				m++;						// ��� �迭 index ����
			} else {					// ������ ������ �迭�� ���� ������
				while (l < left.length) {	// ���� ������ �迭�� ���� ��簪 merge
					mData[m] = left[l];
					l++;
					m++;
				}
			}
		}

		while (r < right.length) {		// ������ ������ �迭�� �����ִ� ���
			mData[m] = right[r];		// ������ �� �պ�
			r++;
			m++;
		}
	}

	public static void mergeSort(int[] A) {
		int n = A.length;

		if (n == 1) {			// �迭�� ���� 1�����Ƿ� ���̻� �ɰ����� ������
			return;
		}

		int[] left = new int[n / 2]; // �迭�� ũ�� ����
		int[] right = new int[n - n / 2];  

		for (int i = 0; i < n / 2; i++) { // �迭�� ������ ������.
			left[i] = A[i];
		}

		for (int i = 0; i < n - n / 2; i++) { // �迭�� ������ ������.
			right[i] = A[i + n / 2];
		}
		mergeSort(left);					// ���ʲ����� ������ �����Ѵ�.
		mergeSort(right);					// ������ �迭�� ������ �����Ѵ�.
		
		merge(left, right, A);				// ���� ������� �������� �����Ѵ�.
		count++;							// merge Ƚ���� ���� count�� ����
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("hw02_uk.txt"); // �����͸� �ҷ��´�.
		BufferedReader br = new BufferedReader(fr);

		String line = null;
		int[] data = null;

		while ((line = br.readLine()) != null) {     // �ҷ��� �����Ϳ��� ������ �о�´�.
			String[] values = line.split(",");		// ,�� �������� ���� �����ش�.
			data = new int[values.length];		

			for (int i = 0; i < values.length; i++) { // ������ ���� �迭�� �Է��Ѵ�.
				data[i] = Integer.parseInt(values[i]); //�ҷ��� String���� int������ �����ϰ� �迭�� �Է�
			}
		}
		
		long startTime = System.nanoTime();			// �ð� ������ ���� �Լ�
		mergeSort(data);							// �պ������� �����Ѵ�.
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;		// nano seconds�� ǥ�õȴ�.
		double seconds = (double)elapsedTime / 1000000000.0; // seconds������ �ٲ��ش�.
		System.out.println("total elapsed time = " + elapsedTime + "ns");
		System.out.println("seconds = " + seconds);
		
		br.close();
		
		FileOutputStream fos = new FileOutputStream("hw01_01_201002378_merge_uk.txt"); // mergesort�� �Ϸ�� ���� �����ϱ� ���� ���� ����
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");				// character Encoding ����
		BufferedWriter bw = new BufferedWriter(osw);
		
		
		for (int i = 0; i<data.length; i++){					// ���� ����� ���Ͽ� �����Ѵ�.
			String tmp = Integer.toString(data[i]);				// ���ڸ� String���·� �����Ѵ�.
			bw.write(tmp);
			bw.write(",");
		}
		String cnt = Integer.toString(count);
		bw.write(cnt); 						// merge Ƚ���� �����Ѵ�.
		bw.close();
	}

}
