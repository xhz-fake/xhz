package Practice;

import java.util.*;
import java.util.regex.*;

public class Main {
    private Stack<Integer> stack;
    private String[] instructions;
    private Map<String, Integer> labels;
    private int pc;

    public Main() {
        stack = new Stack<>();
        labels = new HashMap<>();
    }

    public void loadInstructions(List<String> input) {
        instructions = new String[input.size()];
        for (int i = 0; i < input.size(); i++) {
            instructions[i] = input.get(i);
        }
        // 预处理标签
        preprocessLabels();
    }

    private void preprocessLabels() {
        for (int i = 0; i < instructions.length; i++) {
            String instruction = instructions[i].trim();
            if (instruction.startsWith("LABEL")) {
                String[] parts = instruction.split("\\s+");
                if (parts.length >= 2) {
                    labels.put(parts[1], i);
                }
            }
        }
    }

    public void run() {
        System.out.println("虚拟机开始运行！");
        System.out.println("指令开始执行！");

        if (stack.isEmpty()) {
            System.out.println("栈为空");
        }

        pc = 0;
        while (pc < instructions.length) {
            String instruction = instructions[pc].trim();

            // 跳过空行和标签指令（标签在预处理时已经处理）
            if (instruction.isEmpty() || instruction.startsWith("LABEL")) {
                pc++;
                continue;
            }

            System.out.println("执行指令：" + instruction);

            if (executeInstruction(instruction)) {
                break; // HLT指令结束执行
            }
        }

        System.out.println("指令结束执行！");
        System.out.println("虚拟机结束运行！");
    }

    private boolean executeInstruction(String instruction) {
        String[] parts = instruction.split("\\s+");
        String opcode = parts[0];

        switch (opcode) {
            case "PUSH":
                int value = Integer.parseInt(parts[1]);
                stack.push(value);
                printStackTop();
                pc++;
                break;

            case "POP":
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                printStackTop();
                pc++;
                break;

            case "ADD":
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                }
                printStackTop();
                pc++;
                break;

            case "SUB":
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                }
                printStackTop();
                pc++;
                break;

            case "IFEQ":
                if (!stack.isEmpty()) {
                    int topValue = stack.pop();
                    String label = parts[1];
                    int targetLine = labels.get(label);

                    printStackTop();

                    if (topValue == 0) {
                        System.out.println("跳转到行：" + targetLine);
                        pc = targetLine;
                    } else {
                        System.out.println("继续执行下一行：" + (pc + 1));
                        pc++;
                    }
                } else {
                    printStackTop();
                    pc++;
                }
                break;

            case "GOTO":
                String label = parts[1];
                int targetLine = labels.get(label);
                printStackTop();
                System.out.println("跳转到行：" + targetLine);
                pc = targetLine;
                break;

            case "HLT":
                printStackTop();
                return true; // 结束执行

            default:
                printStackTop();
                pc++;
                break;
        }

        return false;
    }

    private void printStackTop() {
        if (stack.isEmpty()) {
            System.out.println("栈为空");
        } else {
            System.out.println("栈顶元素为：" + stack.peek());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputInstructions = new ArrayList<>();

        // 读取输入直到空行
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            inputInstructions.add(line);
        }
        scanner.close();

        Main vm = new Main();
        vm.loadInstructions(inputInstructions);
        vm.run();
    }
}