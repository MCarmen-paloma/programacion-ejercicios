package bingo;

public class Sorteo
{
    /**
     * Genera el sorteo con tantos cartones como longitud tenga el array que se le pasa como parámetro
     * @param cartones
     */
    public static void sorteo(Carton[] cartones) {
        int numBola;
        boolean linea = false, bingo = false, lineaCantada = false, lineaPremiada = false, bingoPremiado = false;
        
        do {
            numBola = (int)(Math.random()*99+1);
            System.out.print("El " + numBola + "; ");
            
            for(int i=0; i<cartones.length; i++) {
                linea = cartones[i].comprobarCarton(numBola, lineaPremiada);
                if(cartones[i].isBingo()) {
                    bingoPremiado = true;
                }
                if(linea) {
                    lineaCantada = true;
                }
            }
            
            if(bingoPremiado) {
                bingo = true;
            }
            if(lineaCantada) {
                lineaPremiada = true;
            }
        } while(!bingo);        
    }


    public static void main(String[] args)
    {
        
        //Sorteo con varios cartones
        Carton [] sorteo1 = new Carton[15];
        
        //Se crean varios cartones aleatoriamente y se muestran
        for(int i=0; i<sorteo1.length; i++) {
            sorteo1[i] = new Carton();
            sorteo1[i].mostrarCarton();
        }
        
        //Se juega el bingo
        sorteo(sorteo1);
        
        
        /*
        //Sorteo con varios cartones con números elegidos
        Carton [] sorteo2 = new Carton[2];
        int c1l1[] = {1,12,23,34,45};
        int c1l2[] = {56,67,78,89,90};
        int c2l1[] = {9,18,27,36,49};
        int c2l2[] = {54,63,72,81,94};
        
        //Se crean los cartones con números elegidos y se muestran
        sorteo2[0] = new Carton(c1l1, c1l2);
        sorteo2[1] = new Carton(c2l1, c2l2);

        for(int i=0; i<sorteo2.length; i++) {
            sorteo2[i].mostrarCarton();
        }
        
        //Se juega el bingo
        sorteo(sorteo2);
        */
        
        
        
        
        
        
        
        
    }

}
