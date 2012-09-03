import javax.swing.*;
import java.awt.*;

public class SemaforoGUI{

	private JFrame framePrincipal = new JFrame("Semaforo Adaptativo! :3");
	private JLabel[] labels = new JLabel[12]; 
	private JPanel[] panels = new JPanel[4];
	private Color[] colores = {Color.green,Color.yellow,Color.red};

	public SemaforoGUI(){
		//Definir el frame
		//Parametros(x donde aparece,y donde aparece,tamanio x,tamanio y)
		framePrincipal.setBounds(0,0,500,500);
		//Crear todos los JLabels y JPanels
		labels[0] = new JLabel();
		labels[1] = new JLabel();
		labels[2] = new JLabel();
		labels[3] = new JLabel();
		labels[4] = new JLabel();
		labels[5] = new JLabel();
		labels[6] = new JLabel();
		labels[7] = new JLabel();
		labels[8] = new JLabel();
		labels[9] = new JLabel();
		labels[10] = new JLabel();
		labels[11] = new JLabel();
		//Container con el contenido del frame
		Container layout = framePrincipal.getContentPane();
		//(y,x)
		layout.setLayout(new GridLayout(3,4));
		layout.add(labels[0]);
		layout.add(labels[1]);
		layout.add(labels[2]);
		layout.add(labels[3]);
		layout.add(labels[4]);
		layout.add(labels[5]);
		layout.add(labels[6]);
		layout.add(labels[7]);
		layout.add(labels[8]);
		layout.add(labels[9]);
		layout.add(labels[10]);
		layout.add(labels[11]);

		framePrincipal.setDefaultCloseOperation(framePrincipal.EXIT_ON_CLOSE);
		createContenidoLabels();
		framePrincipal.setVisible(true);

		/*
		panels[0] = new JPanel();
		panels[1] = new JPanel();
		panels[2] = new JPanel();
		panels[3] = new JPanel();
		*/
	}

	public void createContenidoLabels(){
		//Labels nombre de los semaforos
		labels[0].setText("Semaforo A");
		labels[1].setText("Semaforo B");
		labels[2].setText("Semaforo C");
		labels[3].setText("Semaforo D");

		//Labels con los colores
		labels[4].setOpaque(true);
		labels[4].setBackground(colores[2]);
		labels[5].setOpaque(true);
		labels[5].setBackground(colores[2]);
		labels[6].setOpaque(true);
		labels[6].setBackground(colores[2]);
		labels[7].setOpaque(true);
		labels[7].setBackground(colores[2]);

		//Numero de carros
		labels[8].setText("Carros: 100");
		labels[9].setText("Carros: 100");
		labels[10].setText("Carros: 100");
		labels[11].setText("Carros: 100");
		/*//Layout null para acomodarlo a placer
		panels[0].setLayout(null);
		
		//Panel con los colores del semaforo
		panels[2].setLayout(null);
		panels[2].setLocation(0,50);
		panels[2].setSize(700,50);
		//panels[2].setBackground(Color.red);
		panels[0].add(panels[2]);

		//Panel que contendra los labels de los identificadores de los semaforos
		panels[1].setLayout(null);
		panels[1].setLocation(0,0);
		panels[1].setSize(700,50);
		panels[0].add(panels[1]);
		
		//Contenidos del panel[1]
		//Labels del panel[1]
		labels[0].setLocation(62,0);
		labels[0].setSize(125,50);
		labels[0].setHorizontalAlignment(0);
		labels[0].setText("Semaforo A");
		panels[1].add(labels[0]);

		labels[1].setLocation(187,0);
		labels[1].setSize(125,50);
		labels[1].setHorizontalAlignment(0);
		labels[1].setText("Semaforo B");
		panels[1].add(labels[1]);

		labels[2].setLocation(312,0);
		labels[2].setSize(125,50);
		labels[2].setHorizontalAlignment(0);
		labels[2].setText("Semaforo C");
		panels[1].add(labels[2]);

		labels[3].setLocation(437,0);
		labels[3].setSize(125,50);
		labels[3].setHorizontalAlignment(0);
		labels[3].setText("Semaforo D");
		panels[1].add(labels[3]);

		//Contenidos del panel[2]
		//Labels del panel[2]
		labels[4].setLocation(25,100);
		labels[4].setSize(125,50);
		labels[4].setText("asdsafsdkjf");
		//labels[4].setOpaque(true);
		//labels[4].setBackground(Color.green);
		labels[4].setHorizontalAlignment(0);
		panels[2].add(labels[4]);

		labels[5].setLocation(187,50);
		labels[5].setSize(125,50);
		labels[5].setHorizontalAlignment(0);
		panels[2].add(labels[5]);

		labels[6].setLocation(312,50);
		labels[6].setSize(125,50);
		labels[6].setHorizontalAlignment(0);
		panels[2].add(labels[6]);

		labels[7].setLocation(437,50);
		labels[7].setSize(125,50);
		labels[7].setHorizontalAlignment(0);
		panels[2].add(labels[7]);

		return panels[0];*/
	}

	public void setColorSemaforo(int semaforo,int color){
		labels[semaforo+4].setBackground(colores[color]);
	}

	public void setText(int semaforo,int carros){
		labels[semaforo + 8].setText("Carros: "+carros);
	}
}