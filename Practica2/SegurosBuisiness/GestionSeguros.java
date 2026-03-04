public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

	private IClientesDAO daoClientes;
	private ISegurosDAO daoSeguros;

	public GestionSeguros(IClientesDAO daoClientes, ISegurosDAO daoSeguros) {
		this.daoClientes = daoClientes;
		this.daoSeguros = daoSeguros;
	}

	@Override
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		return daoClientes.creaCliente(c);
	}

	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = daoClientes.cliente(dni);
		if (cliente == null) {
			return null;
		}
		if (!cliente.getSeguros().isEmpty()) {
			throw new OperacionNoValida("El cliente tiene seguros asociados");
		}
		return daoClientes.eliminaCliente(dni);
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = daoClientes.cliente(dni);
		if (cliente == null) {
			return null;
		}
		Seguro existente = daoSeguros.seguroPorMatricula(s.getMatricula());
		if (existente != null) {
			throw new OperacionNoValida("Ya existe un seguro para esa matricula");
		}
		return daoSeguros.creaSeguro(s, dni);
	}

	@Override
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = daoClientes.cliente(dni);
		if (cliente == null) {
			return null;
		}

		Seguro seguro = daoSeguros.seguroPorMatricula(matricula);
		if (seguro == null) {
			return null;
		}

		boolean pertenece = false;
		for (Seguro s : cliente.getSeguros()) {
			if (s.getMatricula().equals(matricula)) {
				pertenece = true;
				break;
			}
		}
		if (!pertenece) {
			throw new OperacionNoValida("El seguro no pertenece al cliente indicado");
		}

		return daoSeguros.eliminaSeguro(seguro.getId());
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		Seguro seguro = daoSeguros.seguroPorMatricula(matricula);
		if (seguro == null) {
			return null;
		}
		seguro.setConductorAdicional(conductor);
		return daoSeguros.actualizaSeguro(seguro);
	}

	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		return daoClientes.cliente(dni);
	}

	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		return daoSeguros.seguroPorMatricula(matricula);
	}
}
