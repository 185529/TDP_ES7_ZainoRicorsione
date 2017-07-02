package it.polito.tdp.zainoricorsione;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Zaino {
	
	private int capienza;
	private List<Pezzo> pezzi;
	
	/**
	 * @param capienza
	 * @param pezzi
	 */
	public Zaino(int capienza) {
		super();
		this.capienza = capienza;
		this.pezzi = new ArrayList<Pezzo>();
	}
	
	/**
	 * 
	 * @param p
	 */
	public void add(Pezzo p){
		if(!pezzi.contains(p))
			pezzi.add(p);
	}
	
	public Set<Pezzo> risolvi(){		// definisce soluzione parziale di livello 0
		
		HashSet<Pezzo> parziale = new HashSet<Pezzo>();
		HashSet<Pezzo> best = new HashSet<Pezzo>();
		
		scegli(parziale, 0, best);
		
		return best;
		
	}
	
	private int costo(Set<Pezzo> parziale){
		
		int r = 0;
		
		for(Pezzo p : parziale){
			r += p.getCosto();
		}
		
		return r;
		
	}
	
private int peso(Set<Pezzo> parziale){
		
		int r = 0;
		
		for(Pezzo p : parziale){
			r += p.getPeso();
		}
		
		return r;
		
	}
	
	private void scegli(Set<Pezzo> parziale, int livello, Set<Pezzo> best){
		
		if(costo(parziale)>costo(best)){
			// soluzione migliore -- salvare
			
			best.clear();
			best.addAll(parziale);
			
			System.out.println(parziale);
		}
		
		for(Pezzo p : pezzi){
			if(!parziale.contains(p)){
				// pezzo p assente -- provo a metterlo se non supera la capacità
				if(peso(parziale)+p.getPeso()<= capienza){
					// prova a mettere p nello zaino e delega al livello successivo, poi togli p
					parziale.add(p);
					scegli(parziale, livello+1, best);
					parziale.remove(p);
				}
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Zaino z = new Zaino(15);
		
		z.add(new Pezzo(12, 4, "Verde"));
		z.add(new Pezzo(2, 2, "Azzurro"));
		z.add(new Pezzo(2, 1, "Salmone"));
		z.add(new Pezzo(4, 10, "Giallo"));
		z.add(new Pezzo(1, 2, "Grigio"));
		
		Set<Pezzo> soluzione = z.risolvi();		// metodo pubblico di interfaccia preliminare che lancia la ricorsione
		
		System.out.println(soluzione);

	}

}
