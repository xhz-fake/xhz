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
	printf("��������Ϣ��\n");
	printf("������\n");
	scanf("%s", stu1.name);
	printf("���䣺\n");
	scanf("%d", &stu1.age);
	printf("�˺ţ�\n");
	scanf("%d", &stu1.id);
	printf("���룺\n");
	scanf("%d", &stu1.psw);
	printf("ѧ�֣�\n");
	scanf("%d", &stu1.score);

	printf("------ע��ɹ�!------\n-----��Ϣ����:------\n");
	printf("������%s\n���䣺%d\n�˺ţ�%d\n���룺%d\nѧ�֣�%d", stu1.name, stu1.age, stu1.id, stu1.psw, stu1.score);

	return 0;
}
