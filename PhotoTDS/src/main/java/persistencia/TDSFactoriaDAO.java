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
	
	@Override
	public IAdaptadorNotificacionDAO getNotificacionDAO() {
		return AdaptadorNotificacionTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorAlbumDAO getAlbumDAO() {
		return AdaptadorAlbumTDS.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorComentarioDAO getComentarioDAO() {
		return AdaptadorComentarioTDS.getUnicaInstancia();
	}
}
