package br.ufrn.imd.reservagomvc.exception;

public class PrerequisitosNaoAtendidosException extends NegocioException {

	private static final long serialVersionUID = 4986033354705167054L;

	public PrerequisitosNaoAtendidosException() {
	}

	public PrerequisitosNaoAtendidosException(String message) {
		super(message);
	}
}
