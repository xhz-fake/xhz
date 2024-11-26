#include <stdio.h>
#include <math.h>

int main() {
	int t = 2;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		long long n;
		int k;
		scanf("%lld %d", &n, &k);
		double ans = pow(n, 1.0 / k);
		printf("%.6lf\n", ans);
	}
	return 0;
}