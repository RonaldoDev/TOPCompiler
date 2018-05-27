package recovery;

import java.util.*;
import langX;

public class RecoverySet extends HashSet{

	public RecoverySet() {
		super();
	}
	
	public RecoverySet(int t) {
		this.add(new Integer(t));
	}
	
	public boolean contains(int t) {
		return super.contains(new Integer(t));
	}
	
	//faz a união de dois conjuntos
	public RecoverySet union(RecoverySet s) {
		RecoverySet t = null;
		if (s != null) { //se s == null retorna null
			t = (RecoverySet) this.clone();
			t.addAll(s);
		}
		return t; // retorna um terceiro conjunto, sem destruir nenhum #paz
	}
	
	public RecoverySet remove(int n) {
		RecoverySet t = (RecoverySet) this.clone();
		t.remove(new Integer(n));
		return t; // retorna um novo conjunto, sem um dos elementos 
	}
	
	//cria string descrevendo os tokens que pertencem ao conjunto
	public String toString() {
		Iterator it = this.iterator();
		String s = "";
		int k;
		
		while( it.hasNext()) {
			k = ((Integer) it.next()).intValue();
			s += langX.img(x) + " ";
		}
		
		return s;
	}
}
