package br.unifil.dc.sisop;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {

    public static Path getDiretorioAtual() {
        return diretorioAtual;
    }

    public static void setDiretorioAtual(Path diretorioAtual) {
        Jsh.diretorioAtual = diretorioAtual;
    }

    public static Path diretorioAtual = (Paths.get("").toAbsolutePath());

    /**
     * Funcao principal do Jsh.
     */
    public static void promptTerminal() {
        System.out.println("Iniciando Prompt");
        while (true) {
            exibirPrompt();
            ComandoPrompt comandoEntrado = lerComando();
            executarComando(comandoEntrado);
        }
    }

    /**
     * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
     * terminal está pronto para receber o próximo comando como entrada.
     */
    public static void exibirPrompt() {
        String usuario = System.getProperty("user.name");
        String id = System.getProperty("user.id");
        String aux = "#";
        System.out.print(usuario + aux + id + ":" + diretorioAtual + "% ");
    }

    /**
     * Preenche as strings comando e parametros com a entrada do usuario do terminal.
     * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
     * outras palavras subsequentes sao parametros para o comando. A palavras sao
     * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
     * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
     * EOL (End Of Line).
     *
     * @return
     */
    public static ComandoPrompt lerComando() {
        Scanner entrada = new Scanner(System.in);

        ComandoPrompt cmd = new ComandoPrompt(entrada.nextLine());

        return cmd;
    }

    /**
     * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
     * se for, o executa.
     * <p>
     * Se nao for, verifica se é o nome de um programa terceiro localizado no atual
     * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
     * esse processo executa, o processo do uniterm deve permanecer em espera.
     * <p>
     * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
     * programa desconhecido.
     */
    public static void executarComando(ComandoPrompt comando) {

        if (comando.getNome().equals("") || comando.getNome().equals(" ")) {
            System.out.println("Comando Invalido");
        } else {
            switch (comando.getNome()) {
                case ("encerrar"):
                    System.out.println("Encerrando....");
                    System.exit(0);

                case ("relogio"):
                    ComandosInternos.exibirRelogio();
                    break;
                case ("la"):
                    ComandosInternos.escreverListaArquivos(diretorioAtual.toString());
                    break;
                case ("cd"):
                    ComandosInternos.criarNovoDiretorio(comando.getArgumentos().get(0));
                    break;
                case ("ad"):
                    ComandosInternos.apagarDiretorio(comando.getArgumentos().get(0));
                    break;
                case ("mdt"):
                    String novoDiretorio = comando.getArgumentos().get(0);
                    if (Files.exists(Paths.get(novoDiretorio))) {
                        System.setProperty("user.dir", novoDiretorio + System.getProperty("file.separator"));
                        System.out.println(novoDiretorio + System.getProperty("file.separator"));
                        setDiretorioAtual(Paths.get(novoDiretorio + System.getProperty("file.separator")));
                    }
                    break;
                default:
                    executarPrograma(comando);
                    break;
            }
        }

    }

    public static int executarPrograma(ComandoPrompt comando) {
        try {
            ProcessBuilder pb1 = new ProcessBuilder("cmd", "/C", "start " + comando);
            Process p1 = pb1.start();
            System.out.println("Programa executado com sucesso!");
        } catch (IOException e) {
            System.out.println("Programa não encontrado!");
        }
        return 0;
    }


    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) {

        promptTerminal();

    }


    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {
    }
}
