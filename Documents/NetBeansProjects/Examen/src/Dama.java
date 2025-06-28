//2) contar la cantidad de pasos que da de cada solucion, pasos para la solucion 
import java.util.LinkedList;
public class Dama {
public static void main(String[] args) {
    int m[][] = new int[3][3];
    mostrar(m);
    // m[0][1]=1;
    // m[0][2]=1;
    // m[1][2]=-1;
    // m[1][0]=1;
    // m[2][0]=1;
    // m[2][1]=1;
    DamaA(m, 0, 0, 2, 2, 1);
    // DamaB(m, 0, 0, 2, 2, 1);
    // DamaC(m, 0, 0, 2, 2, 1);
    // DamaD(m, 0, 0, 2, 2, 1);
    // DamaD1(m, 0, 0, 2, 2, 1);
    //DamaE(m, 0, 0, 2, 2, 1);
    //DamaE1(m, 0, 0, 2, 2, 1);
    System.out.println("Soluciones = " + c);
    System.out.println("Soluciones con 1 pasos: " + cant1);
    System.out.println("Soluciones con 2 pasos: " + cant2);
    System.out.println("Soluciones con 3 pasos: " + cant3);
    System.out.println("Soluciones con 4 pasos: " + cant4);
    System.out.println("Soluciones con 5 pasos: " + cant5);
    System.out.println("Soluciones con 6 pasos: " + cant6);
    System.out.println("Soluciones con 7 pasos: " + cant7);
    System.out.println("Soluciones con 8 pasos: " + cant8);
    System.out.println("Soluciones con 9 pasos: " + cant9);
}
public static int c;
public static int cant1 = 0,cant2 = 0, cant3 = 0, cant4 = 0, cant5 = 0;
public static int cant6 = 0, cant7 = 0, cant8 = 0, cant9 = 0;

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
//Esa función distancia calcula la distancia euclidiana entre dos puntos (i, j) y (i1, j1)
private static int distancia(int i, int j, int i1, int j1) {
    return (int) (Math.sqrt(Math.pow(i1 - i, 2) + Math.pow(j1 - j, 2)));//distancia=sqrt(i1-i)^2 +(j1-j)^2
}
//aplica una heuristica voraz para acercarse al destino: selecciona el siguiente paso mas prometedor
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
/**********************************REGLAS **************************************/
public static LinkedList<Regla> reglasAplicablesDama(int m[][], int i, int j) {
    LinkedList<Regla> L = new LinkedList<>();
    int k = 1;//Izquierda ←
    while (posValida(m, i, j - k)) {
        L.add(new Regla(i, j - k));
        k++;
    }
    k=1;//Diagonal arriba-izquierda ↖
    while (posValida(m, i - k, j - k)) {
        L.add(new Regla(i - k, j - k));
        k++;
    }
    k=1;//Arriba ↑
    while (posValida(m, i - k, j)) {
        L.add(new Regla(i - k, j));
        k++;
    }
    k=1;//Diagonal arriba-derecha ↗
    while (posValida(m, i - k, j + k)) {
        L.add(new Regla(i - k, j + k));
        k++;
    }
    k=1;//Derecha →
    while (posValida(m, i, j + k)) {
        L.add(new Regla(i, j + k));
        k++;
    }
    k=1;//Diagonal abajo-derecha ↘
    while (posValida(m, i + k, j + k)) {
        L.add(new Regla(i + k, j + k));
        k++;
    }
    k=1;// Abajo ↓
    while (posValida(m, i + k, j)) {
        L.add(new Regla(i + k, j));
        k++;
    }
    k=1;//Diagonal abajo-izquierda ↙
    while (posValida(m, i + k, j - k)) {
        L.add(new Regla(i + k, j - k));
        k++;
    }
    return L;
}
/*******************************************************************************/
/*a)Algoritmo para mostrar todos los caminos posibles desde una posición inicial
a una posición final. Además, mostrar la cantidad de soluciones posibles.*/
public static void DamaA(int m[][], int i, int j,int i1, int j1, int paso) {
    m[i][j] = paso;
    if (i == i1 && j == j1) {
        mostrar(m);
        c++;
        if (paso == 1) 
            cant1++;
        if (paso == 2) 
            cant2++;
        if (paso == 3) 
            cant3++;
        if (paso == 4) 
            cant4++;
        if (paso == 5) 
            cant5++;
        if (paso == 6) 
            cant6++;
        if (paso == 7) 
            cant7++;
        if (paso == 8) 
            cant8++;
        if (paso == 9) 
            cant9++;
    }
    LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
    Regla r;
    while (!L1.isEmpty()) {
        r = elegirReglaB(L1, i1, j1);
        m[r.fila][r.columna] = paso;
        DamaA(m, r.fila, r.columna, i1, j1, paso + 1);
        m[r.fila][r.columna] = 0;
    }
}
/*b)Algoritmo para mostrar todos los caminos posibles desde una posición inicial
a una posición final tal que se visiten todas las casillas de la matriz.
Además, mostrar la cantidad de soluciones posibles.*/
public static void DamaB(int m[][], int i, int j,int i1, int j1, int paso) {
    m[i][j] = paso;
    if (i == i1 && j == j1 && !matrizSinCeros(m)) {
        mostrar(m);
        c++;
    }
    LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
    Regla r;
    while (!L1.isEmpty()) {
        r = elegirReglaB(L1, i1, j1);
        m[r.fila][r.columna] = paso;
        DamaB(m, r.fila, r.columna, i1, j1, paso + 1);
        m[r.fila][r.columna] = 0;
    }
}
/*c)Algoritmo para mostrar todos los caminos posibles desde una posición inicial
a una posición final tal que NO se visiten todas las casillas de la matriz.
Además, mostrar la cantidad de soluciones posibles.*/
public static void DamaC(int m[][], int i, int j,int i1, int j1, int paso) {
    m[i][j] = paso;
    if (i == i1 && j == j1 && matrizSinCeros(m)) {
        mostrar(m);
        c++;
    }
    LinkedList<Regla> L1 = reglasAplicablesDama(m, i, j);
    Regla r;
    while (!L1.isEmpty()) {
        r = elegirReglaB(L1, i1, j1);
        m[r.fila][r.columna] = paso;
        DamaC(m, r.fila, r.columna, i1, j1, paso + 1);
        m[r.fila][r.columna] = 0;
    }
}
/*d)Algoritmo para mostrar todos los caminos posibles de máxima longitud desde
una posición inicial a una posición final. Además, mostrar la cantidad
de soluciones posibles.*/
public static int max;
public static void DamaD(int[][] m, int i, int j, int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso > max){
            max = paso;
        }
    }
    LinkedList<Regla> L = reglasAplicablesDama(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        DamaD(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
public static void DamaD1(int[][] m, int i, int j,int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso == max){
            mostrar(m);
            c++;
        }
    }
    LinkedList<Regla> L = reglasAplicablesDama(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        DamaD1(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
/*e)Algoritmo para mostrar todos los caminos posibles de mínima longitud desde
una posición inicial a una posición final. Además, mostrar la cantidad
de soluciones posibles.*/
public static int min;
public static void DamaE(int[][] m, int i, int j, int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso < min){
            min = paso;
        }
    }
    LinkedList<Regla> L = reglasAplicablesDama(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        DamaE(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
public static void DamaE1(int[][] m, int i, int j,int i1, int j1, int paso){
    m[i][j] = paso;
    if(i == i1 && j == j1){
        if(paso == min){
            mostrar(m);
            c++;
        }
    }
    LinkedList<Regla> L = reglasAplicablesDama(m, i, j);
    while(!L.isEmpty()){
        Regla R = elegirReglaB(L, i1, j1);
        m[R.fila][R.columna] = paso;
        DamaE1(m, R.fila, R.columna, i1, j1, paso + 1);
        m[R.fila][R.columna] = 0;
    }
}
}