import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Doacao {
    private String tipo;
    private String quantidade;
    private String data;

    public Doacao(String tipo, String quantidade, String data) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Doação [Tipo: " + tipo + ", Quantidade: " + quantidade + ", Data: " + data + "]";
    }

    public String toFileString() {
        return data + ",    " + quantidade + ",    " + tipo;
    }
}

public class RegistroDoacoes {
    private static ArrayList<Doacao> doacoes = new ArrayList<>();
    private static final String FILENAME = "doacoes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Registrar Doação");
            System.out.println("2. Listar Doações");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarDoacao(scanner);
                    break;
                case 2:
                    listarDoacoes();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void registrarDoacao(Scanner scanner) {
        System.out.print("Digite o tipo de doação: ");
        String tipo = scanner.nextLine();

        System.out.print("Digite a quantidade: ");
        String quantidade = scanner.next();
        scanner.nextLine();

        System.out.print("Digite a data (dd/MM/yyyy): ");
        String data = scanner.nextLine();

        if (!isValidDate(data)) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        Doacao doacao = new Doacao(tipo, quantidade, data);
        doacoes.add(doacao);
        salvarDoacaoEmArquivo(doacao);
        System.out.println("Doação registrada com sucesso!");
    }

    private static void listarDoacoes() {
        File file = new File(FILENAME);

        if (!file.exists() || file.length() == 0) {
            System.out.println("Nenhuma doação registrada.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            System.out.println("Lista de Doações:");
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                System.out.println(linha);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    private static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void salvarDoacaoEmArquivo(Doacao doacao) {
        try (FileWriter fw = new FileWriter(FILENAME, true);
                PrintWriter pw = new PrintWriter(fw)) {
            pw.println(doacao.toFileString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar doação no arquivo: " + e.getMessage());
        }
    }
}
