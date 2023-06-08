package dominio;

public class DescuentoEdad implements Descuento {

	private static final double DESCUENTO_MENOR_EDAD = 0.05;
	private static final double DESCUENTO_MAYOR_EDAD = 0.10;
	private static final double DESCUENTO_JUBILADO = 0.20;

	public double aplicarDescuento(Usuario usuario, double precio) {

		double descuento = precio;
		int edadUsuario = usuario.getEdad(usuario.getNacimiento());

		if (edadUsuario < 18) {
			descuento = precio - precio * DESCUENTO_MENOR_EDAD;
		} else if (edadUsuario >= 18 && edadUsuario < 60) {
			descuento = precio - precio * DESCUENTO_MAYOR_EDAD;
		} else if (edadUsuario >= 65) {
			descuento = precio - precio * DESCUENTO_JUBILADO;
		}

		return descuento;

	}
}
