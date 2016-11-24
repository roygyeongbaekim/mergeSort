package mergeSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class MergeSort {
	static int count = 0; // merge횟수 count를 위한 변수
	
	public static void merge(int[] left, int[] right, int mData[]) {
		int l = 0;	// 왼쪽 배열 index
		int r = 0;	// 오른쪽 배열 index
		int m = 0;	// merge한 결과 배열 index

		while (l < left.length) {		// 왼쪽 데이터 배열을 모두 merge할때 까지
			if (r < right.length) {		// 오른쪽 데이터 배열이 남아 있을때
				if (left[l] < right[r]) { 	// 왼쪽 해당 index의 값이 오른쪽 해당 index보다 작으면
					mData[m] = left[l];		// 왼쪽 데이터 합병
					l++;					// 왼쪽배열의 다음 index값으로 변경
				} else {				// 오른쪽 해당 index의 값이 왼쪽 해당 index보다 작으면
					mData[m] = right[r];	// 오른쪽 데이터 합병
					r++;					// 오른쪽 배열의 다음 index값으로 변경
				}
				m++;						// 결과 배열 index 증가
			} else {					// 오른쪽 데이터 배열에 값이 없을때
				while (l < left.length) {	// 왼쪽 데이터 배열에 남은 모든값 merge
					mData[m] = left[l];
					l++;
					m++;
				}
			}
		}

		while (r < right.length) {		// 오른쪽 데이터 배열이 남아있는 경우
			mData[m] = right[r];		// 나머지 값 합병
			r++;
			m++;
		}
	}

	public static void mergeSort(int[] A) {
		int n = A.length;

		if (n == 1) {			// 배열의 값이 1개으므로 더이상 쪼개지지 않을때
			return;
		}

		int[] left = new int[n / 2]; // 배열의 크기 지정
		int[] right = new int[n - n / 2];  

		for (int i = 0; i < n / 2; i++) { // 배열을 반으로 나눈다.
			left[i] = A[i];
		}

		for (int i = 0; i < n - n / 2; i++) { // 배열을 반으로 나눈다.
			right[i] = A[i + n / 2];
		}
		mergeSort(left);					// 왼쪽꺼부터 나누고 정렬한다.
		mergeSort(right);					// 오른쪽 배열을 나누고 정렬한다.
		
		merge(left, right, A);				// 나눈 결과값을 바탕으로 병합한다.
		count++;							// merge 횟수를 위한 count값 증가
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("hw02_uk.txt"); // 데이터를 불러온다.
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
		
		long startTime = System.nanoTime();			// 시간 측정을 위한 함수
		mergeSort(data);							// 합병정렬을 시작한다.
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;		// nano seconds로 표시된다.
		double seconds = (double)elapsedTime / 1000000000.0; // seconds단위로 바꿔준다.
		System.out.println("total elapsed time = " + elapsedTime + "ns");
		System.out.println("seconds = " + seconds);
		
		br.close();
		
		FileOutputStream fos = new FileOutputStream("hw01_01_201002378_merge_uk.txt"); // mergesort가 완료된 값을 저장하기 위한 파일 생성
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");				// character Encoding 설정
		BufferedWriter bw = new BufferedWriter(osw);
		
		
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
