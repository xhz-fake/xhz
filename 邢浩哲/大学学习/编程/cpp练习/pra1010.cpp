#include <bits/stdc++.h>

void bubbleSort(int arr[], int n);
void printArray(int arr[], int size);

int main() {

	int arr[] = {1, 2, 3, 5, 3, 7, 5, 8, 9, 4, 6};
	int n = sizeof(arr) / sizeof(arr[0]);
	bubbleSort(arr, n);
	printArray(arr, n);
	return 0;
}

void bubbleSort(int arr[], int n) {
	int i, j;
	int temp = 0;
	for (i = 0; i < n - 1; i++) {
		for (j = 0; j < n - i - 1; j++) {
			if (arr[j] > arr[j + 1]) {
				temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}

		}
	}

}

void printArray (int arr[], int size) {
	int i;
	printf("排序后的数组：");
	for (i = 0; i < size; i++) {
		printf(" %d", arr[i]);

	}
}











