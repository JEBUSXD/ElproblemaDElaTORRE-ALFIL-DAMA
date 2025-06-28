//1)examen. que se muestren los caminos menores a 5 pasos para el (laberintoA)
import java.util.LinkedList;//EL PROBLEMA DE LA TORRE, ALFIL, DAMA.
public class Torre {
public static void main(String[] args) {
    int m[][] = new int[3][3];//creamos una matriz de 3x3
    mostrar(m);
    // m[0][1]=1;
    // m[0][2]=1;
    // m[1][2]=-1;
    // m[1][0]=1;
    // m[2][0]=1;
    // m[2][1]=1;
    LaberintoA(m, 0, 0, 2, 2, 1);
     //laberintoB(m, 0, 0, 2, 2, 1);
    // laberintoC(m, 0, 0, 2, 2, 1);
     //laberintoD(m, 0, 0, 2, 2, 1);
    // laberintoD1(m, 0, 0, 2, 2, 1);
    // laberintoE(m, 0, 0, 2, 2, 1);
    // laberintoE1(m, 0, 0, 2, 2, 1);
    System.out.println("Soluciones = " + c);
}
//va a recorreo 
public static int c;
public static void mostrar(int m[][]) {
    if (m != null) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print("" + m[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
//Valida si (i, j) esta dentro de la matriz y no ha sido visitada (es 0).
public static boolean posValida(int m[][], int i, int j) {
    return m != null && i >= 0 && i < m.length && j >= 0 && j < m[0].length && m[i][j] == 0;
}
//Esa función distancia calcula la distancia euclidiana entre dos puntos (i, j) y (i1, j1)
private static int distancia(int i, int j, int i1, int j1) {
    return (int) (Math.sqrt(Math.pow(i1 - i, 2) + Math.pow(j1 - j, 2)));//distancia=sqrt(i1-i)^2 +(j1-j)^2
}
//Heuristica, Escoge los movimientos posibles la casilla más cercana a la meta (x, y) usando la distancia Euclidiana.
public static Regla elegirReglaB(LinkedList<Regla> L1, int x, int y) {
    int i = 0, menor = Integer.MAX_VALUE, posMenor = 0, dist;
    while (i < L1.size()) {
        dist = distancia(L1.get(i).fila, L1.get(i).columna, x, y);
        if (dist < menor) {
            menor = dist;
            posMenor = i;
        }
        i++;
    }
    return L1.remove(posMenor);
}
/*visita todas las filas y columnas y verifica que no sean ceros */
public static boolean matrizSinCeros(int[][] m) {
    for (int i = 0; i < m.length; i++) {
        for (int j = 0; j < m[i].length; j++) {
            if (m[i][j] == 0) {
                return false;
            }
        }
    }
    return true;
}
//genera todos los movimientos posibles en línea recta: izquierda, arriba, derecha, abajo
public static LinkedList<Regla> reglasAplicables(int[][] m, int i, int j) {
    LinkedList<Regla> L = new LinkedList<>();
    int k = 1; //horizontal izquierda
    while (posValida(m, i, j - k)) {
        L.add(new Regla(i, j - k));
        k = k + 1;
    }
    k = 1; //vertical arriba
    while (posValida(m, i - k, j)) {
        L.add(new Regla(i - k, j));
        k = k + 1;
    }
    k = 1; //horizontal derecha
    while (posValida(m, i, j + k)) {
        L.add(new Regla(i, j + k));
        k = k + 1;
    }
    k = 1; //vertical abajo
    while (posValida(m, i + k, j)) {
        L.add(new Regla(i + k, j));
        k = k + 1;
    }
    return L;
}
/*(i1, j1): posición objetivo (final).******(i, j): posición actual en la matriz.
paso: número de paso actual en el camino (empezando en 1).*/
/*******************************************************************************/
/*a)Algoritmo para mostrar todos los caminos posibles desde una posición inicial
a una posición final. Además, mostrar la cantidad de soluciones posibles.*/
public static void LaberintoA(int m[][], int i, int j,int i1, int j1, int paso) {
    m[i][j] = paso;
    if (i == i1 && j == j1) {
        if (paso <= 5) {
            mostrar(m);
            c++;
        }
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    Regla r;
    while (!L1.isEmpty()) {
        r = elegirReglaB(L1, i1, j1);
        m[r.fila][r.columna] = paso;
        LaberintoA(m, r.fila, r.columna, i1, j1, paso + 1);
        m[r.fila][r.columna] = 0;
    }
}
/*b)Algoritmo para mostrar todos los caminos posibles desde una posición inicial
a una posición final tal que se visiten todas las casillas de la matriz.
Además, mostrar la cantidad de soluciones posibles.*/
public static void laberintoB(int[][] m, int i, int j,int i1, int j1, int paso) {
    m[i][j] = paso;
    if (i == i1 && j == j1 && matrizSinCeros(m)) {
        mostrar(m);
        c++;
    }
    LinkedList<Regla> L = reglasAplicables(m, i, j);
    while (!L.isEmpty()) {
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        laberintoB(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
/*c) Algoritmo para mostrar todos los caminos posibles desde una posición inicial
a una posición final tal que NO se visiten todas las casillas de la matriz.
Además, mostrar la cantidad de soluciones posibles.*/
public static void laberintoC(int m[][], int i, int j,int i1, int j1, int paso) {
    m[i][j] = paso;
    if (i == i1 && j == j1 && !matrizSinCeros(m)) {
        mostrar(m);
        c++;
    }
    LinkedList<Regla> L1 = reglasAplicables(m, i, j);
    Regla r;
    while (!L1.isEmpty()) {
        r = elegirReglaB(L1, i1, j1);
        m[r.fila][r.columna] = paso;
        laberintoC(m, r.fila, r.columna, i1, j1, paso + 1);
        m[r.fila][r.columna] = 0;
    }
}
/*d)Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
una posición inicial a una posición final. Además, mostrar la cantidad
de soluciones posibles.*/
public static int max;
public static void laberintoD(int[][] m, int i, int j, int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso > max){
            max = paso;
        }
    }
    LinkedList<Regla> L = reglasAplicables(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        laberintoD(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
public static void laberintoD1(int[][] m, int i, int j,int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso == max){
            mostrar(m);
            c++;
        }
    }
    LinkedList<Regla> L = reglasAplicables(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        laberintoD1(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
/*e)Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
una posición inicial a una posición final. Además, mostrar la cantidad
de soluciones posibles.*/
public static int min;
public static void laberintoE(int[][] m, int i, int j, int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso < min){
            min = paso;
        }
    }
    LinkedList<Regla> L = reglasAplicables(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        laberintoE(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
public static void laberintoE1(int[][] m, int i, int j,int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso == min){
            mostrar(m);
            c++;
        }
    }
    LinkedList<Regla> L = reglasAplicables(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        laberintoE1(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
}