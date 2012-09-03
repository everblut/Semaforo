public class HiloSemaforo extends Thread{

    //Poner prints para ver que funcione
    //Quitar los tiempos de quitar carros,eso debo mandarlo al otro thread
    //Probar que funcione :v

    private Semaforo[] semaforosCrucero = new Semaforo[4];
    private boolean estadoInicial = true;
    private int semaforo;
    private long startTimeEstado,startTimeCarro,finishTimeEstado,finishTimeCarro,diferenciaEstado,diferenciaCarro;
    private Pop popThread = new Pop();
    //Vinculo con la GUI
    private SemaforoGUI gui;

    public void setHilo(Semaforo semaforoA,Semaforo semaforoB,Semaforo semaforoC,Semaforo semaforoD,SemaforoGUI gui){
	this.gui = gui;
	semaforosCrucero[0] = semaforoA;
	semaforosCrucero[1] = semaforoB;
	semaforosCrucero[2] = semaforoC;
	semaforosCrucero[3] = semaforoD;
	popThread.setPop(semaforosCrucero[0].getCarril(),semaforosCrucero[1].getCarril(),semaforosCrucero[2].getCarril(),semaforosCrucero[3].getCarril(),this.gui);
	popThread.start();
    }
    
    public void run(){
	try{
	    //Empezamos en el carril con prioridad
	    semaforo = 0;
	    while(true){
		//Si es el de prioridad,dar verde
		if(semaforosCrucero[semaforo].getCarril().getPrioridad()){
		    //cambiar a verde en logica y GUI
		    semaforosCrucero[semaforo].changeState();
		    gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
		    //Start popping cars
		    popThread.setCrucero(semaforo);
		    popThread.setQuitarVehiculos(true);
		    //debugging
		    System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState()+" con "+semaforosCrucero[semaforo].getCarril().getNumVehiculos());
		    //Tomar los tiempos iniciales para el cambio de estado y para el paso del vehiculo(3000 -> 3 segundos)
		    startTimeEstado = System.currentTimeMillis();
		    startTimeCarro = startTimeEstado;
		    //ciclo del semaforo
		    while(true){
			//checar si ya paso el tiempo minimo y ya no hay carros
			finishTimeEstado = System.currentTimeMillis();
 			finishTimeCarro = finishTimeEstado;
			//Diferencias de tiempo
			diferenciaEstado = finishTimeEstado - startTimeEstado;
			diferenciaCarro = finishTimeCarro - startTimeCarro;
			if(diferenciaEstado >= semaforosCrucero[semaforo].getMinTime() && semaforosCrucero[semaforo].getCarril().getNumVehiculos() == 0){
			    //cambiar a ambar en logica y GUI
			    semaforosCrucero[semaforo].changeState();
			    gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
			    //Que ya no pasen carros :D
			    popThread.setQuitarVehiculos(false);
			    //debugging
			    System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
			    //poner print para ver que cambie
			    //3 segundos antes del rojo
			    sleep(3000);
			    //cambiar a rojo en logica y GUI
			    semaforosCrucero[semaforo].changeState();
			    gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
			    //debugging
			    System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
			    //cambiar al siguiente crucero
			    semaforo++;
			    popThread.setCrucero(semaforo);
			    break;
			}//end if
			//Si ya se llego al tiempo maximo o ya no hay carros
			if(diferenciaEstado >= semaforosCrucero[semaforo].getMaxTime() || semaforosCrucero[semaforo].getCarril().getNumVehiculos() == 0){
			    //Cambiar a ambar
			    semaforosCrucero[semaforo].changeState();
			    gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
			    //Detener vehiculos
			    popThread.setQuitarVehiculos(false);
			    //debugging
			    System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
			    //3 segundos de ambar
			    sleep(3000);
			    //cambiar a rojo
			    semaforosCrucero[semaforo].changeState();
			    gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
			    //debugging
			    System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
			    //cambiar al siguiente crucero
			    semaforo++;
			    popThread.setCrucero(semaforo);
			    break;
			}
		    }//end while true
		}
		//Semaforos que no son los de prioridad(B,C,D)
		else{
		    while(true){
			//mientras checas los carriles no prioritarios
			while(semaforo < 4){
			    //Checar si tienen vehiculos[Si no hay vehiculos,pasa al siguiente carril]
			    if(semaforosCrucero[semaforo].getCarril().getNumVehiculos() == 0){
				semaforo++;
			    }//end if
			    //Si hay carros,salgo del while < 4 para empezar el ciclo del semaforo
			    else{
				break;
			    }
			}//end while
			//Suponiendo que sale del ciclo y semaforo fue igual a 4,es decir,ningun carril tenia carro,regreso a mi carril con prioridad
			if(semaforo == 4){
			    break;
			}//end if
			//Empieza el ciclo del semaforo
			//cambiamos a verde
			semaforosCrucero[semaforo].changeState();
			gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
			//pasar carros :D
			popThread.setCrucero(semaforo);
			popThread.setQuitarVehiculos(true);
			//debugging
			System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState()+" con "+semaforosCrucero[semaforo].getCarril().getNumVehiculos());
			//Tomamos los tiempos de inicio.
			startTimeEstado = System.currentTimeMillis();
			while(true){	
			    finishTimeEstado = System.currentTimeMillis();
			    diferenciaEstado = finishTimeEstado - startTimeEstado;
			    //Si ya paso el tiempo minimo y ya no hay carros
			    if(diferenciaEstado >= semaforosCrucero[semaforo].getMinTime() && semaforosCrucero[semaforo].getCarril().getNumVehiculos() == 0){
				//cambiar a ambar
				semaforosCrucero[semaforo].changeState();
				gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
				//no pasar carros :D
				popThread.setQuitarVehiculos(false);
				//debugging
				System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
				//3 segundos de ambar
				sleep(3000);
				//cambiar a rojo
				semaforosCrucero[semaforo].changeState();
				gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
				//debugging
				System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
				//ir al siguiente crucero
				semaforo++;
				popThread.setCrucero(semaforo);
				break;
			    }//end if
			    //If de que si ya se llego al tiempo maximo o que si ya no hay carros
			    if(diferenciaEstado >= semaforosCrucero[semaforo].getMaxTime() || semaforosCrucero[semaforo].getCarril().getNumVehiculos() == 0){
				//cambiar a ambar
				semaforosCrucero[semaforo].changeState();
				gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
				//no pasar carros :D
				popThread.setQuitarVehiculos(false);
				//debugging
				System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
				//3 segundo de ambar
				sleep(3000);
				//ir a rojo
				semaforosCrucero[semaforo].changeState();
				gui.setColorSemaforo(semaforo,semaforosCrucero[semaforo].getActualState());
				//debugging
				System.out.println("Semaforo: " + semaforo +" esta en: "+ semaforosCrucero[semaforo].getState());
				//ir al siguiente crucero
				semaforo++;
				popThread.setCrucero(semaforo);
				break;
			    }
			}//end 2nd while true
			//El semaforo fue el ultimo,regresar al semaforo con prioridad
			if(semaforo >= 4){
			    semaforo = 0;
			    break;
			}
		    }//end 1st while true
		}//end else   
	    }//end global loop
	}catch(Exception e){
	    System.out.println("Exception"+e.getMessage());
	}
    }	
}
