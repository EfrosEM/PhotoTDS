package dominio;

public class DescuentoPopularidad implements Descuento{

	private static final double SIN_DESCUENTO = 0.0;
	private static final double DESCUENTO_POPULAR = 0.20;
	private static final double DESCUENTO_INFLUENCER = 0.50;
	
	public double aplicarDescuento(Usuario usuario, double precio) {
		
		double descuento = 0.0;
		int likes = usuario.getTotalLikes();
		
		if (likes < 5) {
			descuento = precio - precio * SIN_DESCUENTO;
		} else if (likes >= 5 && likes < 10) {
			descuento = precio - precio * DESCUENTO_POPULAR;
		} else if (likes >= 10) {
			descuento = precio - precio * DESCUENTO_INFLUENCER;
		}
		
		return descuento;
	}
}
