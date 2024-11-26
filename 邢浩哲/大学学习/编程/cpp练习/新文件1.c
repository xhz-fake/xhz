#include <stdio.h>
#include <string.h>

struct Person {
	char name[80];
	int age;
	int id;
	int psw;
	int score;

};

typedef struct {
	char name[80];
	int age;
	int id;
	int psw;
	int score;

} Student;

int main() {
	Student stu1;
	printf("请输入信息：\n");
	printf("姓名：\n");
	scanf("%s", stu1.name);
	printf("年龄：\n");
	scanf("%d", &stu1.age);
	printf("账号：\n");
	scanf("%d", &stu1.id);
	printf("密码：\n");
	scanf("%d", &stu1.psw);
	printf("学分：\n");
	scanf("%d", &stu1.score);

	printf("------注册成功!------\n-----信息如下:------\n");
	printf("姓名：%s\n年龄：%d\n账号：%d\n密码：%d\n学分：%d", stu1.name, stu1.age, stu1.id, stu1.psw, stu1.score);

	return 0;
}
