package persistencia;

public class TDSFactoriaDAO extends FactoriaDAO{

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorFotoDAO getFotoDAO() {
		return AdaptadorFotoTDS.getUnicaInstancia();
	}
}
