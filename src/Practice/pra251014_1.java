package Practice;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 简单的基于栈的虚拟机实现 - 精简版
 */
public class pra251014_1 {

    private final Stack<Integer> stack = new Stack<>();
    private final List<String[]> program = new ArrayList<>();
    private int pc = 0;
    private boolean isRunning = false;

    /**
     * 从输入中加载指令序列
     */
    public void loadProgram(List<String> inputLines) throws IllegalArgumentException {
        for (String line : inputLines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String trimmedLine = line.trim();
            String[] parts = trimmedLine.toUpperCase().split("\\s+");

            if (parts.length == 0 || parts[0].isEmpty()) continue;

            String opcode = parts[0];

            switch (opcode) {
                case "PUSH":
                    if (parts.length < 2) {
                        throw new IllegalArgumentException("指令错误: PUSH 需要一个整数参数。");
                    }
                    try {
                        Integer.parseInt(parts[1]); // 验证参数是否为整数
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("指令错误: PUSH 参数必须是整数。");
                    }
                    // 以 [opcode, operand] 格式存储
                    program.add(new String[]{opcode, parts[1]});
                    break;
                case "ADD":
                case "SUB":
                case "MUL":
                case "POP":
                case "HLT":
                    // 以 [opcode] 格式存储
                    program.add(new String[]{opcode});
                    break;
                default:
                    throw new IllegalArgumentException("未知指令: " + opcode);
            }
        }
    }

    /**
     * 取指和译码：获取当前 pc 指向的指令和参数
     */
    private String[] fetchDecode() {
        if (pc >= program.size()) {
            return new String[]{"HLT"}; // 模拟 HLT 结束
        }
        String[] instruction = program.get(pc);
        pc++;
        return instruction;
    }

    /**
     * 打印当前栈的状态 (栈顶元素或为空)
     * @param lastOpcode 刚执行的指令的操作码
     */
    private void printStackState(String lastOpcode) {
        if (!stack.isEmpty()) {
            System.out.println("栈顶元素为：" + stack.peek());
        } else if (!"HLT".equals(lastOpcode)) {
            System.out.println("栈为空");
        }
    }

    /**
     * 执行指令
     */
    private void execute(String[] instruction) throws RuntimeException {
        String opcode = instruction[0];

        // 打印指令内容
        String instructionStr = opcode;
        if (instruction.length > 1) {
            instructionStr += " " + instruction[1];
        }
        System.out.println("执行指令：" + instructionStr);

        // 执行操作
        int a, b;
        switch (opcode) {
            case "PUSH":
                stack.push(Integer.parseInt(instruction[1]));
                break;
            case "ADD":
                if (stack.size() < 2) throw new RuntimeException("ADD 错误：栈中元素不足两个");
                b = stack.pop();
                a = stack.pop();
                stack.push(a + b);
                break;
            case "SUB":
                if (stack.size() < 2) throw new RuntimeException("SUB 错误：栈中元素不足两个");
                b = stack.pop(); // 减数
                a = stack.pop(); // 被减数
                stack.push(a - b);
                break;
            case "MUL":
                if (stack.size() < 2) throw new RuntimeException("MUL 错误：栈中元素不足两个");
                b = stack.pop();
                a = stack.pop();
                stack.push(a * b);
                break;
            case "POP":
                if (stack.isEmpty()) throw new RuntimeException("POP 错误：栈为空");
                stack.pop(); // 弹出元素
                break;
            case "HLT":
                isRunning = false;
                System.out.println("指令结束执行！");
                break;
        }

        // 打印执行后的栈状态
        printStackState(opcode);
    }

    /**
     * 运行虚拟机主循环
     */
    public void run() {
        System.out.println("虚拟机开始运行！");
        System.out.println("指令开始执行！");

        isRunning = true;

        // 初始栈状态
        System.out.println("栈为空");

        try {
            while (isRunning) {
                String[] instruction = fetchDecode();
                execute(instruction);
            }
        } catch (RuntimeException e) {
            System.err.println("\n运行时错误: " + e.getMessage());
        }

        System.out.println("虚拟机结束运行！");
    }

    /**
     * 主函数：读取输入并运行虚拟机
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();

        // 读取输入，直到遇到空行
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.trim().isEmpty()) {
                break; // 遇到空行，停止读取
            }
            inputLines.add(line);
        }

        if (inputLines.isEmpty()) {
            // System.out.println("未输入指令，程序退出。");
            return;
        }

        pra251014_1 vm = new pra251014_1();
        try {
            vm.loadProgram(inputLines);
            vm.run();
        } catch (IllegalArgumentException e) {
            System.err.println("程序加载错误: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}