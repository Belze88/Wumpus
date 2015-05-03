package ia;

import java.util.Set;
import java.util.TreeSet;




public class KnowledgeBase {
	
	
	
	private Set<veriteCase> [][] verites;
	private Integer[] nbCases;
	
	public enum veriteCase {
		  Visite,
		  Brise,
		  Odeur,
		  Puit,
		  Puit_proba,
		  Puit_nonproba,
		  Tresor,	
		  Wumpus,
		  Wumpus_proba,
		  Wumpus_nonproba;
		}
	
	public KnowledgeBase(Integer[] nbCases){
		this.nbCases = nbCases;
		verites = new Set[nbCases[0]][nbCases[1]];
		
		// initialisation des ensemble de verit� des cases
		for (int i=0;i<nbCases[0];i++){
			for (int j=0;j<nbCases[1];j++){
				verites[i][j] = new TreeSet();
			}
		}
		
	}
	
	// ajout de verit�s dans la KB
	public int tell(Integer[] position,veriteCase verite){

		int res = -1;
		Set<veriteCase> ceSet;// =new TreeSet();
		// r�sultat d'ajout : 1 ok ,0 non ajout�e (d�j� pr�sent) -1 probleme
				
		if (position[0] < nbCases[0] && position[1] < nbCases[1]){
			ceSet = ((Set)verites[position[0]][position[1]]);
			ceSet.add(verite);
			// contrainte d'int�grit� des verit� par cases implementant les regles
			// Visite -> !Puit_proba, !Wumpus_proba
			if (verite == veriteCase.Visite){
				if (ceSet.contains(veriteCase.Puit_proba)){
					ceSet.remove(veriteCase.Puit_proba);
				}
				if (ceSet.contains(veriteCase.Wumpus_proba)){
					ceSet.remove(veriteCase.Wumpus_proba);
				}
			}
			
			
			
			res = 1;
		}
		else {
			res = -1;
		}
		return res;
	}
	
	// enlevement de verit� � la KB
	public int untell(Integer[] position,veriteCase verite){
		int res = -1;
		Set<veriteCase> ceSet;
		// r�sultat d'ajout : 1 ok ,0 non ajout�e (d�j� pr�sent) -1 probleme
				
		if (position[0] < nbCases[0] && position[1] < nbCases[1]){
			ceSet = ((Set)verites[position[0]][position[1]]);

				if (ceSet.contains(verite)){
					ceSet.remove(verite);
				}
			res = 1;
		}
		else {
			res = -1;
		}
		return res;
	}
	
	// demande � la base de connaissance si cette proposition est vraie pour la case specifi�e
	public boolean ask(Integer[] position,veriteCase verite){
		boolean res = false;
		// set de la case a analyser
		Set<veriteCase> setCase = verites[position[0]][position[1]];
				
		res=setCase.contains(verite);

		return res;
	}
	
	// renvoie la position voisine avec le formalisme
	// 0 en haut,1 a droite,2 en bas, 3 � gauche
	public Integer[] positionVoisine(Integer[] position,int position_voisine){
		Integer[] res = {-1,-1};
		
		System.out.println("i : " + position[0] + " - j : " + position[1]);
		
		if (position_voisine==0){
			if (position[0]>0){
				res[0] = position[0]-1;
				res[1]=position[1];
			}
		}
		else if (position_voisine==1){
			if (position[1]<nbCases[1]-1){
				res[0] = position[0];
				res[1]=position[1]+1;
			}
			
		}
		else if (position_voisine==2){
			if (position[0]<nbCases[0]-1){
				res[0] = position[0]+1;
				res[1]=position[1];
			}
			
		}
		else if (position_voisine==3){
			if (position[1]>0){
				res[0] = position[0];
				res[1]=position[1]-1;
			}
			
		}
		
		
		return res;
	}
	
	// premiere procedure d'inf�rence : 
	// en dur les r�gles d'integrite par case : visite - > !probapuit, ! probawumpus
	// en dur les r�gles sur les cases adjacentes odeur -> wumpus pas loin , brise -> puit pas loin
	// si positionAjout est nul on balaye toute la carte pour faire l'inf�rence, sinon on analyse juste les cases � proximite
	public void inference(Integer[] positionAjout){
		Integer[] voisine = {-1,-1};
		int sens = 0;
		
		if (this.ask(positionAjout, veriteCase.Visite)){
			this.untell(positionAjout, veriteCase.Puit_proba);
			this.untell(positionAjout, veriteCase.Wumpus_proba);
		}
		
		for (sens=0;sens<4;sens++){
			voisine = positionVoisine(positionAjout,sens);
			
			if (voisine[0] !=-1){
				if (this.ask(positionAjout, veriteCase.Brise)){
					if ( !this.ask(voisine, veriteCase.Visite) && !this.ask(voisine, veriteCase.Puit_nonproba)){
						this.tell(voisine, veriteCase.Puit_proba);
					}
				}
				else {
					if ( !this.ask(voisine, veriteCase.Visite)){
						this.untell(voisine, veriteCase.Puit_proba);
						this.tell(voisine, veriteCase.Puit_nonproba);
					}
				}	
				if (this.ask(positionAjout, veriteCase.Odeur)){
					if ( !this.ask(voisine, veriteCase.Visite) && !this.ask(voisine, veriteCase.Wumpus_nonproba)){
						this.tell(voisine, veriteCase.Wumpus_proba);
					}
				}
				else {
					if ( !this.ask(voisine, veriteCase.Visite)){
						this.untell(voisine, veriteCase.Wumpus_proba);
						this.tell(voisine, veriteCase.Wumpus_nonproba);
					}
				}	
			}										
		}	
	}
	

} // fin de la classe KB