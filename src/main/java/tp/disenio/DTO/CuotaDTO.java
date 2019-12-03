package tp.disenio.DTO;

public class CuotaDTO {
	public double monto;
	public boolean pagado;
	public String vencimiento;
	public int id_cuota;
	




	public int getId_cuota() {
		return id_cuota;
	}
	public void setId_cuota(int id_cuota) {
		this.id_cuota = id_cuota;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}


}
