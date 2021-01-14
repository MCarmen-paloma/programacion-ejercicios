package bingo;

public class Carton
{
    private int numCarton;
    private int[] linea1 = new int[5];
    private int[] linea2 = new int[5];
    private int tachados1;
    private int tachados2;
    private int lineaCantada;
    private boolean bingo;
    
    
    /**
     * Constructor vacío que genera un cartón con 2 líneas aleatorias
     */
    public Carton()
    {
        super();
        this.numCarton = (int)(Math.random()*100+1);
        this.linea1 = generarLinea(1, this.linea1);
        this.linea2 = generarLinea(2, this.linea2);
        this.tachados1 = 0;
        this.tachados2 = 0;
        this.lineaCantada = 0;
    }


    /**Constructor mediante el que puedes crear un cartón con números elegidos
     * @param linea1
     * @param linea2
     */
    public Carton(int[] linea1, int[] linea2)
    {
        super();
        this.numCarton = (int)(Math.random()*100+1);
        this.linea1 = linea1;
        this.linea2 = linea2;
        this.tachados1 = 0;
        this.tachados2 = 0;
        this.lineaCantada = 0;
    }


    /**
     * @return the numCarton
     */
    public int getNumCarton()
    {
        return numCarton;
    }
    

    /**
     * @return the linea1
     */
    public int[] getLinea1()
    {
        return linea1;
    }


    /**
     * @param linea1 the linea1 to set
     */
    public void setLinea1(int[] linea1)
    {
        this.linea1 = linea1;
    }


    /**
     * @return the linea2
     */
    public int[] getLinea2()
    {
        return linea2;
    }


    /**
     * @param linea2 the linea2 to set
     */
    public void setLinea2(int[] linea2)
    {
        this.linea2 = linea2;
    }
    
    /**
     * @return the tachados1
     */
    public int getTachados1()
    {
        return tachados1;
    }


    /**
     * @return the tachados2
     */
    public int getTachados2()
    {
        return tachados2;
    }
    

    /**
     * @return the lineaCantada
     */
    public int getLineaCantada()
    {
        return lineaCantada;
    }


    /**
     * @param lineaCantada the lineaCantada to set
     */
    public void setLineaCantada(int lineaCantada)
    {
        this.lineaCantada = lineaCantada;
    }


    /**
     * @return the bingo
     */
    public boolean isBingo()
    {
        return bingo;
    }


    /**
     * @param bingo the bingo to set
     */
    public void setBingo(boolean bingo)
    {
        this.bingo = bingo;
    }


    /**
     * Genera las líneas del cartón en función de si el número aleatorio está entre 1 y 50 (línea 1) 
     * o si está entre 51 y 99 (línea 2). También comprueba si el número está repetido
     * @param esLinea1
     * @param arrayLinea
     * @return
     */
    public int[] generarLinea(int linea, int[] arrayLinea) {
        int numLinea, posicion=0;
        boolean noRepetido = false;
        
        do {
            if(linea == 1) {
                numLinea = (int)(Math.random()*50+1);
            }
            else {
                numLinea = (int)(51 + Math.random()*(99-51+1));
            }
        
            for(int i=0; i<arrayLinea.length; i++) {
                if(numLinea != arrayLinea[i]) {
                    noRepetido = true;
                }
                else {
                    noRepetido = false;
                    break;
                }
            }
            if(noRepetido) {
                arrayLinea[posicion] = numLinea;
                posicion++;
            }
        } while(arrayLinea[4] == 0);
        return arrayLinea;
    }
    
    
    /**
     * Muestra los números de la línea que se le pase como parámetro
     * @param linea
     */
    public void mostrarLinea(int[]linea) {
        for(int i=0; i<linea.length; i++) {
            System.out.print(linea[i] + "|");
        }
    }
    
    
    /**
     * Muestra los datos de un cartón
     */
    public void mostrarCarton() {
        System.out.println("\n-------------------------");
        System.out.print("Número de cartón: " + this.numCarton + "\nLínea 1: ");  
        mostrarLinea(this.linea1); 
        System.out.print("\nLínea 2: ");
        mostrarLinea(this.linea2);
        System.out.println("\n-------------------------");
    }
    
    
    /**
     * Comprueba el número de la bola saliente en la línea correspondiente, aumentando el contador
     * de números tachados. Cuando se tacha una línea, aumenta el contador de línea cantada.
     * @param numBola
     */
    public boolean comprobarCarton(int numBola, boolean lineaPremiada) {
        boolean linea = false;
        for(int i=0; i<this.linea1.length; i++) {
            if(numBola>=1 && numBola<=50) {
                if(numBola == this.linea1[i]) {
                    this.linea1[i] = -1;
                    this.tachados1 ++;
                    if(this.tachados1 == 5) {
                        this.lineaCantada ++;
                    }
                }
            }
            else if(numBola>=51 && numBola<=99) {
                if(numBola == this.linea2[i]) {
                    this.linea2[i] = -1;
                    this.tachados2 ++;
                    if(this.tachados2 == 5) {
                        this.lineaCantada ++;
                    }
                }
            }
        }
        if(this.lineaCantada==2) {
            System.out.print("\nCartón número: " + this.numCarton);
            System.out.println(" --> ¡¡BINGO!! \nEl bingo es correcto");
            this.bingo = true;
        }
        else if(!lineaPremiada) {
            if ((this.tachados1 == 5) || (this.tachados2 == 5)) {
                System.out.print("\n\nCartón número: " + this.numCarton);
                System.out.println(" --> ¡¡LINEA!!");
                linea = true;
                if(this.tachados1 == 5) {
                    System.out.println("La línea 1 es correcta\n");
                }
                else {
                    System.out.println("La línea 2 es correcta");
                }
            }
        }
        return linea;
    }
    
    
    
}
