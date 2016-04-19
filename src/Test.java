public class Test
{
	public static void main(String[] args)
	{	
	
		Joueur j1, j2, j3, j4, j5, j6, j7, j8, j9;
		j1 = new Joueur("Alex");
		j2 = new Joueur("Nicolas");
		j3 = new Joueur("Mathis");
		j4 = new Joueur("Marc");
		j5 = new Joueur("Corentin");
		j6 = new Joueur("Raphael");
		j7 = new Joueur("Mouloud");
		j8 = new Joueur("Jean");
		j9 = new Joueur("Fatima");

		Table t;
		t = new Table(1, j1, j2);
			
		t.ajouterJoueur(j3);
		/*t.ajouterJoueur(j4);
		t.ajouterJoueur(j5);
		t.ajouterJoueur(j6);
		/*t.ajouterJoueur(j7);
		t.ajouterJoueur(j8);
		t.ajouterJoueur(j9);*/
		//for (int i=0;i<100 ;i++ ) {
			
		
		t.distribuer();

		//System.out.println(t);
		
		//t.flop();
		//t.turn();
		//t.river();
		t.tour();
		
		//t.gagnant();
		//}
	}
}