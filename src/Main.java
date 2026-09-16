import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            greetUser();
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            ArrayList<String> parts = parseLine(line);
            String command = parts.getFirst();
            parts.removeFirst();

            switch (command) {
                case "ls":
                    if (parts.isEmpty()) {
                        System.out.println("ls");
                        break;
                    }
                    System.out.println("Команда ls вызвана с аргументами " + parts);
                    break;
                case "cd":
                    if (parts.isEmpty()) {
                        System.out.println("cd");
                        break;
                    }
                    System.out.println("Команда cd вызвана с аргументами " + parts);
                    break;
                case "exit":
                    System.out.println("Выполняется выход из программы...");
                    return;
                default:
                    System.out.println("Имя '" + command + "' не распознано как имя командлета, функции, файла сценария" +
                            " или выполняемой программы.");
            }

        }
    }

    public static void greetUser() {
        String username = System.getProperty("user.name");
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        }
        catch (Exception e) {
            hostname = "hostname";
        }

        String result = username + "@" + hostname + ":~$ ";
        System.out.print(result);
    }

    public static ArrayList<String> parseLine(String line) {
        ArrayList<String> args = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean insideKAVICHKI = false;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                insideKAVICHKI = !insideKAVICHKI;
            } else if (line.charAt(i) == ' ' && !insideKAVICHKI) {
                if (!stringBuilder.isEmpty()) {
                    args.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
            } else {
                stringBuilder.append(line.charAt(i));
            }
        }
        if (!stringBuilder.isEmpty()) {
            args.add(stringBuilder.toString());
        }

        return args;
    }
}