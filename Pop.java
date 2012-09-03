public class Pop extends Thread{

    private Carril[] carriles = new Carril[4];
    private boolean quitarVehiculos = false;
    private int crucero = 0;
    //Vinculo con la GUI
    private SemaforoGUI gui;

    public void setPop(Carril carrilA,Carril carrilB,Carril carrilC,Carril carrilD,SemaforoGUI gui){
	carriles[0] = carrilA;
	carriles[1] = carrilB;
	carriles[2] = carrilC;
	carriles[3] = carrilD;
	this.gui = gui;
    }
    
    public void setQuitarVehiculos(boolean quitarVehiculos){
	this.quitarVehiculos = quitarVehiculos;
    }
    
    public void setCrucero(int crucero){
	this.crucero = crucero;
    }

    public void run(){
	while(true){
	    long startTime = System.currentTimeMillis();
	    while(quitarVehiculos){
		long finishTime = System.currentTimeMillis();
		long difference = finishTime - startTime;
		if(difference > 3000){
		    carriles[crucero].setNumVehiculos(carriles[crucero].getNumVehiculos() - 1);
		    System.out.println("Quite un carro del carril "+crucero+" y ahora hay "+carriles[crucero].getNumVehiculos());
		    gui.setText(crucero,carriles[crucero].getNumVehiculos());
		    startTime = System.currentTimeMillis();
		}
	    }
	}
    }
    
}
