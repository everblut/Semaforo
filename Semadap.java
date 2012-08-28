public class Semadap{
    
    //Agregar que los automoviles sea random
    //Falta el hilo que agrega carros,el de quitar carros esta hardcoded en el HiloSemaforos,acomodarlo en algun otro lado
    
    public static void main(String args[]){
	
	//Crear los carriles
	Carril CA = new Carril(20);

	//Crear semaforos
	Semaforo SA = new Semaforo(25000,13000,CA);
	HiloSemaforo HSS = new HiloSemaforo();
	HSS.setHilo(SA);
	HSS.start();
    }
}
