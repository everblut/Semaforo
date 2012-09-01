public class Semaforo{

    /*Estados del semaforo:
      0->Verde
      1->Ambar
      2->Rojo     
    */
    //Tiempos en milisegundos
    private int actualState;
    private long maxTime,minTime,variableTime;
    private Carril carril;
    private String state[] = {"Verde","Ambar","Rojo"};

    public Semaforo(int maxTime,int minTime,Carril carril){
	//Carril correspondiente
	this.carril = carril;
	//Inicializar el semaforo en rojo
	actualState = 2;
	//Set de los tiempos totales del semaforo,los 3000 son los 3 segundos del ambar
	this.maxTime = maxTime - 3000;
	this.minTime = minTime - 3000;
	variableTime = minTime;
    }

    public void changeState(){
	//Cada que se mande llamar el metodo se cambia de estado
	//Si esta en rojo,cambiamos a verde
	if(actualState == 2)
	    actualState = 0;
	//Si esta en otro estado,cambiamos al siguiente
	else
	    actualState++;
    }

    //setters

    public void setVariableTime(long variableTime){
	this.variableTime = variableTime;
    }

    
    //getters
    public String getState(){
	return state[actualState];
    }

    public long getMinTime(){
	return minTime;
    }

    public long getMaxTime(){
	return maxTime;
    }

    public int getCarros(){
	return carril.getNumVehiculos();
    }

    public Carril getCarril(){
	return carril;
    }
}
