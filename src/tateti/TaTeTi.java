/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tateti;

import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TaTeTi {
    public static final int USU1 = 1; //Valor asignad al usuario 1.
    public static final int USU2 = -1; //Valor asignado al usuario 2.
    
    public static void main(String[] args) {
        int tablero[][];
        String presentacion[][];
        int fila, columna, turno = 0, aux = 0, filaNueva, columnaNueva, tamaño, limite;
        String marca = "X";
        boolean fin = false;
        
        String entradaTam = JOptionPane.showInputDialog("Ingrese el tamaño del tablero");
        tamaño = Integer.valueOf(entradaTam);
        limite=tamaño-1;
               
        tablero=new int[tamaño][tamaño];
        presentacion=new String[tamaño][tamaño];
        PresentacionPantalla(tamaño);
        
        while (fin != true) {
            try{
            String entradaFila = JOptionPane.showInputDialog("Turno de " + marca + " elija fila:");
            fila = Integer.valueOf(entradaFila) - 1;
            String entradaCol = JOptionPane.showInputDialog("Y elija la columna:");
            columna = Integer.valueOf(entradaCol) - 1;

            if (fila > limite || columna > limite|| fila < 0 || columna < 0) {
                JOptionPane.showMessageDialog(null, "El numero ingresado es incrrecto.");
            } else {
                
                if (turno < tamaño*2) {
                    if (tablero[fila][columna] != 0) {
                        JOptionPane.showMessageDialog(null, "Ese espacio ya esta ocupado.");
                    } else {
                        tablero[fila][columna] = Valor(marca);
                        marca = Cambio(marca);
                        turno++;
                    }
                } else {
                    if (tablero[fila][columna] != 0) {
                        if ((marca.equals("X") && tablero[fila][columna] == USU1) || (marca.equals("O") && tablero[fila][columna] == USU2)) {

                            entradaFila = JOptionPane.showInputDialog("Elija la fila a donde moverla:");
                            filaNueva = Integer.valueOf(entradaFila) - 1;
                            entradaCol = JOptionPane.showInputDialog("Y elija la columna:");
                            columnaNueva = Integer.valueOf(entradaCol) - 1;

                            if (tablero[filaNueva][columnaNueva] != 0) {
                                JOptionPane.showMessageDialog(null, "No puede realizar dicha jugada.");
                            } else {
                                if (((filaNueva - 1 == fila || filaNueva + 1 == fila) && columnaNueva == columna) || (filaNueva == fila && ((columnaNueva - 1 == columna) || (columnaNueva + 1 == columna)))) {
                                    tablero[fila][columna] = 0;
                                    tablero[filaNueva][columnaNueva] = Valor(marca);
                                    marca = Cambio(marca);
                                    turno++;
                                } else {
                                    JOptionPane.showMessageDialog(null, "No puede realizar dicha jugada.");
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "No tiene ficha propia en esa posicion.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La posicion esta vacia.");
                    }
                }
                
                System.out.println("Turno: "+turno);

                for (int i = 0; i < tamaño; i++) {
                    for (int j = 0; j < tamaño; j++) {
                        aux +=tablero[i][j];
                    }
                    fin = Comprobacion(aux, tamaño, fin);
                    aux = 0;

                    for (int j = 0; j < tamaño; j++) {
                        aux +=tablero[j][i];
                    }
                    fin = Comprobacion(aux, tamaño, fin);
                    aux = 0;

                    for (int j = 0; j < tamaño; j++) {
                        aux +=tablero[j][j];
                    }
                    fin = Comprobacion(aux, tamaño, fin);
                    aux = 0;

                    for (int j = 0; j < tamaño; j++) {
                        aux +=tablero[j][limite-j];
                    }
                    fin = Comprobacion(aux, tamaño, fin);
                    aux = 0;
                }

                for (int k = 0; k < tamaño; k++) {
                    for (int l = 0; l < tamaño; l++) {
                        switch (tablero[k][l]) {
                            case USU1:
                                presentacion[k][l] = "X";
                                break;
                            case USU2:
                                presentacion[k][l] = "O";
                                break;
                            default:
                                presentacion[k][l] = " ";
                                break;
                        }
                    }
                }

               tableroPre(presentacion,tamaño);
               anunciarFin(fin, marca);
                
            }
            }catch(NumberFormatException u){
                int preguntaFinal;
                preguntaFinal=JOptionPane.showConfirmDialog(null, "No ingreso un numero, ¿desea terminar el juego?","",JOptionPane.YES_NO_OPTION);
                if (preguntaFinal==0){
                    fin=true;
                }
            }
        }
            
    }

    private static boolean Comprobacion(int aux, int size, boolean fin) {
        int ganadorX, ganadorO;
        ganadorX=USU1*size;
        ganadorO=USU2*size;
        
        if (aux == ganadorX) {
            fin = true;
        } else if (aux == ganadorO) {
            fin = true;
        }
        return fin;
    }

    private static int Valor(String marca) {
        int resp;

        if (marca.equals("X")) {
            resp = USU1;
        } else {
            resp = USU2;
        }

        return resp;
    }

    private static String Cambio(String marca) {

        if (marca.equals("X")) {
            marca = "O";
        } else if (marca.equals("O")) {
            marca = "X";
        }
        return marca;
    }
        
    private static void PresentacionPantalla(int tam){
        for (int t = 0; t <tam ; t++) {
            System.out.print("    "+(t+1));
        }
        System.out.println("");
        for (int u = 0; u < tam; u++) {
                System.out.println(u+1);
            }
    }
    
    private static void anunciarFin(boolean fin, String marca){
         if (fin == true && marca.equals("X")) {
                    System.out.println("");
                    System.out.println("El ganador fue O");
                } else if (fin == true && marca.equals("O")) {
                    System.out.println("");
                    System.out.println("El ganador fue X");
                }
    }
    
    private static void tableroPre(String[][]datos, int size){
        
        for (int q = 0; q < size; q++) {
            System.out.print("    "+(q+1));
        }
        System.out.println("");
        for (int r = 0; r < size; r++) {
            System.out.print((r+1));
            for (int s = 0; s < size; s++) {
                System.out.print("   "+datos[r][s]+" ");
            }
            System.out.println("");
        }
    }
}
