public class Semadap{
    
    //Agregar que los automoviles sea random
    //Falta el hilo que agrega carros,el de quitar carros esta hardcoded en el HiloSemaforos,acomodarlo en algun otro lado
    
    public static void main(String args[]){
	
	//Crear los carriles
	Carril carrilA = new Carril(35,true);
	Carril carrilB = new Carril(25,false);
	Carril carrilC = new Carril(15,false);
	Carril carrilD = new Carril(20,false);
	
	//Push,hilo que agrega carros a los carriles
	Push push = new Push();
	push.setPush(carrilA,carrilB,carrilC,carrilD);
	//Debo mandarlo hasta abajo que empiece con los otros hilos,modificar que no imprima cuantos carros van LOL
	push.start();
	
	//Crear semaforos
	Semaforo semaforoA = new Semaforo(30000,13000,carrilA);
	Semaforo semaforoB = new Semaforo(15000,10000,carrilB);
	Semaforo semaforoC = new Semaforo(15000,10000,carrilC);
	Semaforo semaforoD = new Semaforo(15000,10000,carrilD);
	HiloSemaforo hiloS = new HiloSemaforo();
	hiloS.setHilo(semaforoA,semaforoB,semaforoC,semaforoD);
	hiloS.start();
	
	}
}
