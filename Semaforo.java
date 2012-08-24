public class Semaforo{

    /*Estados del semaforo:
      0->Verde
      1->Ambar
      2->Rojo     
    */
    private int actualState,maxTime,minTime,greenTime,yellowTime,redTime;
    private String state[] = {"Verde","Ambar","Rojo"};

    public Semaforo(){
	//Inicializar el semaforo en rojo
	actualState = 2;
    }

    public void changeState(int state){
	actualState = state;
    }
    
    public String getState(){
	return state[actualState];
    }

}
