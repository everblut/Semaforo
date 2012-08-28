public class HiloSemaforo extends Thread{

    //Cuando se llega a 0 carros ya no cambia a ambar D:

    private Semaforo semaforosCrucero;
    private boolean estadoInicial = true;
    
    public void setHilo(Semaforo SA){
	semaforosCrucero = SA;
    }
    
    public void run(){
	try{
	    long startTimeEstado = System.currentTimeMillis();
	    long startTimeCarro = startTimeEstado;
	    System.out.println("Hay "+semaforosCrucero.getCarros()+" carros");
	    while(true){
		if(estadoInicial){
		    semaforosCrucero.changeState();
		    System.out.println(semaforosCrucero.getState());
		    estadoInicial = false;
		}
		long finishTimeEstado = System.currentTimeMillis();
		long finishTimeCarro = finishTimeEstado;
		long diferenciaEstado = finishTimeEstado - startTimeEstado;
		long diferenciaCarro = finishTimeCarro - startTimeCarro;
		//Cada 3 segundos pasa un carro
		if(diferenciaCarro == 3000 && semaforosCrucero.getCarros() > 0){
		    semaforosCrucero.getCarril().setNumVehiculos(semaforosCrucero.getCarros() - 1);
		    System.out.println("Quite un carro y ahora hay "+semaforosCrucero.getCarros()+" carros");
		    startTimeCarro = System.currentTimeMillis();
		} 
		//Si ya paso el tiempo minimo y ya no hay carros
		if( semaforosCrucero.getMinTime() == diferenciaEstado && semaforosCrucero.getCarros() == 0){
		    semaforosCrucero.changeState();
		    System.out.println(semaforosCrucero.getState());
		    //3 segundos del ambar
		    sleep(3000);
		    //Cambia a rojo
		    semaforosCrucero.changeState();
		    System.out.println(semaforosCrucero.getState());
		    startTimeEstado = System.currentTimeMillis();
		    startTimeCarro = startTimeEstado;
		    estadoInicial = true;
		    //Aqui debe ir algun sleep o algo asi para hacer la pause mientras entran los demas semaforos
		}
		//Si ya se llego al tiempo maximo y aun hay carros cambiar a ambar
		if( semaforosCrucero.getMaxTime() == diferenciaEstado && semaforosCrucero.getCarros() > 0){
		     semaforosCrucero.changeState();
		    System.out.println(semaforosCrucero.getState());
		    //3 segundos del ambar
		    sleep(3000);
		    //Cambia a rojo
		    semaforosCrucero.changeState();
		    System.out.println(semaforosCrucero.getState());
		    startTimeEstado = System.currentTimeMillis();
		    startTimeCarro = startTimeEstado;
		    estadoInicial = true;
		}
	    }
	}catch(InterruptedException e){
	    System.out.println("Exception:"+e.getMessage());
	}
    }
}
