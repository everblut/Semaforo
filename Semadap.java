public class Semadap{
    
    //Agregar que los automoviles sea random
    //Falta el hilo que agrega carros,el de quitar carros esta hardcoded en el HiloSemaforos,acomodarlo en algun otro lado
    
    public static void main(String args[]){

    	//Crear gui
    	SemaforoGUI gui = new SemaforoGUI();

		//Crear los carriles
		Carril carrilA = new Carril(0,true,0,gui);
		Carril carrilB = new Carril(0,false,1,gui);
		Carril carrilC = new Carril(0,false,2,gui);
		Carril carrilD = new Carril(0,false,3,gui);
	
		//Push,hilo que agrega carros a los carriles
		Push push = new Push();
		push.setPush(carrilA,carrilB,carrilC,carrilD,gui);
		//Debo mandarlo hasta abajo que empiece con los otros hilos,modificar que no imprima cuantos carros van LOL
		push.start();
	
		//Crear semaforos
		Semaforo semaforoA = new Semaforo(30000,13000,carrilA);
		Semaforo semaforoB = new Semaforo(15000,10000,carrilB);
		Semaforo semaforoC = new Semaforo(15000,10000,carrilC);
		Semaforo semaforoD = new Semaforo(15000,10000,carrilD);
		HiloSemaforo hiloS = new HiloSemaforo();
		hiloS.setHilo(semaforoA,semaforoB,semaforoC,semaforoD,gui);
		hiloS.start();
	
	}
}
