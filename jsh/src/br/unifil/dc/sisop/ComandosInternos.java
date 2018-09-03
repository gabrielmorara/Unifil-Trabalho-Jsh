package br.unifil.dc.sisop;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {

    public static int exibirRelogio() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");

        String hora = hourFormat.format(date);
        String data = dateFormat.format(date);

        System.out.println("Sao " + hora + " de " + data + ".");

        return 0;
    }

    public static int escreverListaArquivos(String nomeDir) {
        File file = new File(nomeDir);
        File[] arquivos = file.listFiles();

        try {
            for (File arquivo : arquivos) {
                System.out.println(arquivo.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int criarNovoDiretorio(String nomeDir) {

        boolean success = (new File(nomeDir)).mkdir();
        if (!success) {
            System.out.println("Não foi possivel criar o diretorio");
        } else {
            System.out.println("Diretorio " + nomeDir + " criado com sucesso !");
        }

        return 0;
    }

    public static int apagarDiretorio(String nomeDir) {

        File dir = new File(nomeDir);
        if (dir.isDirectory()) {
            File[] sun = dir.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }

        boolean success = (new File(nomeDir)).delete();

        if (!success) {
            System.out.println("Falha na Remocao");
        } else {
            System.out.println("Diretorio " + nomeDir + " apagado com sucesso !");
        }

        return 0;
    }

    public static int mudarDiretorioTrabalho(String nomeDir) {
        File dir = new File(nomeDir);
        boolean existe = dir.exists();
        if(existe){
            dir.canExecute();
        }

        return 0;
    }

    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {

    }
}
