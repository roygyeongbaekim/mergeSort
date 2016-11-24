package mergeSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class MergeSort3Way {
	static int count = 0; // mergeȽ�� count�� ���� ����
	
	public static void merge(int[] left, int[] mid, int[] right, int result[]) {
		int l = 0; 	// ���� index
		int r = 0; 	// ������ index
		int m = 0;	// �߰� index
		int ri = 0; // result index

		while (l < left.length) {	// ���� �迭���� ����
			if (m < mid.length) {	// �߰� �迭 ���� ������
				if (r < right.length) { // ������ �迭 ����������
					if (left[l] < mid[m] && left[l] < right[r]) {	// ���� �ش� index�� �迭�� ���� ���� ������
						result[ri] = left[l];						// �ش簪 ����
						l++;
					} else if (mid[m] < left[l] && mid[m] < right[r]) {	// �߰� �ش� index�� �迭�� ���� ���� ������ 
						result[ri] = mid[m];							// �ش簪 ����
						m++;
					} else {										// ������ �ش� index�� �迭�� ���� ���� ������
						result[ri] = right[r];						// �ش簪 ����
						r++;
					}
					ri++;
				} else {											// ������ �迭�� �������� �ʴٸ�
					if (left[l] < mid[m]) {							// ���� ���� ������
						result[ri] = left[l];						// �ش簪 ����
						l++;
					} else {										// �߰� ���� ������
						result[ri] = mid[m];						// �ش簪 ����
						m++;
					}
					ri++;
				}
			} else {				// �߰��迭�� �������� ������
				if (r < right.length) {	// ������ �迭�� ����������
					if (left[l] < right[r]) {	// ���� ���� ������
						result[ri] = left[l];	// �ش簪 ����
						l++;
					} else {					// ���� ���� �������� �ʴٸ�
						result[ri] = right[l];	// ������ �� ����
						r++;
					}
					ri++;
				} else {				// ������ �迭�� �������� �ʴٸ�
					while (l < left.length) {	// �߰� ���� �� �����Ƿ� ������ �迭�� ����
						result[ri] = left[l];
						l++;
						ri++;
					}
				}
			}
		}

		while (m < mid.length) {	// ���� ���� ���� �߰�, �����ʸ� ���������� 
			if (r < right.length) {	// ������ ���� ����������
				if (mid[m] < right[r]) {	// ���Ͽ� �߰����� �� ������
					result[ri] = mid[m];	// �ش簪 ����
					m++;
				} else {			// ������ ���� �� ������
					result[ri] = right[r];	// ������ �� ����
					r++;
				}
				ri++;
			} else {				// ������ ���� �������� ������
				result[ri] = mid[m];// �߰� �� ����
				m++;
				ri++;
			}
		}

		while (r < right.length) {	// �����ʸ� ���������Ƿ�
			result[ri] = right[r];	// ������ ������ �� ����
			r++;
			ri++;
		}
	}

	public static void mergeSort(int[] A) {
		int n = A.length;

		int length1 = 0; // ���� �迭 ���� ������ ���� ���� ����
	    int length2 = 0; // �߰� �迭 ���� ������ ���� ���� ����
	    int length3 = 0; // ������ �迭 ���� ������ ���� ���� ����
	    
	    if (n % 3 == 0) {		// �迭�� ũ�⸦ ��Ȯ�ϰ� 3��� �ϱ� ���� ���� ����
	        length1 = n / 3;
	        length2 = n / 3;
	        length3 = n / 3;
	    } else if (n % 3 == 1) {
	        length1 = (n / 3) + 1;
	        length2 = n / 3;
	        length3 = n / 3;
	    } else { //if (n % 3 == 2)
	        length1 = (n / 3) + 1;
	        length2 = n / 3;
	        length3 = (n / 3) + 1;
	    }
	    
		if (n == 1 || n == 0) {
			return;
		}
		
		int[] left = new int[length1]; // ���� ���� �迭 ũ�� ����
		int[] mid = new int[length2];
		int[] right = new int[length3];

		for (int i = 0; i < left.length; i++) { // ũ�⸸ŭ �� �Է�
			left[i] = A[i];
		}
		
		for (int i = 0; i < mid.length; i++) { // ũ�⸸ŭ �� �Է�
			mid[i] = A[i + left.length];
		}

		for (int i = 0; i < right.length; i++) { // ũ�⸸ŭ �� �Է�
			right[i] = A[i + left.length + mid.length];
		}

		mergeSort(left);	// ���� �迭 mergesort
		mergeSort(mid);		// �߰� �迭 mergesort
		mergeSort(right);	// ������ �迭 mergesort

		merge(left, mid, right, A);	// �պ�
		count++;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("hw02_10man.txt"); // �����͸� �ҷ��´�.
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
		
		long startTime = System.nanoTime();
		mergeSort(data);							// �պ������� �����Ѵ�.
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		double seconds = (double)elapsedTime / 1000000000.0;
		System.out.println("total elapsed time = " + elapsedTime);
		System.out.println("seconds = " + seconds);
		
		br.close();
	
		FileOutputStream fos = new FileOutputStream("hw01_01_201002378_3way_merge_10man.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		for (int i = 0; i<data.length; i++){
			String tmp = Integer.toString(data[i]);
			bw.write(tmp);
			bw.write(",");
		}
	
		
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
