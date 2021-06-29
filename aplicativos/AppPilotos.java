package aplicativos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import classes.Pessoa;
import classes.Pilotos;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        final int MAX_ERROS_CPF = 2;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui
                Pilotos p = new Pilotos();

                System.out.println("Qual o nome do piloto: ");
                p.setNome(in.nextLine());

                System.out.println("Qual o numero da matricula: ");
                p.setMatricula(in.nextLine());

                System.out.println("Qual o numero da breve: ");
                p.setBreve(in.nextLine());

                int cont = 0;

                do {
                    try {
                        System.out.println("Digite o seu CPF: ");
                        p.setCpf(in.nextLine());
                        
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage() + " Digite de novo: ");
                        cont ++;   
                    }
                }while (p.getCpf() == null && cont < MAX_ERROS_CPF);

                if(p.getCpf() == null){
                    System.out.printf("cpf errado, o programa sera encerrado  ", cont);
                    return;
                }
                pilotos[qtdCadastrados] = p;
                qtdCadastrados = qtdCadastrados + 1;

            System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);

                
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há motoristas cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui
                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.printf("\nPiloto %d: %s\n", (i+1), pilotos[i].getNome());
                    System.out.printf("CPF %s\n",  pilotos[i].getCpf());
                    System.out.printf("MATRICULA %s\n",  pilotos[i].getMatricula());
                    System.out.printf("BREVE %s\n",  pilotos[i].getBreve());
                }

                voltarMenu(in);

            } else if (opcao == 3) {
                System.out.println("Consulte o cpf");
                String cpf = (in.nextLine());

                for (int i = 0; i < qtdCadastrados; i++) {
                    if(cpf.equals(pilotos[i].getCpf())){
                        System.out.printf("\n PilotoEncontrado \n");
                        System.out.printf("\n Piloto %d: %s\n", (i+1), pilotos[i].getNome());
                        System.out.printf("CPF: %s\n",  pilotos[i].getCpf());
                        System.out.printf("MATRICULA: %s\n",  pilotos[i].getMatricula());
                        System.out.printf("BREVE: %s\n",  pilotos[i].getBreve());

                    }else{
                        System.out.println("piloto não cadastrado");
                        voltarMenu(in);
                    }
                    
                }


                voltarMenu(in);
            } else if (opcao == 4) {
                System.out.println("Digite o novo tamanho de aramazenamento, maior que: " + MAX_ELEMENTOS);
                int tamVetor = in.nextInt();
                Pessoa[] nvVetor = new Pessoa[tamVetor];
                int posicao = 0;
                for(Pessoa pessoa:pilotos){
                    if(tamVetor > 0){
                        nvVetor[posicao] = pessoa;
                        posicao++;
                    }
                }
            }
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}