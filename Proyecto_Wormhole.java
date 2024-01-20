/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto_wormhole;

/**
 *
 * @author Bryantcore
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Proyecto_Wormhole {
    
    public static String amarillo = "\33[33m";
    public static String cian="\33[36m";
    public static String rojo="\33[31m";
    
    public static String reset = "\33[0m";
    public static String reset2 = "\33[39m";  
    
    private static String personaje=cian+"@"+reset2;
    private static String obst=rojo+"#"+reset2;
    private static String pieza=amarillo+"*"+reset2;
          
    //dimensiones del escenario
    private static int filas=11;
    private static int columnas=31;
    private static int tamanno_escenario=filas+columnas;
    //PENDIENTE: construir condicion para que filas y columnas puedan aceptar numeros pares
        
    //posición inicial del jugador (por defecto: centro)
    private static int posX=filas/2;
    private static int posY=columnas/2;
         
    ////crea una matriz "escenario" con variables de dimensiones "filas" y "columnas"
    private static String[][] escenario=new String[filas][columnas];
    
    private static void derrota() throws IOException, InterruptedException{
        System.out.println("\n*** DERROTA ***\nVuelve a intentarlo.");
        System.exit(0);
    }
    
    //habilitar ANSI en el CMD
    private static void println(String s) {
        try {
            new ProcessBuilder("cmd", "/c", "echo " + s).inheritIO().start().waitFor();
        } catch (InterruptedException|IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void generarObst() {
        Random random = new Random();

        // Genera una cantidad aleatoria de obstáculos
        int cantidadObst = random.nextInt(tamanno_escenario);

        for (int i = 0; i < cantidadObst; i++) {
            int obstaculoX = random.nextInt(filas - 2) + 1; // Evita colocar obstáculos en los bordes
            int obstaculoY = random.nextInt(columnas - 2) + 1;

            escenario[obstaculoX][obstaculoY]=(obst);
        }
    }
    
    private static Random random2=new Random();
    private static int cantidadObjetos=random2.nextInt(3,10);
    private static int piezasRecuperadas=cantidadObjetos;
    
    public static void generarObjetos(){
        for (int i=0;i<cantidadObjetos;i++){
            int objetosX=random2.nextInt(filas-2)+1;
            int objetosY=random2.nextInt(columnas-2)+1;
        
            escenario[objetosX][objetosY]=pieza;
        }
    }
    
    private static void crearEscenario(){
        //haciendo que cada espacio del escenario sea una "."
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                escenario[i][j] = obst;
            }
        }

        // Dibuja "#" en los bordes y rellena el interior del escenario con espacios " "
        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {
                escenario[i][j] = " ";
            }
        }
    }
    
    public static void limpiarConsola() throws IOException, InterruptedException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    
    public static void dibujarEscenario() throws IOException{ //mostrando el escenario
        
        for (int i=0;i<escenario.length;i++){
            for (int j=0;j<escenario[i].length;j++){
                System.out.print(escenario[i][j]);
            }
            System.out.println();
            escenario[posX][posY]=personaje; //coloca al personaje en las coord xy
        }
        System.out.println("\n  PIEZAS RESTANTES: "+piezasRecuperadas);
        if (piezasRecuperadas==0){
            System.out.println("\n*** VICTORIA ***");
            System.exit(0);
        }
    }
    
    public static void moverJugador() throws IOException, InterruptedException{
        escenario[posX][posY]=" "; //???
        Scanner control=new Scanner(System.in);
        System.out.println("\nCÓMO MOVERSE:\n\nIngresa 'w' para moverse hacia arriba.\nIngresa 's' para moverse hacia abajo.\nIngresa 'a' para moverse hacia la izquierda.\nIngresa 'd' para moverse hacia la derecha.");
        System.out.print("\n> ");
        String s=control.next();
        if (s.equals("d")){
            posY++;
            if (escenario[posX][posY] == obst) {
                derrota();
            }
            if (escenario[posX][posY]==pieza){
                piezasRecuperadas--;
            }
            limpiarConsola();
            dibujarEscenario();
            moverJugador();
        } else if (s.equals("a")){
            posY--;
            if (escenario[posX][posY] == obst) {
                derrota();
            }
            if (escenario[posX][posY]==pieza){
                piezasRecuperadas--;
            }
            limpiarConsola();
            dibujarEscenario();
            moverJugador();
        } else if (s.equals("s")){
            posX++;
            if (escenario[posX][posY] == obst) {
                derrota();
            }
            if (escenario[posX][posY]==pieza){
                piezasRecuperadas--;
            }
            limpiarConsola();
            dibujarEscenario();
            moverJugador();
        } else if (s. equals("w")){
            posX--;
            if (escenario[posX][posY] == obst) {
                derrota();
            }
            if (escenario[posX][posY]==pieza){
                piezasRecuperadas--;
            }
            limpiarConsola();
            dibujarEscenario();
            moverJugador();
        } else {
            System.out.println("Comando no válido.");
            limpiarConsola();
            dibujarEscenario();
            moverJugador();
        }
    }
    
    public static void main(String[] args) throws InterruptedException, IOException{
        
        limpiarConsola();
        System.out.println("*****************************");
        System.out.println("        THE LEGEND OF");
        System.out.println(amarillo+"     ▄█ ███    █▄  ███▄▄▄▄   "+reset2);
        System.out.println(amarillo+"    ███ ███    ███ ███▀▀▀██▄ "+reset2);
        System.out.println(amarillo+"    ███ ███    ███ ███   ███ "+reset2);
        System.out.println(amarillo+"    ███ ███    ███ ███   ███ "+reset2);
        System.out.println(amarillo+"    ███ ███    ███ ███   ███ "+reset2);
        System.out.println(amarillo+"    ███ ███    ███ ███   ███ "+reset2);
        System.out.println(amarillo+"    ███ ███    ███ ███   ███ "+reset2);
        System.out.println(amarillo+"█▄ ▄███ ████████▀   ▀█   █▀  "+reset2);
        System.out.println(amarillo+"▀▀▀▀▀▀                       "+reset2);
        System.out.println("*****************************");
        System.out.println("Recolecta todas piezas (*) para ganar.");
        System.out.println("Si tocas un obstáculo (#), pierdes.");
        System.out.println("*****************************");
        System.out.println("    \n[ PRESIONA ENTER PARA JUGAR ]");
        System.out.println("\n*****************************");
        System.in.read();

        limpiarConsola();
        crearEscenario();
        generarObst();
        generarObjetos();
        dibujarEscenario();
        
        moverJugador();
    }    
}