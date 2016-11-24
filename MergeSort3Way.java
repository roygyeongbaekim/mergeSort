package mergeSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class MergeSort3Way {
	static int count = 0; // merge횟수 count를 위한 변수
	
	public static void merge(int[] left, int[] mid, int[] right, int result[]) {
		int l = 0; 	// 왼쪽 index
		int r = 0; 	// 오른쪽 index
		int m = 0;	// 중간 index
		int ri = 0; // result index

		while (l < left.length) {	// 왼쪽 배열부터 시작
			if (m < mid.length) {	// 중간 배열 남아 있으면
				if (r < right.length) { // 오른쪽 배열 남아있으면
					if (left[l] < mid[m] && left[l] < right[r]) {	// 왼쪽 해당 index값 배열의 값이 가장 작으면
						result[ri] = left[l];						// 해당값 삽입
						l++;
					} else if (mid[m] < left[l] && mid[m] < right[r]) {	// 중간 해당 index값 배열의 값이 가장 작으면 
						result[ri] = mid[m];							// 해당값 삽입
						m++;
					} else {										// 오른쪽 해당 index값 배열의 값이 가장 작으면
						result[ri] = right[r];						// 해당값 삽입
						r++;
					}
					ri++;
				} else {											// 오른쪽 배열이 남아있지 않다면
					if (left[l] < mid[m]) {							// 왼쪽 값이 작으면
						result[ri] = left[l];						// 해당값 삽입
						l++;
					} else {										// 중간 값이 작으면
						result[ri] = mid[m];						// 해당값 삽입
						m++;
					}
					ri++;
				}
			} else {				// 중간배열이 남아있지 않으면
				if (r < right.length) {	// 오른쪽 배열이 남아있으면
					if (left[l] < right[r]) {	// 왼쪽 값이 작으면
						result[ri] = left[l];	// 해당값 삽입
						l++;
					} else {					// 왼쪽 값이 남아있지 않다면
						result[ri] = right[l];	// 오른쪽 값 삽입
						r++;
					}
					ri++;
				} else {				// 오른쪽 배열이 남아있지 않다면
					while (l < left.length) {	// 중간 왼쪽 다 없으므로 오른쪽 배열값 삽입
						result[ri] = left[l];
						l++;
						ri++;
					}
				}
			}
		}

		while (m < mid.length) {	// 왼쪽 값은 없고 중간, 오른쪽만 남아있을때 
			if (r < right.length) {	// 오른쪽 값이 남아있으면
				if (mid[m] < right[r]) {	// 비교하여 중간값이 더 작을때
					result[ri] = mid[m];	// 해당값 삽입
					m++;
				} else {			// 오른쪽 값이 더 작으면
					result[ri] = right[r];	// 오른쪽 값 삽입
					r++;
				}
				ri++;
			} else {				// 오른쪽 값이 남아있지 않으면
				result[ri] = mid[m];// 중간 값 삽입
				m++;
				ri++;
			}
		}

		while (r < right.length) {	// 오른쪽만 남아있으므로
			result[ri] = right[r];	// 오른쪽 나머지 값 삽입
			r++;
			ri++;
		}
	}

	public static void mergeSort(int[] A) {
		int n = A.length;

		int length1 = 0; // 왼쪽 배열 길이 설정을 위한 변수 선언
	    int length2 = 0; // 중간 배열 길이 설정을 위한 변수 선언
	    int length3 = 0; // 오른쪽 배열 길이 설정을 위한 변수 선언
	    
	    if (n % 3 == 0) {		// 배열의 크기를 정확하게 3등분 하기 위한 길이 설정
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
		
		int[] left = new int[length1]; // 값을 받을 배열 크기 설정
		int[] mid = new int[length2];
		int[] right = new int[length3];

		for (int i = 0; i < left.length; i++) { // 크기만큼 값 입력
			left[i] = A[i];
		}
		
		for (int i = 0; i < mid.length; i++) { // 크기만큼 값 입력
			mid[i] = A[i + left.length];
		}

		for (int i = 0; i < right.length; i++) { // 크기만큼 값 입력
			right[i] = A[i + left.length + mid.length];
		}

		mergeSort(left);	// 왼쪽 배열 mergesort
		mergeSort(mid);		// 중간 배열 mergesort
		mergeSort(right);	// 오른쪽 배열 mergesort

		merge(left, mid, right, A);	// 합병
		count++;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("hw02_10man.txt"); // 데이터를 불러온다.
		BufferedReader br = new BufferedReader(fr);

		String line = null;
		int[] data = null;

		while ((line = br.readLine()) != null) {     // 불러온 데이터에서 한줄을 읽어온다.
			String[] values = line.split(",");		// ,를 기준으로 값을 나눠준다.
			data = new int[values.length];		

			for (int i = 0; i < values.length; i++) { // 정렬을 위한 배열에 입력한다.
				data[i] = Integer.parseInt(values[i]); //불러온 String값을 int값으로 변경하고 배열에 입력
			}
		}
		
		long startTime = System.nanoTime();
		mergeSort(data);							// 합병정렬을 시작한다.
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
	
		
		for (int i = 0; i<data.length; i++){					// 정렬 결과를 파일에 삽입한다.
			String tmp = Integer.toString(data[i]);				// 숫자를 String형태로 변경한다.
			bw.write(tmp);
			bw.write(",");
		}
		String cnt = Integer.toString(count);
		bw.write(cnt); 						// merge 횟수를 삽입한다.
		bw.close();	
	}

}
