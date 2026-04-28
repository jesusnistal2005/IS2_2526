package es.unican.is2.seguros.gui;

import static org.assertj.core.api.Assertions.assertThat;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unican.is2.seguros.business.GestionSeguros;
import es.unican.is2.seguros.common.IClientesDAO;
import es.unican.is2.seguros.common.ISegurosDAO;
import es.unican.is2.seguros.dao.h2.ClientesDAO;
import es.unican.is2.seguros.dao.h2.SegurosDAO;

public class VistaAgenteIT extends AssertJSwingJUnitTestCase {

	private FrameFixture window;

	@BeforeClass
	public static void setupOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@Override
	protected void onSetUp() {
		IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);

		VistaAgente frame = GuiActionRunner.execute(() -> new VistaAgente(negocio, negocio, negocio));
		window = new FrameFixture(robot(), frame);
		window.show();
	}

	@Test
	public void consultaClienteValidoMuestraNombreTotalYSeguros() {
		buscarCliente("11111111A");

		window.textBox("txtNombreCliente").requireText("Juan");
		window.textBox("txtTotalCliente").requireText("1820.0");
		window.list().requireItemCount(3);
		assertThat(window.list().contents()).containsExactly("1111AAA TERCEROS", "1111BBB TODO_RIESGO", "1111CCC TERCEROS");
	}

	@Test
	public void consultaClienteInexistenteMuestraMensajeYLimpiaDatos() {
		buscarCliente("11111111A");
		window.list().requireItemCount(3);

		buscarCliente("00000000Z");

		assertThat(window.textBox("txtNombreCliente").text()).startsWith("DNI No");
		window.textBox("txtTotalCliente").requireText("");
		window.list().requireItemCount(0);
	}

	@Test
	public void consultaClienteSinSegurosMuestraTotalCeroYListaVacia() {
		buscarCliente("33333333A");

		window.textBox("txtNombreCliente").requireText("Luis");
		window.textBox("txtTotalCliente").requireText("0.0");
		window.list().requireItemCount(0);
	}

	private void buscarCliente(String dni) {
		GuiActionRunner.execute(() -> {
			JTextField txtDni = (JTextField) window.textBox("txtDNICliente").target();
			JButton btn = (JButton) window.button("btnBuscar").target();
			txtDni.setText(dni);
			btn.doClick();
		});
	}

	@Override
	protected void onTearDown() {
		if (window != null) {
			window.cleanUp();
		}
	}
}
