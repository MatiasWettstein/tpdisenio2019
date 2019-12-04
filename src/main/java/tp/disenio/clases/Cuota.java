package tp.disenio.clases;

public class Cuota {

	private int id_cuota;
	private String fecha_vencimiento;
	private double monto;
	private boolean pagada;
	private int recibo;


	public int getRecibo() {
		return recibo;
	}
	public void setRecibo(int recibo) {
		this.recibo = recibo;
	}
	public int getId_cuota() {
		return id_cuota;
	}
	public void setId_cuota(int id_cuota) {
		this.id_cuota = id_cuota;
	}
	public String getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(String fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double d) {
		this.monto = d;
	}
	public boolean isPagada() {
		return pagada;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}



}
