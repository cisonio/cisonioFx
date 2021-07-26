package cl.inacap.cisonioApp.commonNodes;

import javafx.beans.Observable;
import javafx.util.Callback;

public class ItemLista<T> {
	private T objeto;
	public T getObjeto() {
		return objeto;
	}
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	private boolean validado;
	public boolean isValidado() {
		return validado;
	}
	public void setValidado(boolean validado) {
		this.validado = validado;
	}
	
	public ItemLista(T item) {
		this.objeto = item;
		validado = false;
	}
		
}
