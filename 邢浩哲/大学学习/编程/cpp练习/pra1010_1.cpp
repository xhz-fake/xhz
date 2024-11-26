#include <bits/stdc++.h>
void addnum(int a, int b);

int main() {
	int a, b;
	int ans;
	scanf("%d%d", &a, &b);
	addnum(a, b);
	return 0;

}

void addnum(int a, int b) {

	int ans = a + b;
	printf("%d", ans);
}