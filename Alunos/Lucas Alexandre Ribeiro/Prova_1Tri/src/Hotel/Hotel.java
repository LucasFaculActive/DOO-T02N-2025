package Hotel;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
	static final int TOTAL_QUARTOS = 30;
    static Map<String, Integer> quartosOcupadosPorData = new HashMap<>();
    static Map<String, Cliente> clientes = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== Sistema do Hotel ===");
            System.out.println("1. Cadastro de Cliente");
            System.out.println("2. Clistes Registrados");
            System.out.println("3. Cadastro de Quarto");
            System.out.println("4. Fazer Reserva");
            System.out.println("5. Fazer Check-in");
            System.out.println("6. Fazer Check-out");
            System.out.println("7. Calcular Diárias");
            System.out.println("8. Verificar Disponibilidade de Quarto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> cadastroDeCliente(scanner);
                case 2 -> listarClientes();
                case 3 -> cadastroDeQuarto(scanner);
                case 4 -> reservaDeQuarto(scanner);
                case 5 -> checkIn(scanner);
                case 6 -> checkOut(scanner);
                case 7 -> calcularDiarias(scanner);
                case 8 -> verificarDisponibilidade(scanner);
                case 0 -> {
                    executando = false;
                    System.out.println("Encerrando o sistema...");
                }
                default -> System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    public static void cadastroDeCliente(Scanner scanner) {
    	 System.out.print("Digite o CPF do cliente: ");
    	    String cpf = scanner.nextLine();

    	    if (clientes.containsKey(cpf)) {
    	        System.out.println("Este cliente já está cadastrado.");
    	    } else {
    	        System.out.print("Digite o nome do cliente: ");
    	        String nome = scanner.nextLine();
    	        System.out.print("Digite o telefone do cliente: ");
    	        String telefone = scanner.nextLine();

    	        Cliente cliente = new Cliente(nome, telefone, cpf);
    	        clientes.put(cpf, cliente);
    	        System.out.println("Cliente cadastrado com sucesso!");
    	    }
    }
    
    public static void listarClientes() {
    	 if (clientes.isEmpty()) {
    	        System.out.println("Nenhum cliente cadastrado.");
    	    } else {
    	        System.out.println("\nLista de Clientes:");
    	        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
    	            Cliente c = entry.getValue();
    	            System.out.println("Nome: " + c.nome + " | Telefone: " + c.telefone + " | CPF: " + c.cpf);
    	        }
    	    }
    }

    public static void cadastroDeQuarto(Scanner scanner) {
        System.out.print("Digite o tipo de quarto (simples/duplo): ");
        String tipo = scanner.nextLine();
        System.out.println("Quarto " + tipo + " cadastrado com sucesso!");
    }

    public static void reservaDeQuarto(Scanner scanner) {
    	System.out.print("Digite o nome do cliente para reserva: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a data da reserva (dd/mm/yyyy): ");
        String data = scanner.nextLine();

        int ocupados = quartosOcupadosPorData.getOrDefault(data, 0);
        
        if (ocupados < TOTAL_QUARTOS) {
            quartosOcupadosPorData.put(data, ocupados + 1);
            System.out.println("Reserva feita com sucesso para " + nome + " na data " + data);
        } else {
            System.out.println("Não há quartos disponíveis para a data " + data);
        }
    }

    public static void checkIn(Scanner scanner) {
        System.out.print("Digite o nome do cliente para Check-In: ");
        String nome = scanner.nextLine();
        System.out.println("Check-in realizado para " + nome);
    }

    public static void checkOut(Scanner scanner) {
        System.out.print("Digite o nome do cliente para Check-Out: ");
        String nome = scanner.nextLine();
        System.out.println("Check-out realizado para " + nome);
    }

    public static void calcularDiarias(Scanner scanner) {
        System.out.print("Quantas diárias o cliente ficou? ");
        int dias = scanner.nextInt();
        scanner.nextLine();
        double total = dias * 250.00;
        System.out.println("Total a pagar: R$" + total);
    }

    public static void verificarDisponibilidade(Scanner scanner) {
    	System.out.print("Digite a data desejada (dd/mm/yyyy): ");
        String data = scanner.nextLine();

        int ocupados = quartosOcupadosPorData.getOrDefault(data, 0);
        int disponiveis = TOTAL_QUARTOS - ocupados;

        if (disponiveis > 0) {
            System.out.println("Há " + disponiveis + " quartos disponíveis em " + data);
        } else {
            System.out.println("Não há quartos disponíveis em " + data);
        }
    }
    
    static class Cliente {
        String nome;
        String telefone;
        String cpf;

        Cliente(String nome, String telefone, String cpf) {
            this.nome = nome;
            this.telefone = telefone;
            this.cpf = cpf;
        }
    }
}
