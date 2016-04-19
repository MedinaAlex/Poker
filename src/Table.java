/**
*@author Medina Alex, Abdelaziz Mathis, Corentin Paul, Chouin Nicolas
*@version 1.0
*/
import java.util.Random;
import java.util.Scanner;

public class Table
{
	private Carte[] deck, combinaison;
	private int nbtable;
	private static int nbjoueur, carteAdonner, tour;
	private String couleur, riviere="";
	private static Joueur[] joueur;
	
	/** Constructeur de la classe Table avec lecture des parametres nbtable, joueur1 et joueur2
	*@param nbtable - numero de la table
    *@param joueur1 - premier joueur sur la table
    *@param joueur2 - deuxieme joueur sur la table
	*/
	public Table(int nbtable, Joueur joueur1, Joueur joueur2)
	{
		this.nbtable= nbtable;
		deck = new Carte[52];
		nbjoueur = 2;
		tour=0;
		
		joueur = new Joueur[8];
		joueur[0] = joueur1;
		joueur[1] = joueur2;
		
		int cmp=0;
		for(int y=1; y<=4;y++)
		{
			for(int i=2; i<=14; i++)
			{
				if(y==1) couleur = "coeur";
				if(y==2) couleur = "carreau";
				if(y==3) couleur = "pique";
				if(y==4) couleur = "trefle";
				
				deck[cmp] = new Carte(couleur, i);
				cmp++;
			}	
		}
	}
	
	/** Methode afficherDeck qui permet d'afficher les cartes du deck
	*@return s - le string correspondant aux cartes du deck
	*/
	public String afficherDeck()
	{
		String s="";
		for(int i=0 ;i<52;i++)
		{
			s += deck[i]+"\n";
		}
		return s;
	}

	/** Methode afficherJoueurs qui permet d'afficher les joueurs
	*@return s - le string correspondant aux joueus
	*/
	public String afficherJoueurs()
	{
		String s="";
		for(int i=2; i<nbjoueur; i++)
		{
			s +=  ", "+joueur[i] ;
		}
		System.out.print("les joueurs sont : "+joueur[0]+", "+joueur[1]);
		return s;
	}
	
	/** Methode melanger qui permet de melanger le deck avant la distribution des cartes
	*/
	public void melanger()
	{
		Carte tmp;
		
		for(int i=0; i<100; i++)
		{
			Random rd1 = new Random();
			Random rd2 = new Random();
		
			int n1=rd1.nextInt(51);
			int n2=rd2.nextInt(51);
		
			tmp = deck[n1];
			deck[n1] = deck[n2];
			deck[n2] = tmp;
		}
	}

	/** Methode distribuer qui permet de distribuer deux cartes au joueurs
	*/
	public void distribuer()
	{
		this.melanger();
		carteAdonner=0;

		for( int i=0; i<nbjoueur; i++)
		{
			joueur[i].setMain(deck[carteAdonner + i], deck[carteAdonner + i + nbjoueur]);
		}
		carteAdonner += nbjoueur*2;
	}	
	
	/** Methode ajouterJoueur qui permet d'ajouter un joueur a la table
	*@param joueur - le joueur a rajouter sur la table
	*/
	public void ajouterJoueur(Joueur joueur)
	{ 
		if(nbjoueur < 8)
		{
			this.joueur[nbjoueur] = joueur;
			nbjoueur++;	
		}
		else System.out.println("erreur, trop de joueur.");
	}

	/** Methode ajouterJoueur qui permet d'ajouter un joueur a la table
	*@param joueur - le joueur a rajouter sur la table
	*/
	public static void supprimerJoueur(Joueur joueur1)
	{
		for(int i = 0;i < nbjoueur;i++)
		{
			if(joueur[i].equals(joueur1))
			{
				
				for(int j=i;j < nbjoueur-1;j++)
				{
					joueur[j] = joueur[j+1];
				}
				nbjoueur--;
			}
		}
	}
	
	/** Methode flop qui permet d'effectuer le flop, soit tirer les trois premieres cartes sur la table
	*/
	public void flop()
	{
		combinaison = new Carte[5];
		riviere ="Les cartes sur la table sont : ";
		carteAdonner++;
		for(int i=0; i<3;i++)
		{
			riviere += deck[carteAdonner+i]+", ";
			combinaison[i]= deck[carteAdonner+i];
		}
		carteAdonner += 3;

		//System.out.println(riviere);
	}		

	/** Methode turn qui permet d'effectuer le turn, soit tirer une carte supplementaire apres le flop (quatre premieres cartes)
	*/
	public void turn()
	{
		carteAdonner++;
		riviere += deck[carteAdonner]+", ";
		combinaison[3]=deck[carteAdonner];
		carteAdonner++;

		//System.out.println(riviere);
		
	}	
	
	/** Methode river qui permet d'effectuer la river, soit tirer une carte supplementaire apres le turn (cinq cartes sur la table)
	*/
	public void river()
	{
		carteAdonner++;
		riviere += deck[carteAdonner]+".";
		combinaison[4]=deck[carteAdonner];
		carteAdonner++;
		
		//System.out.println(riviere);
		
		/*for(int i=0; i<5;i++)
		{
			System.out.println(riviere);
		}*/
	}
	
	/** Methode gagnant qui permet de connaitre le gagnant de la manche
	*/
	public void gagnant()
	{
		System.out.print("\033[H\033[2J");

		int g=-1,v ,v2=-1 ,nbgagnant=0;
		Joueur gagnant =joueur[0];

		for(int i=0; i< nbjoueur; i++)
		{
			if(!joueur[i].couche)
			{
				System.out.println("\n"+joueur[i].getPseudo() );
				v = joueur[i].testCombinaison(combinaison);

				if(g<v) 
				{
					gagnant = joueur[i];
					g=v;
				}

				if(g==v) 
				{
					if(!gagnant.carteHaute(joueur[i], v))// si le joueur à la même combinaison et à une carte plus haute que le gagnant actuelle
					{
						gagnant = joueur[i];
						g=v;	
					}
				}

				/*for(int  z=0; z<5;z++)
				{
					System.out.println(combinaison[z] );
				}*/

				if(v==-1)
				{
					System.out.println("-->Aucune combinaison");
					joueur[i].setCombinaisonPlusForte("Aucune combinaison");
				}

				if(v==1)
				{
					System.out.println("-->Une Paire");
					joueur[i].setCombinaisonPlusForte("Paire");
				/*	System.out.println(joueur[i].main[0] );
				System.out.println(joueur[i].main[1] );
					for(int  z=0; z<5;z++)
					{
						System.out.println(combinaison[z] );
					}*/
				}

				if(v==2)
				{
					System.out.println("-->Deux Paires");
					joueur[i].setCombinaisonPlusForte("Deux paire");
					/*System.out.println(joueur[i].main[0] );
				System.out.println(joueur[i].main[1] );
					for(int z=0; z<5;z++)
					{
					System.out.println(combinaison[z] );
					}*/
				}

				if(v==3)
				{
					System.out.println("-->Brelan");
					joueur[i].setCombinaisonPlusForte("Brelan");
					/*System.out.println(joueur[i].main[0] );
				System.out.println(joueur[i].main[1] );
					for(int z=0; z<5;z++)
					{
					System.out.println(combinaison[z] );
					}*/
				}

				if(v==4)
				{
					System.out.println("-->Suite");
					joueur[i].setCombinaisonPlusForte("Suite");
				}

				if(v==5)
				{
					System.out.println("-->Couleur");
					joueur[i].setCombinaisonPlusForte("Couleur");
				}

				if(v==6)
				{
					System.out.println("-->Full");
					joueur[i].setCombinaisonPlusForte("Full");
				}

				if(v==7)
				{
					System.out.println("-->Carre");
					joueur[i].setCombinaisonPlusForte("Carre");
					/*System.out.println(joueur[i].main[0] );
				System.out.println(joueur[i].main[1] );
					for(int  z=0; z<5;z++)
					{
						System.out.println(combinaison[z] );
					}*/
				}
				
				if(v==8)
				{
					System.out.println("-->Quinte flush");
					joueur[i].setCombinaisonPlusForte("Quinte flush");
				/*	System.out.println(joueur[i].main[0] );
				System.out.println(joueur[i].main[1] );
					for(int z=0; z<5;z++)
					{
						System.out.println(combinaison[z] );
					}*/
				}

			}
			
		}
		
		System.out.print("gagnant ----> "+ gagnant.getPseudo() +" avec " + gagnant.getCombinaisonPlusForte()+"\n");
	}

	public void tour()
	{
		tour++;
		int joueurCourant=0;
		Scanner sc = new Scanner(System.in);
		while (joueurCourant < nbjoueur) 
		{
			System.out.print("\033[H\033[2J");
			if(joueurCourant==0 && joueur[joueurCourant].getDerniereMise() != 0)
			{
				System.out.println("la derniere mise est de : "+joueur[joueurCourant].getDerniereMise()+" jetons");
			}
			else if(joueur[joueurCourant].getDerniereMise() != 0)
			{
				System.out.println("la derniere mise est de : "+joueur[joueurCourant-1].getDerniereMise()+" jetons");
			}

			if(!joueur[joueurCourant].couche)
			{	
				System.out.println(joueur[joueurCourant]);
				if(!riviere.equals("")) System.out.println(riviere);
				System.out.println("Veuillez choisir votre action parmi : miser, check, se coucher ou quitter.");
				String str = sc.nextLine();

				if(str.equals("miser"))
				{
					System.out.println("Saisir le nombre de jeton à miser");
					int jeton = sc.nextInt();
					//System.out.print("\033[H\033[2J");
					boolean correct = joueur[joueurCourant].miser(jeton);
					if(correct) joueurCourant++;
				}
				else if(str.equals("check"))
				{
					//System.out.print("\033[H\033[2J");
					System.out.println("Vous avez checké");
					if(joueurCourant==0) joueur[joueurCourant].setDerniereMise(0);
					else joueur[joueurCourant].setDerniereMise(joueur[joueurCourant-1].getDerniereMise());
					joueurCourant++;
				}
				else if(str.equals("se coucher"))
				{
					//System.out.print("\033[H\033[2J");
					System.out.println("Vous vous êtes couché");
					joueur[joueurCourant].seCoucher();
					joueurCourant++;
				}
				else if(str.equals("quitter"))
				{
					joueur[joueurCourant].quitter();
					//System.out.print("\033[H\033[2J");
				}
				else 
				{
					//System.out.print("\033[H\033[2J");
					System.out.println("retry or die");
				}
			}
			else joueurCourant++;
		}

		if(tour==1)
		{
			this.flop();
			this.tour();
		}
		else if(tour==2)
		{
			this.turn();
			this.tour();
		}
		else if(tour==3)
		{
			this.river();
			this.tour();
		}
		else this.gagnant();

		
	}
	
	/** Methode toString redefinition de la methode toString()
  	*/
	public String toString()
	{
		String s="";
		for(int i=2; i<nbjoueur; i++)
		{
			s +=  "\n"+joueur[i] ;
		}
		System.out.print("les joueurs sont : \n"+joueur[0]+"\n"+joueur[1]);
		return s;
	}					
}		