package Practice;
import java.util.*;

public class pra251029_1 {
    private Stack<Integer> stack;
    private int[] staticVariables;
    private int[] localVariables;
    private Map<String, Integer> staticVarMap;
    private Map<String, Integer> localVarMap;
    private int staticIndex;
    private int localIndex;

    public pra251029_1() {
        stack = new Stack<>();
        staticVariables = new int[100]; // 假设最多100个静态变量
        localVariables = new int[100];  // 假设最多100个局部变量
        staticVarMap = new HashMap<>();
        localVarMap = new HashMap<>();
        staticIndex = 0;
        localIndex = 0;
    }

    public void execute(List<String> instructions) {
        System.out.println("虚拟机开始运行！");
        System.out.println("指令开始执行！");
        System.out.println("栈为空 ");

        // 先处理所有的VAR声明
        for (String instruction : instructions) {
            if (instruction.startsWith("VAR")) {
                processVarDeclaration(instruction);
            }
        }

        // 执行指令
        for (String instruction : instructions) {
            if (instruction.isEmpty()) continue;

            if (instruction.startsWith("VAR")) {
                // VAR声明已经在前面处理过了，跳过执行
                continue;
            }

            System.out.println("执行指令：" + instruction+" ");
            executeInstruction(instruction);

            if (instruction.equals("HLT")) {
                System.out.println("指令结束执行！");
                System.out.println("虚拟机结束运行！");
                return;
            }else{
                // 输出栈状态
                if (stack.isEmpty()) {
                    System.out.println("栈为空 ");
                } else {
                    System.out.println("栈顶元素为：" + stack.peek()+" ");
                }
            }
        }
    }

    private void processVarDeclaration(String instruction) {
        String[] parts = instruction.split(" ");
        if (parts.length < 3) return;

        String type = parts[1];
        String varName = parts[2];

        if (type.equals("static")) {
            if (!staticVarMap.containsKey(varName)) {
                staticVarMap.put(varName, staticIndex);
                staticIndex++;
            }
        } else if (type.equals("local")) {
            if (!localVarMap.containsKey(varName)) {
                localVarMap.put(varName, localIndex);
                localIndex++;
            }
        }
    }

    private void executeInstruction(String instruction) {
        String[] parts = instruction.split(" ");
        String opcode = parts[0];

        switch (opcode) {
            case "PUSH":
                handlePush(parts);
                break;
            case "POP":
                handlePop(parts);
                break;
            case "ADD":
                handleAdd();
                break;
            case "SUB":
                handleSub();
                break;
            case "MUL":
                handleMul();
                break;
            case "HLT":
                // 停止执行，在外部方法中处理
                break;
        }
    }

    private void handlePush(String[] parts) {
        if (parts[1].equals("constant")) {
            int value = Integer.parseInt(parts[2]);
            stack.push(value);
        } else {
            // 处理变量名
            String varName = parts[1];
            if (staticVarMap.containsKey(varName)) {
                int index = staticVarMap.get(varName);
                stack.push(staticVariables[index]);
            } else if (localVarMap.containsKey(varName)) {
                int index = localVarMap.get(varName);
                stack.push(localVariables[index]);
            } else {
                // 如果变量未声明，当作静态变量处理
                if (!staticVarMap.containsKey(varName)) {
                    staticVarMap.put(varName, staticIndex);
                    staticIndex++;
                }
                int index = staticVarMap.get(varName);
                stack.push(staticVariables[index]);
            }
        }
    }

    private void handlePop(String[] parts) {
        if (stack.isEmpty()) {
            return;
        }

        int value = stack.pop();
        String varName = parts[1];

        if (staticVarMap.containsKey(varName)) {
            int index = staticVarMap.get(varName);
            staticVariables[index] = value;
        } else if (localVarMap.containsKey(varName)) {
            int index = localVarMap.get(varName);
            localVariables[index] = value;
        } else {
            // 如果变量未声明，当作局部变量处理
            if (!localVarMap.containsKey(varName)) {
                localVarMap.put(varName, localIndex);
                localIndex++;
            }
            int index = localVarMap.get(varName);
            localVariables[index] = value;
        }
    }

    private void handleAdd() {
        if (stack.size() < 2) return;
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a + b);
    }

    private void handleSub() {
        if (stack.size() < 2) return;
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a - b);
    }

    private void handleMul() {
        if (stack.size() < 2) return;
        int b = stack.pop();
        int a = stack.pop();
        stack.push(a * b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> instructions = new ArrayList<>();

        // 读取输入直到空行
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            instructions.add(line);
        }

        pra251029_1 vm = new pra251029_1();
        vm.execute(instructions);
    }
}