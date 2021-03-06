package fenetre;

import wumpus.Contexte;

// import java.util.Double;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import carte.Carte;
import carte.VueCarte;


public class FenetreJeu extends JFrame {
	
	private Contexte ceContexte;
	
	protected double pLargeurPanneauDroite = 0.25;
	protected double pHauteurPanneauHaut = 0.15;
	
	protected double pMargeX = 0.025;
	protected double pMargeY = 0.025;
	
	protected double pLargeurCarte = 0.7;
	protected double pHauteurCarte = 0.65;
	
	protected double pHauteurTexte1 = 0.04;
	protected double pLargeurTexte1 = 0.8;
	
	protected double pHauteurTexte2 = 0.04;
	protected double pLargeurTexte2 = 0.15;
	
	protected double pHauteurBouton1 = 0.05;
	protected double pLargeurBouton1 = 0.25;
	
	protected double pHauteurPanneauBas = 0.0;
	
	
	private JPanel container = new JPanel();
	private Carte carteJeu;
	private VueCarte vueCarte;
	private  JPanel[] tab_jpanel = new JPanel[3];
	private  JButton[] tab_jbutton = new JButton[5];
	private JTextField[] tab_jtextfield = new JTextField[5];
	
	private BorderLayout layout = new BorderLayout();
	private String nomFenetre = "Fenetre jeu libre";
	
	protected jeuListener jeuListener = new jeuListener();
	
	
	public FenetreJeu(Contexte contexte){
		
		super();
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(true);
		super.setTitle(nomFenetre);
		this.ceContexte = contexte;
		super.setBounds(0, 0, ceContexte.LARGEUR_FENETRE, ceContexte.HAUTEUR_FENETRE);
		super.setLocationByPlatform(true);
		
		 //On d�finit le layout � utiliser sur le content pane
		super.setContentPane(container);
	    this.setLayout(layout);
	    layout.setHgap( ((Double)(super.getWidth()*pMargeX)).intValue() );
	    layout.setVgap( ((Double)(super.getHeight()*pMargeY)).intValue() );
	    

	    carteJeu = new Carte( ((Double)(super.getWidth()*pLargeurCarte)).intValue(), ((Double)(super.getHeight()*pHauteurCarte)).intValue(),ceContexte);
	    carteJeu.creerVue();
	    vueCarte = carteJeu.getVueCarte();
	    vueCarte.setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurCarte)).intValue(), ((Double)(super.getHeight()*pHauteurCarte)).intValue() ) );
	    // carteJeu.addKeyListener(new jeuListener());
	    
	    // creation du paneau haut
	    tab_jpanel[0] = new JPanel();
	    tab_jpanel[0].setPreferredSize(new Dimension(super.getWidth()-layout.getHgap(),((Double)(super.getHeight()*pHauteurPanneauHaut)).intValue() ) );
	    tab_jtextfield[0] = new JTextField();
	    tab_jtextfield[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte1)).intValue(), ((Double)(super.getHeight()*pHauteurTexte1)).intValue() ));
	    tab_jtextfield[0].setEditable(false);
	    tab_jtextfield[0].setText("");
	    
	    tab_jtextfield[1] = new JTextField();
	    tab_jtextfield[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue()));
	    tab_jtextfield[1].setEditable(false);
	    tab_jtextfield[1].setText("");
	    
	    tab_jtextfield[2] = new JTextField();
	    tab_jtextfield[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue()));
	    tab_jtextfield[2].setEditable(false);
	    tab_jtextfield[2].setText("");
	    
	    tab_jtextfield[3] = new JTextField();
	    tab_jtextfield[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[3].setEditable(false);
	    tab_jtextfield[3].setText("");
	    
	    tab_jtextfield[4] = new JTextField();
	    tab_jtextfield[4].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurTexte2)).intValue(), ((Double)(super.getHeight()*pHauteurTexte2)).intValue() ));
	    tab_jtextfield[4].setEditable(false);
	    tab_jtextfield[4].setText("Fleches : ");
	    
	    
	    tab_jpanel[0].add(tab_jtextfield[0]);
	    tab_jpanel[0].add(tab_jtextfield[1]);
	    tab_jpanel[0].add(tab_jtextfield[2]);
	    tab_jpanel[0].add(tab_jtextfield[3]);
	    tab_jpanel[0].add(tab_jtextfield[4]);
	    
	    // creation du panneau droit avec les differents boutons de controle
	    tab_jpanel[1] = new JPanel();
	    tab_jpanel[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurPanneauDroite)).intValue(), ((Double)(super.getHeight()*pHauteurCarte)).intValue() ) );
	    
	    tab_jbutton[0] = new JButton();
	    tab_jbutton[0].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[0].setText("Abandonner");
	    tab_jbutton[0].addActionListener(new abandonListener());
	    
	    tab_jbutton[1] = new JButton();
	    tab_jbutton[1].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[1].setText("Reveler");
	    tab_jbutton[1].addActionListener(new revelerListener());
	    
	    tab_jbutton[2] = new JButton();
	    tab_jbutton[2].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[2].setText("Recommencer");
	    tab_jbutton[2].addActionListener(new recommencerListener());
	    
	    tab_jbutton[3] = new JButton();
	    tab_jbutton[3].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[3].setText("Lancer IA");
	    tab_jbutton[3].addActionListener(new lancerIA());
	    
	    tab_jbutton[4] = new JButton();
	    tab_jbutton[4].setPreferredSize(new Dimension(((Double)(super.getWidth()*pLargeurBouton1)).intValue(), ((Double)(super.getHeight()*pHauteurBouton1)).intValue() ));
	    tab_jbutton[4].setText("Retour Menu principal");
	    tab_jbutton[4].addActionListener(new RetourMenuPrincipal());
	    
	    tab_jpanel[1].add(tab_jbutton[0]);
	    tab_jpanel[1].add(tab_jbutton[1]);
	    tab_jpanel[1].add(tab_jbutton[2]);
	    tab_jpanel[1].add(tab_jbutton[3]);
	    tab_jpanel[1].add(tab_jbutton[4]);
	    
	    // creation du panneau bas (bordure basse)
	    tab_jpanel[2] = new JPanel();
	    tab_jpanel[2].setPreferredSize(new Dimension(super.getWidth(),((Double)(super.getHeight()*pHauteurPanneauBas)).intValue() ) );
	    
	    
	    container.add(tab_jpanel[1], layout.EAST);
	    container.add(vueCarte, layout.CENTER);
	    container.add(tab_jpanel[0], layout.NORTH);
	    container.add(tab_jpanel[2], layout.SOUTH);

		
		// super.setJMenuBar(new JMenuBar());
		// super.getJMenuBar().setBounds(0, 0, nbPixelx-20, 20);
	    carteJeu.creerIA(); // creer l'ia du jeu
	    vueCarte.setFocusable(true);
	    vueCarte.setVisible(true);
		super.setVisible(true);
		super.setFocusable(true);
		
		
		super.repaint();
		vueCarte.repaint();
		
		super.addKeyListener(jeuListener);
		moteur();
		super.addComponentListener(new fenetreListener());
		
	}
	
	public void fermerFenetre(){
	    super.dispose();
	}
	
    class RetourMenuPrincipal implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	fermerFenetre();
        	MenuPrincipal fenetre = new MenuPrincipal(ceContexte);
        }
      }
	
	class fenetreListener implements ComponentListener {
		public void componentResized(ComponentEvent e) {
			vueCarte.redimensionner( ((Double)(getWidth()*pLargeurCarte)).intValue(), ((Double)(getHeight()*pHauteurCarte)).intValue());      
		}
		public void componentHidden(ComponentEvent e) {           
		}
		public void componentShown(ComponentEvent e) {           
		}
		public void componentMoved(ComponentEvent e) {           
		}
	}

	// 0 en cours, 1 : termin� par victoire, 2 termin� par mort, 3 termin� par abandon
    
    public void moteur(){
    	
    	tab_jtextfield[0].setText(carteJeu.getMessageAventurier());
    	tab_jtextfield[1].setText(carteJeu.getMessageJeu());
    	tab_jtextfield[2].setText("Tour : " + carteJeu.getTour() + " / " + carteJeu.getMaxTour());
    	tab_jtextfield[3].setText("Score : " + carteJeu.getScore());
    	tab_jtextfield[4].setText("Fleches : " + carteJeu.getNbFleches() + "/" +carteJeu.getNbMaxFleches());

    	// a chaque fois qu'on appelle le moteur on retourne le focus sur la fenetre
    	super.requestFocusInWindow();
    	vueCarte.repaint();
    	super.repaint();   	
    }
    	
    class abandonListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	carteJeu.abandonner();
        	moteur();
        }
      }
    
    class lancerIA implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	carteJeu.simulerIA();
        	moteur();
        }
      }	
    	
        public void actionPerformed(ActionEvent arg0){
        	carteJeu.simulerIA();
        	moteur();
        }
      
    
    class testListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
          moteur();
        }
      }	
    
    class revelerListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        	carteJeu.reveler();
          moteur();
        }
      }
    
    class recommencerListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
        // on n'autorise � recommencer que si le jeu est fini
        if (carteJeu.getEtatJeu() != 0)	{
        	container.remove(vueCarte);
        	layout.removeLayoutComponent(vueCarte);
        	carteJeu = new Carte( ((Double)(getWidth()*pLargeurCarte)).intValue(), ((Double)(getHeight()*pHauteurCarte)).intValue(),ceContexte);
    	    carteJeu.creerVue();
    	    vueCarte = carteJeu.getVueCarte();
        	vueCarte.setPreferredSize(new Dimension(((Double)(getWidth()*pLargeurCarte)).intValue(), ((Double)(getHeight()*pHauteurCarte)).intValue() ) );
        	container.add(vueCarte, layout.CENTER);
        	carteJeu.creerIA(); // creer l'ia du jeu
        	vueCarte.setVisible(true);
        	vueCarte.setFocusable(true);
        	
        	vueCarte.repaint();
        	container.repaint();
        	setContentPane(container);
        	//this.this.setContentPane(container);
        }
        moteur();
        }
      }
    
    class jeuListener implements KeyListener {
    	 public void keyPressed(KeyEvent e) {
    		 boolean actionValide = true;
    	     switch (e.getKeyCode()){
    	        case KeyEvent.VK_UP:
    	        	carteJeu.allerEnHaut();
    	        	break;
    	        case KeyEvent.VK_RIGHT:
    	        	carteJeu.allerADroite();
    	        	break;
    	        case KeyEvent.VK_DOWN:
    	        	carteJeu.allerEnBas();
    	        	break;
    	        case KeyEvent.VK_LEFT:
    	        	carteJeu.allerAGauche();
    	        	break;
    	        case KeyEvent.VK_Z:
    	        	carteJeu.tirerEnHaut();
    	        	break;
    	        case KeyEvent.VK_D:
    	        	carteJeu.tirerADroite();
    	        	break;
    	        case KeyEvent.VK_X:
    	        	carteJeu.tirerEnBas();
    	        	break;
    	        case KeyEvent.VK_Q:
    	        	carteJeu.tirerAGauche();
    	        	break;
    	        case KeyEvent.VK_SPACE:
    	        	carteJeu.ramasser();
    	        	break;		
    	        default:
    	        	actionValide = false;
    	        	break;	
    	        	}
    	    if (actionValide){
    	    	moteur();	
    	    	} 	
    	    }
    	    
    	    public void keyTyped(KeyEvent e) {
    	    }
    		
    	    public void keyReleased(KeyEvent e) {
    	    }
      } 
   
}





