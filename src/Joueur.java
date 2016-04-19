/**
*@author Medina Alex, Abdelaziz Mathis, Corentin Paul, Chouin Nicolas
*@version 1.0
*/

public class Joueur
{
	private String pseudo, combinaisonPlusForte;
	private int jetons;
	public Carte[] main, combinaison, paire, paire2, brelan, couleur, full, suite, carre, quinte_flush, quinte_flush_royale;
	public boolean couche = false;
	private int petiteBlinde = 10;
    private int grosseBlinde = 20;
    public boolean grosseBlindeBoolean = false;
    public int derniereMise = grosseBlinde;
	
	 /** Constructeur de la classe Joueur avec lecture du parametre pseudo
    *@param pseudo - le pseudo du joueur
	*/
	public Joueur(String pseudo)
	{
		this.pseudo = pseudo;
		jetons = 5000;
		main = new Carte[2];
	}
	
	/** Constructeur de la classe Joueur avec lecture des parametres pseudo et jetons
    *@param pseudo - le pseudo du joueur
    *@param jetons - les jetons du joueur 
    */
	public Joueur(String pseudo, int jetons)
	{
		this.pseudo = pseudo;
		this.jetons = jetons;
	}
	
	 /** Methode seCoucher qui permet au joueur de se retirer de la manche en se couchant
    */
	public void seCoucher()
	{
    		this.couche = true;
	}

	/** Methode quitter qui permet de quitter la table
    */
	public void quitter()
	{
		this.seCoucher();
		Table.supprimerJoueur(this);
	}
	
	/** Methode miser qui permet au joueur de miser un certain nombre de ses jetons
    *@param somme - la somme de la mise
    */	
	public boolean miser(int somme)
	{
		boolean correct=true;
    		
    	if(!couche)
    	{
	   		if(somme == jetons)
	    	{
				System.out.println("Le joueur a mit tapis :" + somme);
				jetons = 0;
				derniereMise=somme;
	    	}
	    		
	    	else if(somme >= 2*derniereMise)
	    	{
				System.out.println("Le joueur a relance a :" + somme);
				jetons = jetons - somme;
				derniereMise=somme;
	    	}
	    
	    	else if(somme >= grosseBlinde)
	    	{
				System.out.println("Le joueur a mise :" + somme);
				jetons = jetons - somme;
				derniereMise=somme;
	    	}

	    	else if (((derniereMise==0 )||(grosseBlindeBoolean))&& somme==0)
	    	{
			System.out.println("le joueur a checke");
			}	
	    	else if(somme == derniereMise)
	    	{
	       			System.out.println("Le joueur a suivi :" + somme);
	       			jetons = jetons - somme;
	       			derniereMise = somme;
	   		}	   		
	   		else 
	   		{
	   			correct=false;
	   			System.out.println("Mise non correct.");
	   		}
	   }
	   return correct;
	}
	
	/** Methode getPseudo accesseur qui permet de recuperer le pseudo du joueur
    *@return pseudo - le pseudo du joueur
    */
	public String getPseudo()
	{
		return pseudo;
	}

	public int getDerniereMise()
	{
		return derniereMise;
	}

	public void setDerniereMise(int jeton)
	{
		derniereMise = jeton;
	}

	public void setCombinaisonPlusForte(String combinaisonPlusForte) 
	{
		this.combinaisonPlusForte= combinaisonPlusForte;
	}

	public String getCombinaisonPlusForte() //pre condition: setCombinaisonPlusForte() doit avoir été efectué
	{
		return combinaisonPlusForte;
	}
	
	/** Methode getJetons accesseur qui permet de recuperer les jetons du joueur
    *@return jetons - les jetons du joueur
    */
	public int getJetons()
	{
		return jetons;
	}
	
	/** Methode setMain qui permet de modifier la main d'un joueur en lui attribuant deux nouvelles cartes
    *@param carte1 - la premiere carte en main
    *@param carte2 - la deuxieme carte en main
    */
	public void setMain(Carte carte1, Carte carte2)
	{
		main[0] = carte1;
		main[1] = carte2;
	}
	
	/** Methode toString redefinition de la methode toString()
    */
	public String toString()
	{
		String s = pseudo +" avec " +jetons+ " jetons et les cartes : " +main[0]+", " +main[1];
		return s;
	}
	
	/** Methode testCombinaison qui permet de tester la combinaison du joueur
    *@param comb - les cartes sur la table (la riviere)
    *@return v - la valeur de la meilleur combinaison
    */
	public int testCombinaison(Carte[] comb)
	{
		boolean couleurB =false, suiteB=false; 
		Carte[] combinaisonSansDoublon;
		paire  = new Carte[2];
		paire2 = new Carte[2];
		brelan = new Carte[3];
		couleur= new Carte[5];
		full   = new Carte[5];
		suite  = new Carte[5];
		carre  = new Carte[4];
		quinte_flush = new Carte[5];
		quinte_flush_royale = new Carte[5];
		combinaison = new Carte[7];
		int z=1,v=0;
		
		for(int i=0; i<5;i++)
		{
			combinaison[i]=comb[i];
		}
		combinaison[5]= main[0];
		combinaison[6]= main[1];

		/*System.out.println(main[0]);
		System.out.println(main[1]);*/

		/*	
		for(int i=0; i<7;i++)
		{
			System.out.println(combinaison[i] );
		}*/
		
		Tri_Par_Valeur(combinaison,0,6);
		//Tri_Par_Couleur(combinaison);
		//Tri_Par_Valeur2();
		for(int i=0; i<7;i++)
		{
			System.out.println(combinaison[i] );
		}

		if(v==0)
		{
			for(int y=0; y<6;y++) // boucle pour voir si il y a une paire
			{
				z = y+1; 
				while(z<7)
				{
					if(combinaison[y].getValeur() == combinaison[z].getValeur())
					{
						v=1;  
						paire[0] = combinaison[y]; 
						paire[1] = combinaison[z];
						paire2[0] = new Carte("aucun",-1);
						paire2[1] = new Carte("aucun",-1);
					}	
					z++;
				}
			}
		}
		else v=-1;
		
		
		if(v==1) // si il y a une paire
		{
			for(int y=0; y<6;y++) // boucle pour voir si il y a une 2eme paire
			{
				z = y+1; 
				while(z<7)
				{
					if(combinaison[y].getValeur() == combinaison[z].getValeur() 
					&& !combinaison[y].equals(paire[0])
					&& !combinaison[z].equals(paire[0])
					&& !combinaison[y].equals(paire[1])
					&& !combinaison[z].equals(paire[1]))

						/*&& combinaison[y].getValeur() != paire[0].getValeur() 
					&& combinaison[z].getValeur() != paire[1].getValeur())*/

					/*&& !combinaison[y].equals(paire[0]) 
					&& !combinaison[z].equals(paire[1]))*/
					{ 
						v=2;  
						paire2[0] = combinaison[y]; 
						paire2[1] = combinaison[z];

						/*System.out.println("\n"+paire[0]);
						System.out.println(paire2[0]);*/
					}	
					z++;
				}
			}	
		}
		else v=-1;

		/*if(v == 2 && paire[0].getValeur() == paire2[0].getValeur())
		{
			carre[2] = paire[0];
			carre[3] = paire[1];
			carre[0] = paire2[0];
			carre[1] = paire2[1];
			v=7 ;//carre
			System.out.println("--------------------->>>>>carre");
		}*/

			
			

		if(v == 1 || v == 2)
		{
			for(int y=0; y<6;y++) // boucle pour voir si il y a un brelan ou un full
			{
				if((combinaison[y].getValeur() == paire[0].getValeur() // si il y a une carte similaire a la paire1
				&&!combinaison[y].equals(paire[0]) && !combinaison[y].equals(paire[1]))
				||( combinaison[y].getValeur() == paire2[0].getValeur() // si il y a une carte similaire a la paire2
				&&!combinaison[y].equals(paire2[0]) && !combinaison[y].equals(paire2[1]))  )
				{
					if(v==1) // si il y a une paire, ce sera un brelan
					{
					v=3; //brelan
					brelan[0] = paire[0];
					brelan[1] = paire[1];
					brelan[2] = combinaison[y];						
					}

					else if(v==2) // si il y a 2 paires, ce sera un full
					{
					v=6; //full
					full[0] = paire[0];
					full[1] = paire[1];
					full[2] = combinaison[y];
					full[3] = paire2[0];
					full[4] = paire2[1];
					}		
				}
			}
		}
		else v=-1;	

		/*if(v==3 || v==4)
		{
			for(int y=0; y<=6;y++) // boucle pour voir si il y a un carre
			{
				if(combinaison[y].getValeur() == brelan[0].getValeur() 
				&& !combinaison[y].equals(brelan[0]) 
				&& !combinaison[y].equals(brelan[1])
				&& !combinaison[y].equals(brelan[2]))

					combinaison[y].getValeur() == brelan[0].getValeur() 
				&& combinaison[y].getCouleur() != brelan[0].getCouleur() 
				&& combinaison[y].getCouleur() != brelan[1].getCouleur()
				&& combinaison[y].getCouleur() != brelan[2].getCouleur())
				{
					carre[0] = brelan[0];
					carre[1] = brelan[1];
					carre[2] = brelan[2];
					carre[3] = combinaison[y];
					v=7 ;//carre
				}
			}	
		}*/

		for(int j=0;j<=3;j++)
			{
				if(combinaison[j].getValeur() == combinaison[j+1].getValeur() && combinaison[j].getValeur() == combinaison[j+2].getValeur() 
				&& combinaison[j].getValeur() == combinaison[j+3].getValeur())
				{
					carre[0] = combinaison[j];
					carre[1] = combinaison[j+1];
					carre[2] = combinaison[j+2];
					carre[3] = combinaison[j+3];
					v=7 ;//carre
					//System.out.println("--------------------->>>>>carre");
				}
			}

		if (v<=3) // début de la detection de la suite si la valur courante est inférieur à 3
		{
			int y=0,a=1, nb =7, nbTour,d=0;
			if (v==2 || v==3) // s'il y a 2 paire ou un brelan
			{
				nb=5;
				combinaisonSansDoublon = new Carte[nb]; // on créer un tableau de 5 valeurs, 2 vont etre exclue
				nbTour=nb+1; // on définie le nombre de tour à 6
				combinaisonSansDoublon[nb-1] = new Carte("aucun", -1);
			}
			else if(v==1) // sinon s'il y a 1 paire
			{
				nb=6;
				combinaisonSansDoublon = new Carte[nb]; // on créer un tableau de 6 valeurs, 1 va etre exclue
				nbTour = nb+1;// on définie le nombre de tour à 7
			} 
			else 
			{
				combinaisonSansDoublon = new Carte[nb];
				nbTour = nb; // on définie le nombre de tour à 7
			}

			//System.out.println("\nnb = "+nb +"et v = "+v+"\n");

			combinaisonSansDoublon[0] = combinaison[0];
			if (v==-1) 
			{
				for (int i=1;i<7 ;i++)
				{
					combinaisonSansDoublon[i]=combinaison[i];	
				}
			}
			while(y<=nbTour && v != -1 && a != nb)  // tant que on a pas réalisé le nombre de tour définie, qu'il y a au moins 1 paire ou un brelan et que le tableau n'est pas remplie
			{
				if(combinaison[y].getValeur() == combinaison[y+1].getValeur() )// s'il 2 cartes qui se suivent ont la même valeur
				{
					y += 2; // on saute la prochaine carte
					d++;
				}
				else // sinon
				{
					y++;// on passe à la prochaine carte
				} 
				

				//System.out.println("a= "+a+"y= " + y + " d= "+d);

				if (y>nbTour-1 && v == 2 && d==3 && a>=3/* && combinaisonSansDoublon[a].equals(null)*/)
				{
					if(y==6 &&combinaison[y].getValeur() != combinaisonSansDoublon[a-1].getValeur())
					{
						combinaisonSansDoublon[a] = combinaison[y];
						a++;
						combinaisonSansDoublon[a] = new Carte("aucun", -1);	
						y=42;
						/*System.out.println("---------------------->yoloooooooooooooooooooo");
						Scanner scan=new Scanner(System.in);
						scan.next();*/
					}
					else 
					{
						//System.out.println("---------------------->dans le if");
						combinaisonSansDoublon[a] = new Carte("aucun", -1);	
						y=42;
						/*Scanner scan=new Scanner(System.in);
						scan.next();*/
					}
				}
				else combinaisonSansDoublon[a] = combinaison[y]; // on met notre carte dans notre nouveau tableau
				a++; //on incrémente a
			}
			
			/*for (int i=0;i<nb ;i++ ) {
				System.out.println(combinaisonSansDoublon[i]);
			}*/
				
			for(int i=0;i<nb-4;i++) // on fait une boucle pour vérifier à partir des nb-4 cartes
			{
				if((combinaisonSansDoublon[i].getValeur()== combinaisonSansDoublon[i+1].getValeur()-1 && combinaisonSansDoublon[i].getValeur()== combinaisonSansDoublon[i+2].getValeur()-2 
				&& combinaisonSansDoublon[i].getValeur() == combinaisonSansDoublon[i+3].getValeur()-3 && combinaisonSansDoublon[i].getValeur()== combinaisonSansDoublon[i+4].getValeur()-4) 
				||(combinaisonSansDoublon[i].getValeur() == combinaisonSansDoublon[i+1].getValeur()-1 && combinaisonSansDoublon[i].getValeur()== combinaisonSansDoublon[i+2].getValeur()-2 
				&& combinaisonSansDoublon[i].getValeur() == combinaisonSansDoublon[i+3].getValeur()-3 && combinaisonSansDoublon[i].getValeur()== combinaisonSansDoublon[nb-1].getValeur()-12)) // si la suite commence par un As
				{
					v =4; // Suite
					suite[0] = combinaisonSansDoublon[i]; // on met les cartes dans notre tableau suite
					suite[1] = combinaisonSansDoublon[i+1];
					suite[2] = combinaisonSansDoublon[i+2];
					suite[3] = combinaisonSansDoublon[i+3];
					suite[4] = combinaisonSansDoublon[i+4];
					suiteB = true; // on met le booléan de la suite à true
				} 
			}
		}

		if(v<=4)
		{
			Tri_Par_Couleur(); // On trie les cartes par couleur pour identifier plus facilement la couleur
			for(int i=0;i<3;i++) // on fait une boucle pour vérifier à partir des 3 cartes
			{
				if(combinaison[i].getCouleur() == combinaison[i+1].getCouleur() && combinaison[i].getCouleur()== combinaison[i+2].getCouleur()  
				&& combinaison[i].getCouleur() == combinaison[i+3].getCouleur() && combinaison[i].getCouleur()== combinaison[i+4].getCouleur()) // on vérifie si les cartes ont la même couleur
				{
				v=5; // Couleur 
				couleur[0] = combinaison[i]; // on met les cartes dans notre tableau couleur
				couleur[1] = combinaison[i+1];
				couleur[2] = combinaison[i+2];
				couleur[3] = combinaison[i+3];
				couleur[4] = combinaison[i+4];
				couleurB=true; // on met le booléan de la couleur à true
				}
			}
			Tri_Par_Valeur(combinaison,0,6); // On retrie les cartes selon leurs valeur
			//Tri_Par_Valeur2();
		}

		if(couleurB && suiteB && couleur[0].equals(suite[0]) && couleur[4].equals(suite[4])) // s'il y a une couleur et une suite
		{
			v=8;
			for(int i=0;i<5;i++)
			{
				quinte_flush[i] = suite[i];		
			}
		}	

		if(couleurB && suiteB && couleur[0].equals(suite[0]) && couleur[4].equals(suite[4]) && suite[4].getValeur() == 14)
		{
			v=9;
			for(int i=0;i<5;i++)
			{
				quinte_flush_royale[i] = suite[i];		
			}
		}
		return v;		
	}

	/** Methode carteHaute qui permet de connaitre si le joueur teste possede une meilleur combinaison que le joueur precedent
    *@param j - le joueur dont la combinaison est testee par rapport a celle du gagnant
    *@param v - la valeur de combinaison des joueurs testee
    *@return true - si la combinaison du gagnant est superieure a celle testee
    *@return false - si la combinaison du gagnant est superieure a celle testee
    */
	public boolean carteHaute(Joueur j, int v)
	{
		boolean plusHaut = false;
		switch(v)
		{
			case 1:
			case 2:
			if(this.paire[0].getValeur() > j.paire[0].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.paire[0].getValeur() == j.paire[0].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;


			case 3:
			if(this.brelan[0].getValeur() > j.brelan[0].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.brelan[0].getValeur() == j.brelan[0].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;
			
			case 4:
			if(this.suite[0].getValeur() > j.suite[0].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.suite[0].getValeur() == j.suite[0].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;

			case 5:
			if(this.couleur[4].getValeur() > j.couleur[4].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.couleur[4].getValeur() == j.couleur[4].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;

			case 6:
			if(this.full[0].getValeur() > j.full[0].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.full[0].getValeur() == j.full[0].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;

			case 7:
			if(this.carre[0].getValeur() > j.carre[0].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.carre[0].getValeur() == j.carre[0].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;

			case 8:
			if(this.quinte_flush[0].getValeur() > j.quinte_flush[0].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.quinte_flush[0].getValeur() == j.quinte_flush[0].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;

			case 9:
			if(this.quinte_flush_royale[0].getValeur() > j.quinte_flush_royale[0].getValeur()) plusHaut = true; // la plus grande des paires 
			else plusHaut = false;

			if(this.quinte_flush_royale[0].getValeur() == j.quinte_flush_royale[0].getValeur() 
			&& this.combinaison[6].getValeur() >= j.combinaison[6].getValeur()) plusHaut = true; // la plus grande des cartes 
			break;
		}
		return plusHaut;
	}
	
	 /** Methode equals redefinition de la methode public boolean equals(Object o)
    *@return true si les 2 objets sont egaux, sinon false
	*/
	public boolean equals(Object o)
	{
		if(o instanceof Joueur)
		{
			Joueur j = (Joueur) o;
			if(j.pseudo == this.pseudo)
			{
				return true;
			}
		}
		return false;
	}

	/** Methode Tri_Rapide_Par_valeur qui permet de trier une combinaison parmis les 2 cartes d'un joueur et les 5 de la table dans l'ordre croissant des valeurs
    *@param combinaison - la combinaison testee du joueur
    *@param gauche - la carte de gauche
    *@param droite - la carte de droite
    */
	public void Tri_Par_Valeur(Carte[] combinaison, int gauche, int droite)
	{
	    int i, pivot;
	    Carte tmp;
	    
	    if (droite>gauche)
	    {
	        pivot=(gauche+droite)/2;
	        tmp=combinaison[gauche];
	        combinaison[gauche]=combinaison[pivot];
	        combinaison[pivot]=tmp;
	        pivot=gauche;
	        for(i=gauche+1;i<=droite;i++)
	        {
	            if (combinaison[i].getValeur()<combinaison[gauche].getValeur())
	            {
	                pivot=pivot+1;
	                tmp=combinaison[i];
	                combinaison[i]=combinaison[pivot];
	                combinaison[pivot]=tmp;
	            }
	        }
	        tmp=combinaison[gauche];
	        combinaison[gauche]=combinaison[pivot];
	        combinaison[pivot]=tmp;
	        Tri_Par_Valeur(combinaison,gauche,pivot-1);
	        Tri_Par_Valeur(combinaison,pivot+1,droite);
	    }
	}

	public void Tri_Par_Valeur2()
	{
		Carte tmp;
		for(int i=0; i<6;i++) // 
		{
			if(this.combinaison[i].getValeur() > this.combinaison[i+1].getValeur())
			{
				tmp = combinaison[i];
				combinaison[i] = combinaison[i+1];
				combinaison[i+1] = tmp;
			}
		}
	}

	/** Methode Tri_Rapide_Par_Couleur qui permet de trier une combinaison parmis les 2 cartes d'un joueur et les 5 de la table par rapport a leurs couleur
    */
	public void Tri_Par_Couleur() 
	{
		int z=1;
		Carte tmp;
		for(int i=0; i<6;i++) // 
		{
			z = i+1; 
			while(z<7)
			{
				if(this.combinaison[i].getCouleur().equals(this.combinaison[z].getCouleur()))
				{
					tmp = combinaison[i+1];
					combinaison[i+1] = combinaison[z];
					combinaison[z] = tmp;
				}
				z++;
			}
		}
		/*for(int i=0; i<7;i++)
		{
			System.out.println(combinaison[i] );
		}*/
	}
}