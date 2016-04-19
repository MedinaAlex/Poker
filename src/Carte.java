/**
*@author Medina Alex, Abdelaziz Mathis, Corentin Paul, Chouin Nicolas
*@version 1.0
*/
public class Carte
{
	private String couleur;
	private int valeur;

	/** Constructeur de la classe Carte avec lecture des parametres couleur et valeur
	*@param couleur - la couleur de la carte
    *@param valeur - la valeur de la carte
	*/
	public Carte(String couleur, int valeur)
	{
		this.couleur=couleur;
		this.valeur=valeur;
	}
	
	/** Methode getCouleur accesseur qui permet de recuperer la couleur de la carte
	*@return couleur - la couleur de la carte
	*/
	public String getCouleur()
	{
		return couleur;
	}
	
	/** Methode getValeur accesseur qui permet de recuperer la valeur de la carte
	*@return valeur - la valeur de la carte
	*/
	public int getValeur()
	{
		return valeur;
	}
	
	/** Methode toString redefinition de la methode toString()
  	*/
	public String toString()
	{
		String str;
		if (valeur==14){
		str="As de "+couleur;
		}
		else if (valeur==11){
		str="Valet de "+couleur;
		}
		else if (valeur==12){
		str="Dame de "+couleur;
		}
		else if (valeur==13){
		str="Roi de "+couleur;
		}
		else{
		str=valeur+" de "+couleur;
		}
		return str;
	}
	
	/** Methode equals redefinition de la methode public boolean equals(Object o)
	*@return true si les 2 objets sont egaux, sinon false
	*/
	public boolean equals(Object o)
	{
		if(o instanceof Carte)
		{
			Carte c = (Carte) o;
			if(c.couleur == this.couleur && c.valeur == this.valeur)
			{
				return true;
			}
		}
		return false;
	}
}